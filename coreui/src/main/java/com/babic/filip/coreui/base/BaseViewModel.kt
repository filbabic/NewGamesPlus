package com.babic.filip.coreui.base

import android.arch.lifecycle.ViewModel
import com.babic.filip.core.coroutineContext.CoroutineContextProvider
import com.babic.filip.coreui.routing.Router
import com.babic.filip.coreui.routing.RoutingDispatcher
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.channels.BroadcastChannel
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext

abstract class BaseViewModel<Data : Any, View : BaseView> : ViewModel(), StateViewModel<Data, View> {

    protected var view: View? = null

    private val jobs = mutableListOf<Job>()

    override fun viewReady(view: View) {
        this.view = view
        checkStateChannel()
        checkInitialState()
        start()
    }

    private fun checkStateChannel() {
        if (stateChannel.isClosedForSend) {
            stateChannel = createStateChannel()
            checkInitialState()
        }
    }

    abstract fun initialState(): Data

    private fun checkInitialState() {
        val currentState = if (!::state.isInitialized) initialState() else state

        pushViewState(currentState)
    }

    fun onDestroy() {
        jobs.forEach(::cancelJob)
        jobs.clear()
        router = null
        view = null
        stateChannel.close()
    }

    private fun cancelJob(job: Job) {
        if (job.isActive) {
            job.cancel()
        }
    }

    protected fun start() = Unit

    protected lateinit var state: Data
    private var stateChannel = createStateChannel()

    override fun viewState(): ReceiveChannel<Data> = stateChannel.openSubscription()

    private fun createStateChannel() = BroadcastChannel<Data>(Channel.CONFLATED)

    private lateinit var contextProvider: CoroutineContextProvider

    override fun setCoroutineContextProvider(coroutineContextProvider: CoroutineContextProvider) {
        this.contextProvider = coroutineContextProvider
    }

    val main: CoroutineContext by lazy { contextProvider.main }
    val background: CoroutineContext by lazy { contextProvider.io }

    protected inline fun withState(consumer: (Data) -> Unit) = consumer(state)
    protected inline fun <T> fromState(consumer: (Data) -> T) = consumer(state)

    protected fun execute(action: suspend () -> Unit) {
        jobs.add(launch(main) { action() })
    }

    protected fun pushViewState(viewState: Data) {
        this.state = viewState

        sendStateDownstream()
    }

    //override to provide network connection error logic
    open fun showNetworkError() = runViewCommand { it.showNetworkError() }

    //override to provide parsing error logic
    open fun showDataParseError() = runViewCommand { it.showParseError() }

    //override to provide server error logic
    open fun showServerError() = runViewCommand { it.showServerError() }

    //override to provide authentication error logic
    open fun showAuthenticationError() = runViewCommand { it.showAuthenticationError() }

    protected fun runViewCommand(command: (View) -> Unit) {
        view?.run(command)
    }

    protected fun changeViewState(editor: (Data) -> Unit) {
        withState(editor)

        sendStateDownstream()
    }

    private fun sendStateDownstream() {
        if (!stateChannel.isClosedForSend) {
            stateChannel.offer(state)
        }
    }

    private var router: RoutingDispatcher<Router>? = null

    override fun setRoutingSource(routingDispatcher: RoutingDispatcher<Router>) {
        this.router = routingDispatcher
    }

    fun dispatchRoutingAction(action: (Router) -> Unit) {
        router?.dispatchRoutingAction(action)
    }

    suspend fun <T : Any> getData(dataProvider: suspend () -> T): T {
        val deferredData = async(contextProvider.io) { dataProvider() }

        jobs.add(deferredData)

        return deferredData.await()
    }
}
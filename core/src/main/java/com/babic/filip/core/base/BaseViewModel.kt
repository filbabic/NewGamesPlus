package com.babic.filip.core.base

import android.arch.lifecycle.ViewModel
import com.babic.filip.core.routing.Router
import com.babic.filip.core.routing.RoutingDispatcher
import com.filip.babic.data.coroutineContext.CoroutineContextProvider
import com.filip.babic.domain.model.result.Failure
import com.filip.babic.domain.model.result.Result
import kotlinx.coroutines.experimental.channels.BroadcastChannel
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlin.coroutines.experimental.CoroutineContext

abstract class BaseViewModel<Data : Any, View : BaseView>(private val contextProvider: CoroutineContextProvider) : ViewModel(), StateViewModel<Data, View> {

    private val bufferCapacity = 1

    private lateinit var view: View

    override fun viewReady(view: View) {
        setView(view)
        checkInitialState()
        start()
    }

    abstract fun initialState(): Data

    private fun checkInitialState() {
        if (!::state.isInitialized) {
            pushViewState(initialState())
        }
    }

    private fun setView(view: View) {
        this.view = view
    }

    fun onDestroy() {
        router = null
        stateChannel.close()
        errorChannel.close()
    }

    protected fun start() = Unit

    private lateinit var state: Data
    private val stateChannel by lazy { BroadcastChannel<Data>(bufferCapacity) }
    private val errorChannel by lazy { BroadcastChannel<Throwable?>(bufferCapacity) }

    override fun viewState(): ReceiveChannel<Data> = stateChannel.openSubscription()
    override fun errorState(): ReceiveChannel<Throwable?> = errorChannel.openSubscription()

    val main: CoroutineContext by lazy { contextProvider.main }
    val background: CoroutineContext by lazy { contextProvider.io }

    protected fun withState(consumer: (Data) -> Unit) = consumer(state)

    protected fun pushViewState(viewState: Data) {
        this.state = viewState

        sendStateDownstream()
    }

    protected fun checkResultForError(result: Result<*>) {
        if (result is Failure) pushError(result.error)
    }

    protected fun pushError(error: Throwable?) {
        if (!errorChannel.isClosedForSend) {
            errorChannel.offer(error)
        }
    }

    protected fun changeStateData(editor: (Data) -> Unit) {
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
}
package com.babic.filip.coreui.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.babic.filip.core.coroutineContext.CoroutineContextProvider
import com.babic.filip.coreui.routing.Router
import com.babic.filip.coreui.routing.RoutingDispatcher
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<Data : Any, View : BaseView>(
        private val contextProvider: CoroutineContextProvider
) : StatePresenter<Data, View>, CoroutineScope {

    protected var view: View? = null

    private val viewState = MutableLiveData<Data>()

    private var router: RoutingDispatcher<Router>? = null

    private val parentJob = SupervisorJob()

    private val defaultExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("CoroutineException", "Exception occurred in ${Thread.currentThread().name}")
        Log.d("ExceptionMessage", throwable.message ?: "Message unknown")
    }

    override fun viewReady(view: View) {
        this.view = view
        checkInitialState()
        start()
    }

    abstract fun initialState(): Data

    private fun checkInitialState() {
        val data = viewState.value
        val currentState = data ?: initialState()

        pushViewState(currentState)
    }

    fun onDestroy() {
        parentJob.cancelChildren()
        router = null
        view = null
    }

    protected fun start() = Unit

    override fun viewState(): LiveData<Data> = viewState

    protected fun pushViewState(newState: Data) {
        viewState.postValue(newState)
    }

    //override to provide network connection error logic
    open fun showNetworkError() = runViewCommand { it.showNetworkError() }

    //override to provide parsing error logic
    open fun showDataParseError() = runViewCommand { it.showParseError() }

    //override to provide server error logic
    open fun showServerError() = runViewCommand { it.showServerError() }

    //override to provide authentication error logic
    open fun showAuthenticationError() = runViewCommand { it.showAuthenticationError() }

    protected inline fun runViewCommand(command: (View) -> Unit) {
        view?.run(command)
    }

    protected inline fun execute(crossinline action: suspend () -> Unit) {
        launch { action() }
    }

    override fun setRoutingSource(routingDispatcher: RoutingDispatcher<Router>) {
        this.router = routingDispatcher
    }

    fun dispatchRoutingAction(action: (Router) -> Unit) {
        router?.dispatchRoutingAction(action)
    }

    override val coroutineContext: CoroutineContext
        get() = contextProvider.context + defaultExceptionHandler + parentJob
}
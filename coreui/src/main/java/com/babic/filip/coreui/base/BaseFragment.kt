package com.babic.filip.coreui.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.babic.filip.core.common.subscribe
import com.babic.filip.core.coroutineContext.CoroutineContextProvider
import com.babic.filip.coreui.routing.RoutingDispatcher
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import org.koin.android.scope.ext.android.scopedWith

abstract class BaseFragment<Data : Any> : Fragment(), BaseView {

    protected val channels = mutableSetOf<ReceiveChannel<*>>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().viewReady(this)

        scopedWith(getScope())
    }

    fun initViewModel(routingDispatcher: RoutingDispatcher, coroutineContextProvider: CoroutineContextProvider) {
        getViewModel().setRoutingSource(routingDispatcher)
        getViewModel().setCoroutineContextProvider(coroutineContextProvider)
    }

    protected inline fun <reified T> addSubscription(channel: ReceiveChannel<T>, crossinline consumer: (T) -> Unit) {
        channels.add(channel)
        channel.subscribe(consumer)
    }

    //override to provide extra behaviour for error handling, leave it as is when you don't need to handle certain errors
    override fun showAuthenticationError() = Unit

    override fun showNetworkError() = Unit
    override fun showParseError() = Unit
    override fun showServerError() = Unit

    override fun onStop() {
        channels.forEach(::unsubscribeChannel)
        channels.clear()
        getViewModel().viewState().cancel()
        super.onStop()
    }

    override fun onDestroy() {
        val baseViewModel = getViewModel() as? BaseViewModel<*, *>
        baseViewModel?.onDestroy()

        super.onDestroy()
    }

    private fun unsubscribeChannel(channel: ReceiveChannel<*>) {
        channel.cancel()
    }

    abstract fun getViewModel(): StateViewModel<Data, BaseView>

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun getScope(): String
}
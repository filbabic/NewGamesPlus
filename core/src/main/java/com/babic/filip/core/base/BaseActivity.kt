package com.babic.filip.core.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.babic.filip.core.common.subscribe
import com.babic.filip.core.routing.Router
import com.babic.filip.core.routing.RoutingDispatcher
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import org.koin.android.scope.ext.android.scopedWith

abstract class BaseActivity<Data : Any> : AppCompatActivity(), BaseView {

    protected val channels = mutableSetOf<ReceiveChannel<*>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        getViewModel().viewReady(this)

        scopedWith(getScope())
    }

    fun initRouting(routingDispatcher: RoutingDispatcher<Router>) {
        getViewModel().setRoutingSource(routingDispatcher)
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

    private fun unsubscribeChannel(channel: ReceiveChannel<*>) {
        channel.cancel()
    }

    abstract fun getViewModel(): StateViewModel<Data, BaseView>

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun getScope(): String
}
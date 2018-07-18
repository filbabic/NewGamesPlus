package com.babic.filip.core.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.babic.filip.core.common.subscribe
import com.babic.filip.core.routing.Router
import com.babic.filip.core.routing.RoutingDispatcher
import com.filip.babic.data.api.error.ApiDataTransformationException
import com.filip.babic.data.api.error.NetworkException
import com.filip.babic.data.api.error.ServerError
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import org.koin.android.scope.ext.android.scopedWith

abstract class BaseActivity<Data : Any> : AppCompatActivity(), BaseView {

    protected val channels = mutableSetOf<ReceiveChannel<*>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        getViewModel().viewReady(this)
        addSubscription(getViewModel().errorState(), ::processError)

        scopedWith(getScope())
    }

    fun initRouting(routingDispatcher: RoutingDispatcher<Router>) {
        getViewModel().setRoutingSource(routingDispatcher)
    }

    protected inline fun <reified T> addSubscription(channel: ReceiveChannel<T>, crossinline consumer: (T) -> Unit) {
        channels.add(channel)
        channel.subscribe(consumer)
    }

    override fun onStop() {
        channels.forEach(::unsubscribeChannel)
        channels.clear()
        getViewModel().viewState().cancel()
        super.onStop()
    }

    private fun unsubscribeChannel(channel: ReceiveChannel<*>) {
        channel.cancel()
    }

    protected fun processError(throwable: Throwable?) = when (throwable) {
        is ServerError -> showServerError()
        is ApiDataTransformationException -> showDataParseError()
        is NetworkException -> showNetworkError()
        else -> Unit
    }

    //override to provide network connection error logic
    open fun showNetworkError() = Unit

    //override to provide parsing error logic
    open fun showDataParseError() = Unit

    //override to provide server error logic
    open fun showServerError() = Unit

    abstract fun getViewModel(): StateViewModel<Data, BaseView>

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun getScope(): String
}
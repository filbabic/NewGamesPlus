package com.babic.filip.coreui.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.babic.filip.core.common.subscribe
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import org.koin.android.ext.android.get
import org.koin.android.scope.ext.android.scopedWith
import org.koin.core.parameter.parametersOf

abstract class BaseActivity<Data : Any> : AppCompatActivity(), BaseView {

    protected val channels = mutableSetOf<ReceiveChannel<*>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        scopedWith(getScope())

        getViewModel().viewReady(this)
        getViewModel().setRoutingSource(get(parameters = { parametersOf(this) }))
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
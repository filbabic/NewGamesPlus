package com.babic.filip.coreui.base

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.babic.filip.core.common.subscribe
import com.babic.filip.core.coroutineContext.CoroutineContextProviderImpl
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import org.koin.android.ext.android.get
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf

abstract class BaseFragment<Data : Any> : Fragment(), BaseView {

    protected val channels = mutableSetOf<ReceiveChannel<*>>()
    private val scopeRetainer: ScopeRetainer by lazy { buildScopeRetainer() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeScope(savedInstanceState)
        getPresenter().viewReady(this)

        val activity = activity as? BaseActivity<*>
        activity?.run { initPresenter(this) }
    }

    private fun initializeScope(savedInstanceState: Bundle?) {
        savedInstanceState ?: scopeRetainer.initializeScope(getScope())
    }

    private fun initPresenter(baseActivity: BaseActivity<*>) = getPresenter().run {
        setCoroutineContextProvider(get<CoroutineContextProviderImpl>())
        setRoutingSource(get(parameters = { parametersOf(baseActivity) }))
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
        getPresenter().viewState().cancel()
        super.onStop()
    }

    override fun onDestroy() {
        val basePresenter = getPresenter() as? BasePresenter<*, *>
        basePresenter?.onDestroy()

        super.onDestroy()
    }

    private fun unsubscribeChannel(channel: ReceiveChannel<*>) {
        channel.cancel()
    }

    abstract fun getPresenter(): StatePresenter<Data, BaseView>

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun getScope(): String

    private fun buildScopeRetainer(): ScopeRetainer = ViewModelProviders.of(this, getScopeFactory()).get(ScopeRetainer::class.java)

    private fun getScopeFactory() = ScopeRetainerFactory(getKoin())
}
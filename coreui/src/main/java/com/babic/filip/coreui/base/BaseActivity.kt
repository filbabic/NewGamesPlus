package com.babic.filip.coreui.base

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.babic.filip.coreui.common.subscribe
import com.babic.filip.coreui.scope.ScopeRetainer
import com.babic.filip.coreui.scope.ScopeRetainerFactory
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

abstract class BaseActivity<Data : Any> : AppCompatActivity(), BaseView {

    private val scopeRetainer: ScopeRetainer by lazy { buildScopeRetainer() }
    protected val scope: Scope
        get() = scopeRetainer.scope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initializeScope(savedInstanceState)
        initPresenter()

        getPresenter().viewReady(this)
        subscribeToViewState()
    }

    private fun initializeScope(savedInstanceState: Bundle?) {
        savedInstanceState ?: scopeRetainer.initializeScope(getScope())
    }

    private fun initPresenter() {
        getPresenter().setRoutingSource(get(parameters = { parametersOf(this) }))
    }

    //override to provide extra behaviour for error handling, leave it as is when you don't need to handle certain errors
    override fun showAuthenticationError() = Unit

    override fun showNetworkError() = Unit
    override fun showParseError() = Unit
    override fun showServerError() = Unit

    private fun subscribeToViewState() {
        getPresenter().viewState().subscribe(this, ::onViewStateChanged)
    }

    abstract fun onViewStateChanged(viewState: Data)

    override fun onDestroy() {
        val baseViewModel = getPresenter() as? BasePresenter<*, *>
        baseViewModel?.onDestroy()

        super.onDestroy()
    }

    abstract fun getPresenter(): StatePresenter<Data, BaseView>

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun getScope(): String

    private fun buildScopeRetainer(): ScopeRetainer = ViewModelProviders.of(this, getScopeFactory()).get(ScopeRetainer::class.java)

    private fun getScopeFactory() = ScopeRetainerFactory()
}
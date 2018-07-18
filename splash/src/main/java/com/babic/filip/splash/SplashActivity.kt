package com.babic.filip.splash

import android.os.Bundle
import com.babic.filip.core.base.BaseActivity
import com.babic.filip.core.base.BaseView
import com.babic.filip.core.base.StateViewModel
import com.babic.filip.core.common.isVisible
import com.babic.filip.core.common.onClick
import com.babic.filip.core.common.subscribe
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<SplashViewState>() {

    private val splashViewModel: SplashContract.ViewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()

        splashViewModel.viewState().subscribe(::onViewStateChanged)
        splashViewModel.checkUserLoginState()
    }

    private fun initUi() {
        loginButton.onClick { splashViewModel.login() }
        registerButton.onClick { splashViewModel.register() }
    }

    private fun onViewStateChanged(viewState: SplashViewState) {
        loginButton.isVisible = !viewState.isLoggedIn
        registerButton.isVisible = !viewState.isLoggedIn
    }

    override fun getLayout(): Int = R.layout.activity_splash

    override fun getViewModel(): StateViewModel<SplashViewState, BaseView> = splashViewModel as StateViewModel<SplashViewState, BaseView>

    override fun getScope(): String = SPLASH_SCOPE
}

const val SPLASH_SCOPE = "Scope"
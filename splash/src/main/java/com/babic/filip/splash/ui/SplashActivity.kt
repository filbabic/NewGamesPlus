package com.babic.filip.splash.ui

import android.os.Bundle
import com.babic.filip.core.common.subscribe
import com.babic.filip.coreui.base.BaseActivity
import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StatePresenter
import com.babic.filip.coreui.common.isVisible
import com.babic.filip.coreui.common.onClick
import com.babic.filip.splash.R
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<SplashViewState>() {

    private val splashViewModel: SplashContract.Presenter by viewModel<SplashPresenter>()

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

    override fun getViewModel(): StatePresenter<SplashViewState, BaseView> = splashViewModel as StatePresenter<SplashViewState, BaseView>

    override fun getScope(): String = SPLASH_SCOPE
}

const val SPLASH_SCOPE = "Splash"
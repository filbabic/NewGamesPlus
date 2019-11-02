package com.babic.filip.splash.ui

import android.os.Bundle
import com.babic.filip.coreui.base.BaseActivity
import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StatePresenter
import com.babic.filip.coreui.common.isVisible
import com.babic.filip.coreui.common.onClick
import com.babic.filip.splash.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity<SplashViewState>() {

    private val presenter: SplashContract.Presenter by lazy { scope.get<SplashPresenter>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()

        presenter.checkUserLoginState()
    }

    private fun initUi() {
        loginButton.onClick { presenter.login() }
        registerButton.onClick { presenter.register() }
    }

    override fun onViewStateChanged(viewState: SplashViewState) {
        loginButton.isVisible = !viewState.isLoggedIn
        registerButton.isVisible = !viewState.isLoggedIn
    }

    override fun getLayout(): Int = R.layout.activity_splash

    override fun getPresenter(): StatePresenter<SplashViewState, BaseView> = presenter as StatePresenter<SplashViewState, BaseView>

    override fun getScope(): String = SPLASH_SCOPE
}

const val SPLASH_SCOPE = "Splash"
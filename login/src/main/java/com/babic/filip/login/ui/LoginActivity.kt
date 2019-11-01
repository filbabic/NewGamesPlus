package com.babic.filip.login.ui

import com.babic.filip.coreui.base.BaseActivity
import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StatePresenter
import com.babic.filip.login.R
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity<LoginViewState>() {

    private val loginPresenter: StatePresenter<LoginViewState, LoginContract.View> by inject<LoginPresenter>()

    override fun onViewStateChanged(loginViewState: LoginViewState) {
        passwordRoot.error = loginViewState.passwordError?.getError(this)
        emailRoot.error = loginViewState.emailError?.getError(this)
    }

    override fun getLayout(): Int = R.layout.activity_login

    override fun getPresenter(): StatePresenter<LoginViewState, BaseView> = loginPresenter as StatePresenter<LoginViewState, BaseView>

    override fun getScope(): String = LOGIN_SCOPE
}

const val LOGIN_SCOPE = "Login"
package com.babic.filip.login.ui

import com.babic.filip.coreui.base.BaseActivity
import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StatePresenter
import com.babic.filip.login.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginViewState>() {

    private val loginPresenter: LoginContract.Presenter by lazy { scope.get<LoginPresenter>() }

    override fun onViewStateChanged(viewState: LoginViewState) {
        passwordRoot.error = viewState.passwordError?.getError(this)
        emailRoot.error = viewState.emailError?.getError(this)
    }

    override fun getLayout(): Int = R.layout.activity_login

    override fun getPresenter(): StatePresenter<LoginViewState, BaseView> = loginPresenter as StatePresenter<LoginViewState, BaseView>

    override fun getScope(): String = LOGIN_SCOPE
}

const val LOGIN_SCOPE = "Login"
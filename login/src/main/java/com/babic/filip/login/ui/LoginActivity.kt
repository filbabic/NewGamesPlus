package com.babic.filip.login.ui

import android.os.Bundle
import com.babic.filip.core.common.subscribe
import com.babic.filip.coreui.base.BaseActivity
import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StateViewModel
import com.babic.filip.login.R
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<LoginViewState>() {

    private val loginViewModel: StateViewModel<LoginViewState, LoginContract.View> by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel.viewState().subscribe(::onViewStateChanged)
    }

    private fun onViewStateChanged(loginViewState: LoginViewState) {
        passwordRoot.error = loginViewState.passwordError?.getError(this)
        emailRoot.error = loginViewState.emailError?.getError(this)
    }

    override fun getLayout(): Int = R.layout.activity_login

    override fun getViewModel(): StateViewModel<LoginViewState, BaseView> = loginViewModel as StateViewModel<LoginViewState, BaseView>

    override fun getScope(): String = LOGIN_SCOPE
}

const val LOGIN_SCOPE = "Login"
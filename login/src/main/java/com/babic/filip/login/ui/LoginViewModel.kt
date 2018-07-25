package com.babic.filip.login.ui

import com.babic.filip.core.base.BaseViewModel
import com.babic.filip.core.base.StateViewModel

class LoginViewModel : BaseViewModel<LoginViewState, LoginContract.View>(), StateViewModel<LoginViewState, LoginContract.View> {

    override fun initialState(): LoginViewState = LoginViewState()
}
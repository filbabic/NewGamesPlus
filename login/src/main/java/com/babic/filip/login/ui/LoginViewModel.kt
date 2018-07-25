package com.babic.filip.login.ui

import com.babic.filip.core.base.BaseViewModel
import com.babic.filip.core.base.StateViewModel
import com.babic.filip.core.coroutineContext.CoroutineContextProvider

class LoginViewModel(contextProvider: CoroutineContextProvider) : BaseViewModel<LoginViewState, LoginContract.View>(contextProvider), StateViewModel<LoginViewState, LoginContract.View> {

    override fun initialState(): LoginViewState = LoginViewState()
}
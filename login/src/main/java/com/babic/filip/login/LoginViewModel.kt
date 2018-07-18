package com.babic.filip.login

import com.babic.filip.core.base.BaseViewModel
import com.babic.filip.core.base.StateViewModel
import com.babic.filip.core.routing.LoginRouter
import com.filip.babic.data.coroutineContext.CoroutineContextProvider

class LoginViewModel(contextProvider: CoroutineContextProvider) : BaseViewModel<LoginViewState, LoginContract.View>(contextProvider), StateViewModel<LoginViewState, LoginContract.View> {

    override fun initialState(): LoginViewState = LoginViewState()
}
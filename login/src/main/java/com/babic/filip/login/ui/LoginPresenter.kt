package com.babic.filip.login.ui

import com.babic.filip.core.coroutineContext.CoroutineContextProvider
import com.babic.filip.coreui.base.BasePresenter
import com.babic.filip.coreui.base.StatePresenter

class LoginPresenter(
        contextProvider: CoroutineContextProvider
) : BasePresenter<LoginViewState, LoginContract.View>(contextProvider), LoginContract.Presenter {

    override fun initialState(): LoginViewState = LoginViewState()
}
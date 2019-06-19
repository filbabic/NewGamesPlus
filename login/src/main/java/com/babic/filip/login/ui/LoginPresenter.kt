package com.babic.filip.login.ui

import com.babic.filip.coreui.base.BasePresenter
import com.babic.filip.coreui.base.StatePresenter

class LoginPresenter : BasePresenter<LoginViewState, LoginContract.View>(), StatePresenter<LoginViewState, LoginContract.View> {

    override fun initialState(): LoginViewState = LoginViewState()
}
package com.babic.filip.login.ui

import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StatePresenter

interface LoginContract {

    interface View : BaseView

    interface Presenter : StatePresenter<LoginViewState, View>
}
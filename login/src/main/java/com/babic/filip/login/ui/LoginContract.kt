package com.babic.filip.login.ui

import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StateViewModel

interface LoginContract {

    interface View : BaseView

    interface ViewModel : StateViewModel<LoginViewState, View>
}
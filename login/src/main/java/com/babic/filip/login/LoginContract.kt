package com.babic.filip.login

import com.babic.filip.core.base.BaseView
import com.babic.filip.core.base.StateViewModel

interface LoginContract {

    interface View : BaseView

    interface ViewModel : StateViewModel<LoginViewState, View>
}
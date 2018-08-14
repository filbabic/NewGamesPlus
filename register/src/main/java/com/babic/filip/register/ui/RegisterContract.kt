package com.babic.filip.register.ui

import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StateViewModel

interface RegisterContract {

    interface View : BaseView

    interface ViewModel : StateViewModel<RegisterViewState, View> {

        fun usernameChanged(username: String)

        fun emailChanged(email: String)

        fun passwordChanged(password: String)

        fun registerUser()
    }
}
package com.babic.filip.register.ui

import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StatePresenter

interface RegisterContract {

    interface View : BaseView

    interface Presenter : StatePresenter<RegisterViewState, View> {

        fun usernameChanged(username: String)

        fun emailChanged(email: String)

        fun passwordChanged(password: String)

        fun registerUser()
    }
}
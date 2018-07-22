package com.babic.filip.register

import com.babic.filip.core.base.BaseView
import com.babic.filip.core.base.StateViewModel

interface RegisterContract {

    interface View : BaseView {

        fun showUserExistsError()

        fun showInvalidCredentialsError()
    }

    interface ViewModel : StateViewModel<RegisterViewState, View> {

        fun usernameChanged(username: String)

        fun emailChanged(email: String)

        fun passwordChanged(password: String)

        fun registerUser()
    }
}
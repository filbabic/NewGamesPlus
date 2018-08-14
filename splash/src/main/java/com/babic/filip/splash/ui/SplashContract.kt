package com.babic.filip.splash.ui

import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StateViewModel

interface SplashContract {

    interface View : BaseView

    interface ViewModel : StateViewModel<SplashViewState, View> {

        fun checkUserLoginState()

        fun login()

        fun register()
    }
}
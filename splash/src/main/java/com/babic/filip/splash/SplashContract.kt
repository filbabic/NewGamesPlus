package com.babic.filip.splash

import com.babic.filip.core.base.BaseView
import com.babic.filip.core.base.StateViewModel

interface SplashContract {

    interface View : BaseView

    interface ViewModel : StateViewModel<SplashViewState, View> {

        fun checkUserLoginState()

        fun login()

        fun register()
    }
}
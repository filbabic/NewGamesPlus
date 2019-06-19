package com.babic.filip.splash.ui

import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StatePresenter

interface SplashContract {

    interface View : BaseView

    interface Presenter : StatePresenter<SplashViewState, View> {

        fun checkUserLoginState()

        fun login()

        fun register()
    }
}
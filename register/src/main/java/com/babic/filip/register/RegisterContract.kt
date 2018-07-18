package com.babic.filip.register

import com.babic.filip.core.base.BaseView
import com.babic.filip.core.base.StateViewModel

interface RegisterContract {

    interface View : BaseView

    interface ViewModel : StateViewModel<RegisterViewState, View> {

    }
}
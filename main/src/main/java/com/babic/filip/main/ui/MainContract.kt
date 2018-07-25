package com.babic.filip.main.ui

import com.babic.filip.core.base.BaseView
import com.babic.filip.core.base.StateViewModel

interface MainContract {

    interface View : BaseView

    interface ViewModel : StateViewModel<MainViewState, View>
}
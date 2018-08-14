package com.babic.filip.main.ui

import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StateViewModel

interface MainContract {

    interface View : BaseView

    interface ViewModel : StateViewModel<MainViewState, View> {

        fun setCurrentPage(selectedPage: Int)

        fun showTopRated()

        fun showUpcoming()

        fun showFeed()

        fun showMessages()

        fun showMyProfile()

        fun refreshPage()
    }
}
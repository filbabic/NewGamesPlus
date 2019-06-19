package com.babic.filip.main.ui

import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.EmptyState
import com.babic.filip.coreui.base.StatePresenter

interface MainContract {

    interface View : BaseView

    interface Presenter : StatePresenter<EmptyState, View> {

        fun showTopRated()

        fun showUpcoming()

        fun showFeed()

        fun showMessages()

        fun showMyProfile()

        fun refreshPage()
    }
}
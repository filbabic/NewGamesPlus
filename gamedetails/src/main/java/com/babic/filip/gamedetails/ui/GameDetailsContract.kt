package com.babic.filip.gamedetails.ui

import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StatePresenter

interface GameDetailsContract {

    interface View : BaseView

    interface Presenter : StatePresenter<GameDetailsViewState, View> {

        fun showDetails(gameId: String)
    }
}
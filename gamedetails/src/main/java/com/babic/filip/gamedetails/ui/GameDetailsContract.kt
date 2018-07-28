package com.babic.filip.gamedetails.ui

import com.babic.filip.core.base.BaseView
import com.babic.filip.core.base.StateViewModel

interface GameDetailsContract {

    interface View : BaseView

    interface ViewModel : StateViewModel<GameDetailsViewState, View> {

        fun showDetails(gameId: String)
    }
}
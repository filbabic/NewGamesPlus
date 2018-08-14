package com.babic.filip.main.ui.topRated

import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StateViewModel
import com.babic.filip.main.domain.model.Game

interface TopRatedGamesContract {

    interface View : BaseView

    interface ViewModel : StateViewModel<GamesViewState, View> {

        fun getTopRatedGames()

        fun refresh()

        fun showDetails(game: Game)
    }
}
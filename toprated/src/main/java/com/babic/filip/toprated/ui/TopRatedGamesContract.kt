package com.babic.filip.toprated.ui

import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StatePresenter
import com.babic.filip.toprated.domain.model.TopRatedGame

interface TopRatedGamesContract {

    interface View : BaseView

    interface Presenter : StatePresenter<GamesViewState, View> {

        fun getTopRatedGames()

        fun refresh()

        fun showDetails(game: TopRatedGame)
    }
}
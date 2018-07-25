package com.babic.filip.main.ui.topRated

import com.babic.filip.core.base.BaseViewModel
import com.babic.filip.main.domain.model.Game

class TopRatedGamesViewModel : BaseViewModel<GamesViewState, TopRatedGamesContract.View>(), TopRatedGamesContract.ViewModel {

    override fun initialState(): GamesViewState = GamesViewState()

    override fun getTopRatedGames() {
    }

    override fun refresh() {
    }

    override fun showDetails(game: Game) {
        dispatchRoutingAction { it.showGameDetails(game.id) }
    }
}
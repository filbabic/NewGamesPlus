package com.babic.filip.main.ui.topRated

import com.babic.filip.core.base.BaseViewModel
import com.babic.filip.main.domain.interaction.GetTopRatedGamesUseCase
import com.babic.filip.main.domain.model.Game
import com.babic.filip.networking.data.model.doOnError
import com.babic.filip.networking.data.model.doOnSuccess

class TopRatedGamesViewModel(private val getTopRatedGamesUseCase: GetTopRatedGamesUseCase) : BaseViewModel<GamesViewState, TopRatedGamesContract.View>(), TopRatedGamesContract.ViewModel {

    private var page = 0

    override fun initialState(): GamesViewState = GamesViewState()

    override fun getTopRatedGames() = execute {
        val isLoading = fromState { it.isLoading }

        if (!isLoading) {
            changeViewState { it.isLoading = true }

            val data = getData { getTopRatedGamesUseCase(page) }

            data.doOnSuccess(::onDataLoaded).doOnError { error ->
                changeViewState { it.isLoading = false }
                processError(error)
            }
        }
    }

    private fun onDataLoaded(games: List<Game>) {
        val currentPage = page
        page++

        changeViewState { viewState ->
            val allItems = if (currentPage == 0) games else viewState.games + games

            viewState.games = allItems
            viewState.isLoading = false
        }
    }

    private fun processError(error: Throwable?) {
        //todo add error handling
    }

    override fun refresh() {
        page = 0
        getTopRatedGames()
    }

    override fun showDetails(game: Game) = dispatchRoutingAction { it.showGameDetails(game.id) }
}
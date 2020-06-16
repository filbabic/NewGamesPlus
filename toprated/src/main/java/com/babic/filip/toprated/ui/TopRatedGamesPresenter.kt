package com.babic.filip.toprated.ui

import com.babic.filip.coreui.base.BasePresenter
import com.babic.filip.networking.data.model.doOnError
import com.babic.filip.networking.data.model.doOnSuccess
import com.babic.filip.toprated.domain.interaction.GetTopRatedGamesUseCase
import com.babic.filip.toprated.domain.model.TopRatedGame

class TopRatedGamesPresenter(private val getTopRatedGames: GetTopRatedGamesUseCase) : BasePresenter<GamesViewState, TopRatedGamesContract.View>(), TopRatedGamesContract.Presenter {

    private var page = 0

    override fun initialState(): GamesViewState = GamesViewState()

    override fun getTopRatedGames() = execute {
        val isLoading = fromState { it.isLoading }

        if (!isLoading) {
            changeViewState { it.isLoading = true }

            val data = getData { getTopRatedGames(page) }

            data.doOnSuccess(::onDataLoaded).doOnError { error ->
                changeViewState { it.isLoading = false }
                processError(error)
            }
        }
    }

    private fun onDataLoaded(games: List<TopRatedGame>) {
        val currentPage = page
        page++

        // todo implement this
        val newGames = mapper.mapGamesToViewState(games)

        changeViewState { currentState -> mapper.updateState(currentState, games) }

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

    override fun showDetails(game: TopRatedGame) = dispatchRoutingAction { it.showGameDetails(game.id) }

    override fun start() {
    }
}
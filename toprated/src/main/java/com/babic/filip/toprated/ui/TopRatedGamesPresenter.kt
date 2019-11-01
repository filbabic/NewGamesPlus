package com.babic.filip.toprated.ui

import com.babic.filip.core.coroutineContext.CoroutineContextProvider
import com.babic.filip.coreui.base.BasePresenter
import com.babic.filip.networking.data.model.doOnError
import com.babic.filip.networking.data.model.doOnSuccess
import com.babic.filip.toprated.domain.interaction.GetTopRatedGamesUseCase
import com.babic.filip.toprated.domain.model.TopRatedGame

class TopRatedGamesPresenter(
        contextProvider: CoroutineContextProvider,
        private val getTopRatedGamesUseCase: GetTopRatedGamesUseCase,
        private val mapper: TopRatedGamesViewStateMapper)
    : BasePresenter<GamesViewState, TopRatedGamesContract.View>(contextProvider), TopRatedGamesContract.Presenter {

    private var page = 0
    private var isLoading = false

    override fun initialState(): GamesViewState = GamesViewState()

    override fun getTopRatedGames() = execute {
        if (!isLoading) {
            isLoading = true

            val data = getTopRatedGamesUseCase(page)

            data.doOnSuccess(::onDataLoaded).doOnError { error ->
                isLoading = false
                processError(error)
            }
        }
    }

    private fun onDataLoaded(games: List<TopRatedGame>) {
        page++

        val viewState = viewState().value
        val totalGames = if (viewState == null || viewState.games.isEmpty()) games else viewState.games + games

        isLoading = false
        pushViewState(mapper.mapToViewState(totalGames))
    }

    private fun processError(error: Throwable?) {
        //todo add error handling
    }

    override fun refresh() {
        page = 0
        getTopRatedGames()
    }

    override fun showDetails(game: TopRatedGame) = dispatchRoutingAction { it.showGameDetails(game.id) }
}
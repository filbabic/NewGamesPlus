package com.babic.filip.gamedetails.ui

import com.babic.filip.core.coroutineContext.CoroutineContextProvider
import com.babic.filip.coreui.base.BasePresenter
import com.babic.filip.gamedetails.domain.interaction.GetGameDetailsUseCase
import com.babic.filip.gamedetails.domain.model.GameDetails
import com.babic.filip.networking.data.model.doOnError
import com.babic.filip.networking.data.model.doOnSuccess

class GameDetailsPresenter(
        contextProvider: CoroutineContextProvider,
        private val getGameDetails: GetGameDetailsUseCase,
        private val mapper: GameDetailsViewStateMapper)
    : BasePresenter<GameDetailsViewState, GameDetailsContract.View>(contextProvider), GameDetailsContract.Presenter {

    private var gameId: String = ""

    override fun initialState(): GameDetailsViewState = GameDetailsViewState.EMPTY

    override fun showDetails(gameId: String) {
        setGameId(gameId)
        getDetails()
    }

    private fun setGameId(gameId: String) {
        this.gameId = gameId
    }

    private fun getDetails() = execute {
        val result = getGameDetails(gameId)

        result.doOnSuccess(::onDataLoaded)
                .doOnError(::processError)
    }

    private fun onDataLoaded(gameDetails: GameDetails) {
        pushViewState(mapper.mapToViewState(gameDetails))
    }

    private fun processError(throwable: Throwable?) {
        //todo handle error
    }
}
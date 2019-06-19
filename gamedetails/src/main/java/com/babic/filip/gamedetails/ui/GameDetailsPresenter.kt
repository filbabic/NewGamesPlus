package com.babic.filip.gamedetails.ui

import com.babic.filip.coreui.base.BasePresenter
import com.babic.filip.gamedetails.domain.interaction.GetGameDetailsUseCase
import com.babic.filip.gamedetails.domain.model.GameDetails
import com.babic.filip.networking.data.model.doOnError
import com.babic.filip.networking.data.model.doOnSuccess

class GameDetailsPresenter(private val getGameDetails: GetGameDetailsUseCase) : BasePresenter<GameDetailsViewState, GameDetailsContract.View>(), GameDetailsContract.Presenter {

    private var gameId: String = ""

    override fun initialState(): GameDetailsViewState = GameDetailsViewState()

    override fun showDetails(gameId: String) {
        setGameId(gameId)
        getDetails()
    }

    private fun setGameId(gameId: String) {
        this.gameId = gameId
    }

    private fun getDetails() = execute {
        val result = getData { getGameDetails(gameId) }

        result.doOnSuccess(::onDataLoaded).doOnError(::processError)
    }

    private fun onDataLoaded(gameDetails: GameDetails) = changeViewState { it.gameDetails = gameDetails }

    private fun processError(throwable: Throwable?) {
        //todo handle error
    }
}
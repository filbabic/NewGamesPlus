package com.babic.filip.main.ui.topRated

import com.babic.filip.core.base.BaseViewModel

class TopRatedGamesViewModel : BaseViewModel<GamesViewState, TopRatedGamesContract.View>() {

    override fun initialState(): GamesViewState = GamesViewState()
}
package com.babic.filip.main.ui.topRated

import com.babic.filip.main.domain.model.Game

class GamesViewState(var games: List<Game> = listOf(),
                     var isLoading: Boolean = false)
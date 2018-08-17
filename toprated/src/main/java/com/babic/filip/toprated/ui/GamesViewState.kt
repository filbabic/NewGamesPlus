package com.babic.filip.toprated.ui

import com.babic.filip.toprated.domain.model.TopRatedGame

class GamesViewState(var games: List<TopRatedGame> = listOf(),
                     var isLoading: Boolean = false)
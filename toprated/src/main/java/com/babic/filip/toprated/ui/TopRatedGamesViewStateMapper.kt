package com.babic.filip.toprated.ui

import com.babic.filip.toprated.domain.model.TopRatedGame

/**
 * Takes in a list of Top rated games, and maps them into something displayable on the UI.
 */
interface TopRatedGamesViewStateMapper {

    fun mapToViewState(games: List<TopRatedGame>): GamesViewState
}
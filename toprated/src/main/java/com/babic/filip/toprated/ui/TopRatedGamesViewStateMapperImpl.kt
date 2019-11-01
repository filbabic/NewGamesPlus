package com.babic.filip.toprated.ui

import com.babic.filip.toprated.domain.model.TopRatedGame

/**
 * Maps the data into displayable [GamesViewState].
 */
class TopRatedGamesViewStateMapperImpl : TopRatedGamesViewStateMapper {

    override fun mapToViewState(games: List<TopRatedGame>): GamesViewState {
        return GamesViewState(games)
    }
}
package com.babic.filip.gamedetails.ui

import com.babic.filip.gamedetails.domain.model.GameDetails

/**
 * Maps the data to [GameDetailsViewState], preparing formatted data for the UI.
 */
class GameDetailsViewStateMapperImpl : GameDetailsViewStateMapper {

    override fun mapToViewState(gameDetails: GameDetails): GameDetailsViewState {
        return GameDetailsViewState(
                gameDetails.id,
                gameDetails.name,
                gameDetails.summary,
                gameDetails.url,
                gameDetails.storyline,
                gameDetails.popularity,
                gameDetails.totalRating,
                gameDetails.cover
        )
    }
}
package com.babic.filip.gamedetails.ui

import com.babic.filip.gamedetails.domain.model.GameDetails

/**
 * Used to map data to [GameDetailsViewState].
 */
interface GameDetailsViewStateMapper {

    fun mapToViewState(gameDetails: GameDetails): GameDetailsViewState
}
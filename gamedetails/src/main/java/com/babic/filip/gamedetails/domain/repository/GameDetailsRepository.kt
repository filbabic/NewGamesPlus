package com.babic.filip.gamedetails.domain.repository

import com.babic.filip.gamedetails.domain.model.GameDetails
import com.babic.filip.networking.data.model.Result

interface GameDetailsRepository {

   suspend fun getGameDetails(gameId: String): Result<GameDetails>
}
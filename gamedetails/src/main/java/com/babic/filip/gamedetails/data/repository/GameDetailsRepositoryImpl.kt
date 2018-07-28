package com.babic.filip.gamedetails.data.repository

import com.babic.filip.gamedetails.data.networking.GameDetailsApiService
import com.babic.filip.gamedetails.domain.model.GameDetails
import com.babic.filip.gamedetails.domain.repository.GameDetailsRepository
import com.babic.filip.networking.data.common.getResult
import com.babic.filip.networking.data.model.Result

class GameDetailsRepositoryImpl(private val apiService: GameDetailsApiService) : GameDetailsRepository {

    override suspend fun getGameDetails(gameId: String): Result<GameDetails> = apiService.getGameDetails(gameId).getResult()
}
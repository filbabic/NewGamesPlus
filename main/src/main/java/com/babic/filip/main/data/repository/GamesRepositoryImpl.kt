package com.babic.filip.main.data.repository

import com.babic.filip.main.data.network.GamesApiService
import com.babic.filip.main.domain.model.Game
import com.babic.filip.main.domain.repository.GamesRepository
import com.babic.filip.networking.data.common.getResult
import com.babic.filip.networking.data.model.Result

class GamesRepositoryImpl(private val gamesApiService: GamesApiService) : GamesRepository {

    override suspend fun getTopRatedGames(page: Int): Result<List<Game>> = gamesApiService.getTopRatedGames(page).getResult()
}
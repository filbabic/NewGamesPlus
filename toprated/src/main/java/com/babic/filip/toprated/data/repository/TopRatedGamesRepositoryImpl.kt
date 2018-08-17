package com.babic.filip.toprated.data.repository

import com.babic.filip.networking.data.common.getResult
import com.babic.filip.networking.data.model.Result
import com.babic.filip.toprated.data.network.TopRatedGamesApiService
import com.babic.filip.toprated.domain.model.TopRatedGame
import com.babic.filip.toprated.domain.repository.TopRatedGamesRepository

class TopRatedGamesRepositoryImpl(private val gamesApiService: TopRatedGamesApiService) : TopRatedGamesRepository {

    override suspend fun getTopRatedGames(page: Int): Result<List<TopRatedGame>> = gamesApiService.getTopRatedGames(page).getResult()
}
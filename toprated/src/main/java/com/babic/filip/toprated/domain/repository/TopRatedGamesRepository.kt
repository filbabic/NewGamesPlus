package com.babic.filip.toprated.domain.repository

import com.babic.filip.networking.data.model.Result
import com.babic.filip.toprated.domain.model.TopRatedGame

interface TopRatedGamesRepository {

    suspend fun getTopRatedGames(page: Int): Result<List<TopRatedGame>>
}
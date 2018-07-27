package com.babic.filip.main.domain.repository

import com.babic.filip.main.domain.model.Game
import com.babic.filip.networking.data.model.Result

interface GamesRepository {

    suspend fun getTopRatedGames(page: Int): Result<List<Game>>
}
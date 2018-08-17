package com.babic.filip.toprated.domain.interaction

import com.babic.filip.core.domain.interaction.GetUseCaseWithParam
import com.babic.filip.networking.data.model.Result
import com.babic.filip.toprated.domain.model.TopRatedGame
import com.babic.filip.toprated.domain.repository.TopRatedGamesRepository

class GetTopRatedGamesUseCase(private val repository: TopRatedGamesRepository) : GetUseCaseWithParam<Int, Result<List<TopRatedGame>>> {

    override suspend operator fun invoke(param: Int): Result<List<TopRatedGame>> = repository.getTopRatedGames(param)
}
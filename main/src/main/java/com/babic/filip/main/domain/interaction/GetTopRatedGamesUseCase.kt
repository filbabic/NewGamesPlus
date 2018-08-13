package com.babic.filip.main.domain.interaction

import com.babic.filip.core.domain.interaction.GetUseCaseWithParam
import com.babic.filip.main.domain.model.Game
import com.babic.filip.main.domain.repository.GamesRepository
import com.babic.filip.networking.data.model.Result

class GetTopRatedGamesUseCase(private val repository: GamesRepository) : GetUseCaseWithParam<Int, Result<List<Game>>> {

    override suspend operator fun invoke(param: Int): Result<List<Game>> = repository.getTopRatedGames(param)
}
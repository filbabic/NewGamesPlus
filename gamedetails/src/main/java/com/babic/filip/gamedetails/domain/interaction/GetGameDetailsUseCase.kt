package com.babic.filip.gamedetails.domain.interaction

import com.babic.filip.core.domain.interaction.GetUseCaseWithParam
import com.babic.filip.gamedetails.domain.model.GameDetails
import com.babic.filip.gamedetails.domain.repository.GameDetailsRepository
import com.babic.filip.networking.data.model.Result

class GetGameDetailsUseCase(private val gameDetailsRepository: GameDetailsRepository) : GetUseCaseWithParam<String, Result<GameDetails>> {

    override suspend fun run(param: String): Result<GameDetails> = gameDetailsRepository.getGameDetails(param)
}
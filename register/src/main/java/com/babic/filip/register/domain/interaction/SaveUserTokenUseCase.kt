package com.babic.filip.register.domain.interaction

import com.babic.filip.core.domain.interaction.CommandUseCaseWithParam
import com.filip.babic.device.repository.UserPreferencesRepository

class SaveUserTokenUseCase(private val userPreferencesRepository: UserPreferencesRepository) : CommandUseCaseWithParam<String> {

    override fun invoke(param: String) = userPreferencesRepository.setToken(param)
}
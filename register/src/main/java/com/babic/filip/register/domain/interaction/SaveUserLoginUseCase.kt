package com.babic.filip.register.domain.interaction

import com.babic.filip.core.domain.interaction.CommandUseCaseWithParam
import com.filip.babic.device.repository.UserPreferencesRepository

class SaveUserLoginUseCase(private val userPreferencesRepository: UserPreferencesRepository) : CommandUseCaseWithParam<String> {

    override fun execute(param: String) = userPreferencesRepository.setToken(param)
}
package com.filip.babic.domain.interaction

import com.filip.babic.domain.interaction.type.CommandUseCaseWithParam
import com.filip.babic.domain.repository.UserPreferencesRepository

class SaveUserLoginUseCase(private val userPreferencesRepository: UserPreferencesRepository) : CommandUseCaseWithParam<String> {

    override fun execute(param: String) {
        userPreferencesRepository.setToken(param)
    }
}
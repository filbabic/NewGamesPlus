package com.babic.filip.splash.domain.interaction

import com.babic.filip.core.domain.interaction.GetUseCase
import com.filip.babic.device.repository.UserPreferencesRepository

class GetUserLoggedInUseCase(private val userPreferencesRepository: UserPreferencesRepository) : GetUseCase<Boolean> {

    override suspend operator fun invoke(): Boolean = userPreferencesRepository.isLoggedIn()
}
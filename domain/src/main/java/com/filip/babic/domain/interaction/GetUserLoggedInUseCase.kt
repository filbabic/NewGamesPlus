package com.filip.babic.domain.interaction

import com.filip.babic.domain.interaction.type.GetUseCase
import com.filip.babic.domain.repository.UserPreferencesRepository

class GetUserLoggedInUseCase(private val userPreferencesRepository: UserPreferencesRepository) : GetUseCase<Boolean> {

    override suspend fun get(): Boolean = userPreferencesRepository.isLoggedIn()
}
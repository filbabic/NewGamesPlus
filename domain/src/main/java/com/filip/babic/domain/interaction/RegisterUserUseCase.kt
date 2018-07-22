package com.filip.babic.domain.interaction

import com.filip.babic.domain.interaction.type.GetUseCaseWithParam
import com.filip.babic.domain.model.UserRegistration
import com.filip.babic.domain.model.request.RegisterData
import com.filip.babic.domain.model.result.Result
import com.filip.babic.domain.repository.AuthRepository

class RegisterUserUseCase(private val authRepository: AuthRepository) : GetUseCaseWithParam<RegisterData, UserRegistration> {

    override suspend fun run(param: RegisterData): Result<UserRegistration> = authRepository.registerUser(param)
}
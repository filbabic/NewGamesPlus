package com.babic.filip.register.domain.interaction

import com.babic.filip.core.domain.interaction.GetUseCaseWithParam
import com.babic.filip.networking.data.model.Result
import com.babic.filip.register.data.model.RegisterData
import com.babic.filip.register.domain.model.UserRegistration
import com.babic.filip.register.domain.repository.RegisterRepository

class RegisterUserUseCase(private val registerRepository: RegisterRepository) : GetUseCaseWithParam<RegisterData, Result<UserRegistration>> {

    override suspend operator fun invoke(param: RegisterData): Result<UserRegistration> = registerRepository.registerUser(param)
}
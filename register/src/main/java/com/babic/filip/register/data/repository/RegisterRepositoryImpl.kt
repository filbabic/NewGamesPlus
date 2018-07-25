package com.babic.filip.register.data.repository

import com.babic.filip.networking.data.common.getResult
import com.babic.filip.networking.data.model.Result
import com.babic.filip.register.data.model.RegisterData
import com.babic.filip.register.data.networking.RegisterApiService
import com.babic.filip.register.domain.model.UserRegistration
import com.babic.filip.register.domain.repository.RegisterRepository

class RegisterRepositoryImpl(private val registerApiService: RegisterApiService) : RegisterRepository {

    override suspend fun registerUser(registerData: RegisterData): Result<UserRegistration> = registerApiService.registerUser(registerData).getResult()
}
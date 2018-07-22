package com.filip.babic.data.repository

import com.filip.babic.data.common.getResult
import com.filip.babic.data.networking.service.AuthApiService
import com.filip.babic.domain.model.UserRegistration
import com.filip.babic.domain.model.request.RegisterData
import com.filip.babic.domain.model.result.Result
import com.filip.babic.domain.repository.AuthRepository

class AuthRepositoryImpl(private val authApiService: AuthApiService) : AuthRepository {

    override suspend fun registerUser(registerData: RegisterData): Result<UserRegistration> = authApiService.registerUser(registerData).getResult()
}
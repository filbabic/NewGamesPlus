package com.filip.babic.domain.repository

import com.filip.babic.domain.model.UserRegistration
import com.filip.babic.domain.model.request.RegisterData
import com.filip.babic.domain.model.result.Result

interface AuthRepository {

    suspend fun registerUser(registerData: RegisterData) : Result<UserRegistration>
}
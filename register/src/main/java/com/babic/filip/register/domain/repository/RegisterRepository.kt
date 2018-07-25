package com.babic.filip.register.domain.repository

import com.babic.filip.networking.data.model.Result
import com.babic.filip.register.data.model.RegisterData
import com.babic.filip.register.domain.model.UserRegistration

interface RegisterRepository {

    suspend fun registerUser(registerData: RegisterData) : Result<UserRegistration>
}
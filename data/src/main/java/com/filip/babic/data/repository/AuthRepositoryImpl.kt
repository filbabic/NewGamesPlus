package com.filip.babic.data.repository

import com.filip.babic.data.api.service.AuthApiService
import com.filip.babic.domain.repository.AuthRepository

class AuthRepositoryImpl(private val authApiService: AuthApiService) : AuthRepository {

}
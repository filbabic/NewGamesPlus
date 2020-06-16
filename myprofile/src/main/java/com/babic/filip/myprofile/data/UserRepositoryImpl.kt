package com.babic.filip.myprofile.data

import com.babic.filip.myprofile.api.UserApiService
import com.babic.filip.myprofile.domain.UserRepository

class UserRepositoryImpl(private val userApiService: UserApiService) : UserRepository {
}
package com.filip.babic.data.networking.service

import com.filip.babic.data.networking.model.UserRegistrationResponse
import com.filip.babic.domain.model.request.RegisterData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("/api/register")
    fun registerUser(@Body registerData: RegisterData): Call<UserRegistrationResponse>
}
package com.babic.filip.register.data.networking

import com.babic.filip.register.data.model.RegisterData
import com.babic.filip.register.data.model.UserRegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApiService {

    @POST("/api/register")
    fun registerUser(@Body registerData: RegisterData): Call<UserRegistrationResponse>
}
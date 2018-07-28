package com.babic.filip.gamedetails.data.networking

import com.babic.filip.gamedetails.data.model.GameDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GameDetailsApiService {

    @GET("/api/games/{id}")
    fun getGameDetails(@Path("id") gameId: String): Call<GameDetailsResponse>
}
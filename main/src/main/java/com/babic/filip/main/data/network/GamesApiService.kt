package com.babic.filip.main.data.network

import com.babic.filip.main.data.model.GamesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApiService {

    @GET("/api/games/topRated")
    fun getTopRatedGames(@Query("page") page: Int) : Call<GamesResponse>
}
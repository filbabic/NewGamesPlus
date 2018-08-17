package com.babic.filip.toprated.data.network

import com.babic.filip.toprated.data.model.TopRatedGamesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRatedGamesApiService {

    @GET("/api/games/topRated")
    fun getTopRatedGames(@Query("page") page: Int) : Call<TopRatedGamesResponse>
}
package com.babic.filip.toprated.data.model

import com.babic.filip.toprated.domain.model.TopRatedGame
import com.babic.filip.networking.data.model.Mappable
import com.google.gson.annotations.SerializedName

class TopRatedGamesResponse(@SerializedName("body") val games: List<TopRatedGameResponse> = listOf()) : Mappable<List<TopRatedGame>> {

    override fun isValid(): Boolean = games.isNotEmpty()

    override fun mapToData(): List<TopRatedGame> = games.map { it.mapToData() }
}
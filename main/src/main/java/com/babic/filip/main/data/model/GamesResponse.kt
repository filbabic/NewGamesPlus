package com.babic.filip.main.data.model

import com.babic.filip.main.domain.model.Game
import com.babic.filip.networking.data.model.Mappable
import com.google.gson.annotations.SerializedName

class GamesResponse(@SerializedName("body") val games: List<GameResponse> = listOf()) : Mappable<List<Game>> {

    override fun isValid(): Boolean = games.isNotEmpty()

    override fun mapToData(): List<Game> = games.map { it.mapToData() }
}
package com.babic.filip.toprated.data.model

import com.babic.filip.toprated.domain.model.TopRatedGame
import com.babic.filip.networking.data.model.Mappable

class TopRatedGameResponse(private val id: String = "",
                           private val cover: String = "") : Mappable<TopRatedGame> {

    override fun isValid(): Boolean = id.isNotBlank() && cover.isNotBlank()

    override fun mapToData(): TopRatedGame = TopRatedGame(id, cover)
}
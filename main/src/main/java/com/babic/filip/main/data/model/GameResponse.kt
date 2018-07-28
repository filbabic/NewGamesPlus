package com.babic.filip.main.data.model

import com.babic.filip.main.domain.model.Game
import com.babic.filip.networking.data.model.Mappable

class GameResponse(private val id: String = "",
                   private val cover: String = "") : Mappable<Game> {

    override fun isValid(): Boolean = id.isNotBlank() && cover.isNotBlank()

    override fun mapToData(): Game = Game(id, cover)
}
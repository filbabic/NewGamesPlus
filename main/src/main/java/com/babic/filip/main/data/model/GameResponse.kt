package com.babic.filip.main.data.model

import com.babic.filip.main.domain.model.Game
import com.babic.filip.networking.data.model.Mappable

class GameResponse(val id: String = "") : Mappable<Game> {

    override fun isValid(): Boolean = id.isNotBlank()

    override fun mapToData(): Game = Game(id)
}
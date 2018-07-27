package com.babic.filip.main.data.model

import com.babic.filip.main.domain.model.Game
import com.babic.filip.main.domain.model.GameCover
import com.babic.filip.networking.data.model.Mappable
import com.google.gson.annotations.SerializedName

class GameResponse(val id: String = "",
                   val name: String = "",
                   val summary: String = "",
                   val url: String = "",
                   val storyline: String = "",
                   val popularity: Double = 0.0,
                   @SerializedName("total_rating") val totalRating: Double = 0.0,
                   val cover: GameCoverResponse = GameCoverResponse()) : Mappable<Game> {

    override fun isValid(): Boolean = id.isNotBlank() && name.isNotBlank() && summary.isNotBlank() && url.isNotBlank() && storyline.isNotBlank() && popularity != 0.0 && totalRating != 0.0 && cover.url.isNotBlank()

    override fun mapToData(): Game = Game(id, name, summary, url, storyline, popularity, totalRating, GameCover(cover.url))
}
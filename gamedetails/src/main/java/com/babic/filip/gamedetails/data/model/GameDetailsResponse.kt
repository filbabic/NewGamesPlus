package com.babic.filip.gamedetails.data.model

import com.babic.filip.gamedetails.domain.model.GameDetails
import com.babic.filip.networking.data.model.Mappable
import com.google.gson.annotations.SerializedName

class GameDetailsResponse(val id: String = "",
                          val name: String = "",
                          val summary: String = "",
                          val url: String = "",
                          val storyline: String = "",
                          val popularity: Double = 0.0,
                          @SerializedName("total_rating") val totalRating: Double = 0.0,
                          val cover: String = "") : Mappable<GameDetails> {

    override fun isValid(): Boolean = id.isNotBlank() && name.isNotBlank() && summary.isNotBlank() && url.isNotBlank() && popularity != 0.0 && totalRating != 0.0 && cover.isNotBlank()


    override fun mapToData(): GameDetails = GameDetails(id, name, summary, url, storyline, popularity, totalRating, cover)
}
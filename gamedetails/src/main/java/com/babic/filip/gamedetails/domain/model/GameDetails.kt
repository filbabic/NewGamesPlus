package com.babic.filip.gamedetails.domain.model

data class GameDetails(val id: String = "",
                       val name: String = "",
                       val summary: String = "",
                       val url: String = "",
                       val storyline: String = "",
                       val popularity: Double = 0.0,
                       val totalRating: Double = 0.0,
                       val cover: String = "")
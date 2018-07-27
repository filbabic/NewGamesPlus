package com.babic.filip.main.domain.model

data class Game(val id: String,
                val name: String,
                val summary: String,
                val url: String,
                val storyline: String,
                val popularity: Double,
                val totalRating: Double,
                val cover: GameCover)
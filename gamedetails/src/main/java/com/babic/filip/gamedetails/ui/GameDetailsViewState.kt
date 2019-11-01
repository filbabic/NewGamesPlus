package com.babic.filip.gamedetails.ui

class GameDetailsViewState(val id: String,
                           val name: String,
                           val summary: String,
                           val url: String,
                           val storyline: String,
                           val popularity: Double,
                           val totalRating: Double,
                           val cover: String) {

    companion object {
        val EMPTY = GameDetailsViewState("", "", "", "", "", 0.0, 0.0, "")
    }
}
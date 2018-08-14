package com.babic.filip.coreui.routing

interface Router {

    fun showMain()

    fun showLogin()

    fun showRegister()

    fun onUserRegistered()

    fun showTopRated()

    fun showUpcoming()

    fun showFeed()

    fun showMessages()

    fun showMyProfile()

    fun refreshPage()

    fun showGameDetails(gameId: String)
}
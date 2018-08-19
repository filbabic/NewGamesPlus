package com.babic.filip.coreui.routing

interface Router {

    fun showMain()

    fun showLogin()

    fun showRegister()

    fun onUserRegistered()

    fun showGameDetails(gameId: String)
}
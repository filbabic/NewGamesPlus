package com.filip.babic.device.preferences

interface PreferencesHelper {

    fun setLoggedIn(loggedIn: Boolean)

    fun isLoggedIn(): Boolean

    fun setToken(token: String)

    fun getToken(): String
}

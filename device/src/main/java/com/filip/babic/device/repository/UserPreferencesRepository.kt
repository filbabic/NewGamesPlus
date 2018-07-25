package com.filip.babic.device.repository

interface UserPreferencesRepository {

    fun isLoggedIn(): Boolean

    fun setToken(token: String)

    fun getToken(): String
}
package com.filip.babic.domain.repository

interface UserPreferencesRepository {

    fun isLoggedIn(): Boolean

    fun setToken(token: String)

    fun getToken(): String
}
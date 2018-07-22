package com.filip.babic.data.repository

import android.content.SharedPreferences
import com.filip.babic.domain.repository.UserPreferencesRepository

class UserPreferencesRepositoryImpl(private val sharedPreferences: SharedPreferences) : UserPreferencesRepository {

    override fun isLoggedIn(): Boolean = sharedPreferences.getString(KEY_USER_TOKEN, "").isNotBlank()

    override fun getToken(): String = sharedPreferences.getString(KEY_USER_TOKEN, "")
    override fun setToken(token: String) = sharedPreferences.edit().putString(KEY_USER_TOKEN, token).apply()
}

private const val KEY_USER_TOKEN = "user_token"

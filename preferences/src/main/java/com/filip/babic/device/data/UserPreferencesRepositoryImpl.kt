package com.filip.babic.device.data

import android.content.SharedPreferences
import com.filip.babic.device.repository.UserPreferencesRepository

class UserPreferencesRepositoryImpl(private val sharedPreferences: SharedPreferences) : UserPreferencesRepository {

    override fun isLoggedIn(): Boolean = sharedPreferences.getString(KEY_USER_TOKEN, "")?.isNotBlank() == true

    override fun getToken(): String = sharedPreferences.getString(KEY_USER_TOKEN, "") ?: ""
    override fun setToken(token: String) = sharedPreferences.edit().putString(KEY_USER_TOKEN, token).apply()
}

private const val KEY_USER_TOKEN = "user_token"

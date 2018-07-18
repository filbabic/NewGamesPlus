package com.filip.babic.device.preferences

import android.content.SharedPreferences

class SharedPreferencesHelper(private val sharedPreferences: SharedPreferences) : PreferencesHelper {

    override fun isLoggedIn(): Boolean = sharedPreferences.getBoolean(KEY_LOGGED_IN, false)
    override fun setLoggedIn(loggedIn: Boolean) = sharedPreferences.edit().putBoolean(KEY_LOGGED_IN, loggedIn).apply()

    override fun getToken(): String = sharedPreferences.getString(KEY_USER_TOKEN, "")
    override fun setToken(token: String) = sharedPreferences.edit().putString(KEY_USER_TOKEN, token).apply()
}

private const val KEY_USER_TOKEN = "user_token"
private const val KEY_LOGGED_IN = "logged_in"
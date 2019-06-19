package com.filip.babic.device.di

import android.content.Context
import com.filip.babic.device.data.UserPreferencesRepositoryImpl
import com.filip.babic.device.repository.UserPreferencesRepository
import org.koin.dsl.module

private const val KEY_PREFERENCES = "NewGamesPlus-SharedPreferences"

val preferenceModule = module {
    single { get<Context>().getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE) }

    single { UserPreferencesRepositoryImpl(get()) as UserPreferencesRepository }
}
package com.filip.babic.newgamesplus.di.module

import com.filip.babic.data.repository.AuthRepositoryImpl
import com.filip.babic.data.repository.UserPreferencesRepositoryImpl
import com.filip.babic.domain.repository.AuthRepository
import com.filip.babic.domain.repository.UserPreferencesRepository
import org.koin.dsl.module.module

val repositoryModule = module {
    single { AuthRepositoryImpl(get()) as AuthRepository }

    single { UserPreferencesRepositoryImpl(get()) as UserPreferencesRepository }
}
package com.filip.babic.newgamesplus.di.module

import com.filip.babic.data.repository.AuthRepositoryImpl
import org.koin.dsl.module.module

val repositoryModule = module {
    single { AuthRepositoryImpl(get()) }
}
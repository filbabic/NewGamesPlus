package com.filip.babic.newgamesplus.di.module

import com.filip.babic.domain.interaction.GetUserLoggedInUseCase
import com.filip.babic.domain.interaction.RegisterUserUseCase
import com.filip.babic.domain.interaction.SaveUserLoginUseCase
import org.koin.dsl.module.module

val useCaseModule = module {

    single { GetUserLoggedInUseCase(get()) }
    single { RegisterUserUseCase(get()) }
    single { SaveUserLoginUseCase(get()) }
}
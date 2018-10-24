package com.babic.filip.splash.di

import com.babic.filip.splash.domain.interaction.GetUserLoggedInUseCase
import com.babic.filip.splash.ui.SPLASH_SCOPE
import com.babic.filip.splash.ui.SplashPresenter
import org.koin.dsl.module.module

val splashModule = module {

    scope(SPLASH_SCOPE) { GetUserLoggedInUseCase(get()) }

    scope(SPLASH_SCOPE) { SplashPresenter(get()) }
}
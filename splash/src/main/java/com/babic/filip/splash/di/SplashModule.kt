package com.babic.filip.splash.di

import com.babic.filip.core.di.DEFAULT_CONTEXT
import com.babic.filip.splash.domain.interaction.GetUserLoggedInUseCase
import com.babic.filip.splash.ui.SPLASH_SCOPE
import com.babic.filip.splash.ui.SplashPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val splashModule = module {

    scope(named(SPLASH_SCOPE)) {

        scoped { GetUserLoggedInUseCase(get()) }

        scoped { SplashPresenter(get(named(DEFAULT_CONTEXT)), get()) }
    }
}
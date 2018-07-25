package com.babic.filip.splash.di

import com.babic.filip.core.di.LIVE_CONTEXT
import com.babic.filip.splash.domain.interaction.GetUserLoggedInUseCase
import com.babic.filip.splash.ui.SPLASH_SCOPE
import com.babic.filip.splash.ui.SplashViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val splashModule = module(SPLASH_SCOPE) {

    single { GetUserLoggedInUseCase(get()) }

    viewModel { SplashViewModel(get(LIVE_CONTEXT), get()) }
}
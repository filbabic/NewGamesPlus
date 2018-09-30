package com.babic.filip.splash.di

import com.babic.filip.splash.domain.interaction.GetUserLoggedInUseCase
import com.babic.filip.splash.ui.SPLASH_SCOPE
import com.babic.filip.splash.ui.SplashViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val splashModule = module {

    scope(SPLASH_SCOPE) { GetUserLoggedInUseCase(get()) }

    viewModel { SplashViewModel(get()) }
}
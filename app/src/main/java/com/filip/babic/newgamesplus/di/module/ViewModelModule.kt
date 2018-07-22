package com.filip.babic.newgamesplus.di.module

import com.babic.filip.login.LOGIN_SCOPE
import com.babic.filip.login.LoginViewModel
import com.babic.filip.register.REGISTER_SCOPE
import com.babic.filip.register.RegisterViewModel
import com.babic.filip.splash.SPLASH_SCOPE
import com.babic.filip.splash.SplashViewModel
import com.filip.babic.data.di.LIVE_CONTEXT
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {

    module(SPLASH_SCOPE) { viewModel { SplashViewModel(get(LIVE_CONTEXT), get()) } }

    module(LOGIN_SCOPE) { viewModel { LoginViewModel(get(LIVE_CONTEXT)) } }

    module(REGISTER_SCOPE) { viewModel { RegisterViewModel(get(LIVE_CONTEXT), get(), get()) } }
}
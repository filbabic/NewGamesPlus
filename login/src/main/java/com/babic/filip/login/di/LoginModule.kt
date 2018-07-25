package com.babic.filip.login.di

import com.babic.filip.core.di.LIVE_CONTEXT
import com.babic.filip.login.ui.LOGIN_SCOPE
import com.babic.filip.login.ui.LoginViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val loginModule = module(LOGIN_SCOPE) {

    viewModel { LoginViewModel(get(LIVE_CONTEXT)) }
}
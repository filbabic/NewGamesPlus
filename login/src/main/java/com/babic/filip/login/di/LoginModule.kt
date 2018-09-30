package com.babic.filip.login.di

import com.babic.filip.login.ui.LoginViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val loginModule = module {

    viewModel { LoginViewModel() }
}
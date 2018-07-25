package com.babic.filip.main.di

import com.babic.filip.main.ui.MAIN_SCOPE
import com.babic.filip.main.ui.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val mainModule = module(MAIN_SCOPE) {

    viewModel { MainViewModel() }
}
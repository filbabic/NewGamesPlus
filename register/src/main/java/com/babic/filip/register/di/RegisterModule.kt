package com.babic.filip.register.di

import com.babic.filip.core.di.LIVE_CONTEXT
import com.babic.filip.register.data.networking.RegisterApiService
import com.babic.filip.register.data.repository.RegisterRepositoryImpl
import com.babic.filip.register.domain.interaction.RegisterUserUseCase
import com.babic.filip.register.domain.interaction.SaveUserLoginUseCase
import com.babic.filip.register.domain.repository.RegisterRepository
import com.babic.filip.register.ui.REGISTER_SCOPE
import com.babic.filip.register.ui.RegisterViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val registerModule = module(REGISTER_SCOPE) {

    single { RegisterUserUseCase(get()) }
    single { SaveUserLoginUseCase(get()) }

    single { get<Retrofit>().create(RegisterApiService::class.java) }

    single { RegisterRepositoryImpl(get()) as RegisterRepository }

    viewModel { RegisterViewModel(get(LIVE_CONTEXT), get(), get()) }
}
package com.babic.filip.register.di

import com.babic.filip.register.data.networking.RegisterApiService
import com.babic.filip.register.data.repository.RegisterRepositoryImpl
import com.babic.filip.register.domain.interaction.RegisterUserUseCase
import com.babic.filip.register.domain.interaction.SaveUserTokenUseCase
import com.babic.filip.register.domain.repository.RegisterRepository
import com.babic.filip.register.ui.REGISTER_SCOPE
import com.babic.filip.register.ui.RegisterPresenter
import org.koin.dsl.module.module
import retrofit2.Retrofit

val registerModule = module {

    scope(REGISTER_SCOPE) { RegisterUserUseCase(get()) }
    scope(REGISTER_SCOPE) { SaveUserTokenUseCase(get()) }

    scope(REGISTER_SCOPE) { get<Retrofit>().create(RegisterApiService::class.java) }

    scope(REGISTER_SCOPE) { RegisterRepositoryImpl(get()) as RegisterRepository }

    scope(REGISTER_SCOPE) { RegisterPresenter(get(), get()) }
}
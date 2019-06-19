package com.babic.filip.register.di

import com.babic.filip.register.data.networking.RegisterApiService
import com.babic.filip.register.data.repository.RegisterRepositoryImpl
import com.babic.filip.register.domain.interaction.RegisterUserUseCase
import com.babic.filip.register.domain.interaction.SaveUserTokenUseCase
import com.babic.filip.register.domain.repository.RegisterRepository
import com.babic.filip.register.ui.REGISTER_SCOPE
import com.babic.filip.register.ui.RegisterPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val registerModule = module {

    scope(named(REGISTER_SCOPE)) {
        scoped { RegisterUserUseCase(get()) }

        scoped { SaveUserTokenUseCase(get()) }

        scoped { get<Retrofit>().create(RegisterApiService::class.java) }

        scoped { RegisterRepositoryImpl(get()) as RegisterRepository }

        scoped { RegisterPresenter(get(), get()) }
    }
}
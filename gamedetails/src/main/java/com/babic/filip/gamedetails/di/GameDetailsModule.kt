package com.babic.filip.gamedetails.di

import com.babic.filip.gamedetails.data.networking.GameDetailsApiService
import com.babic.filip.gamedetails.data.repository.GameDetailsRepositoryImpl
import com.babic.filip.gamedetails.domain.interaction.GetGameDetailsUseCase
import com.babic.filip.gamedetails.domain.repository.GameDetailsRepository
import com.babic.filip.gamedetails.ui.GAME_DETAILS_SCOPE
import com.babic.filip.gamedetails.ui.GameDetailsPresenter
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val gameDetailsModule = module {

    scope(named(GAME_DETAILS_SCOPE)) {

        scoped { get<Retrofit>().create(GameDetailsApiService::class.java) }
        scoped { GameDetailsRepositoryImpl(get()) as GameDetailsRepository }
        scoped { GetGameDetailsUseCase(get()) }
        scoped { GameDetailsPresenter(get()) }
    }
}
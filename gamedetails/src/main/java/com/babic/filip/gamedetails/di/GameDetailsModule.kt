package com.babic.filip.gamedetails.di

import com.babic.filip.gamedetails.data.networking.GameDetailsApiService
import com.babic.filip.gamedetails.data.repository.GameDetailsRepositoryImpl
import com.babic.filip.gamedetails.domain.interaction.GetGameDetailsUseCase
import com.babic.filip.gamedetails.domain.repository.GameDetailsRepository
import com.babic.filip.gamedetails.ui.GAME_DETAILS_SCOPE
import com.babic.filip.gamedetails.ui.GameDetailsViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val gameDetailsModule = module(GAME_DETAILS_SCOPE) {

    single { get<Retrofit>().create(GameDetailsApiService::class.java) }
    single { GameDetailsRepositoryImpl(get()) as GameDetailsRepository }
    single { GetGameDetailsUseCase(get()) }

    viewModel { GameDetailsViewModel(get()) }
}
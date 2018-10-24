package com.babic.filip.gamedetails.di

import com.babic.filip.gamedetails.data.networking.GameDetailsApiService
import com.babic.filip.gamedetails.data.repository.GameDetailsRepositoryImpl
import com.babic.filip.gamedetails.domain.interaction.GetGameDetailsUseCase
import com.babic.filip.gamedetails.domain.repository.GameDetailsRepository
import com.babic.filip.gamedetails.ui.GAME_DETAILS_SCOPE
import com.babic.filip.gamedetails.ui.GameDetailsPresenter
import org.koin.dsl.module.module
import retrofit2.Retrofit

val gameDetailsModule = module {

    scope(GAME_DETAILS_SCOPE) { get<Retrofit>().create(GameDetailsApiService::class.java) }
    scope(GAME_DETAILS_SCOPE) { GameDetailsRepositoryImpl(get()) as GameDetailsRepository }
    scope(GAME_DETAILS_SCOPE) { GetGameDetailsUseCase(get()) }

    scope(GAME_DETAILS_SCOPE) { GameDetailsPresenter(get()) }
}
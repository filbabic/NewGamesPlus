package com.babic.filip.gamedetails.di

import com.babic.filip.core.di.DEFAULT_CONTEXT
import com.babic.filip.gamedetails.data.networking.GameDetailsApiService
import com.babic.filip.gamedetails.data.repository.GameDetailsRepositoryImpl
import com.babic.filip.gamedetails.domain.interaction.GetGameDetailsUseCase
import com.babic.filip.gamedetails.domain.repository.GameDetailsRepository
import com.babic.filip.gamedetails.ui.GAME_DETAILS_SCOPE
import com.babic.filip.gamedetails.ui.GameDetailsPresenter
import com.babic.filip.gamedetails.ui.GameDetailsViewStateMapper
import com.babic.filip.gamedetails.ui.GameDetailsViewStateMapperImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val gameDetailsModule = module {

    scope(named(GAME_DETAILS_SCOPE)) {

        scoped { get<Retrofit>().create(GameDetailsApiService::class.java) }
        scoped { GameDetailsRepositoryImpl(get()) as GameDetailsRepository }
        scoped { GetGameDetailsUseCase(get()) }
        scoped { GameDetailsViewStateMapperImpl() as GameDetailsViewStateMapper }
        scoped { GameDetailsPresenter(get(named(DEFAULT_CONTEXT)), get(), get()) }
    }
}
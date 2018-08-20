package com.babic.filip.toprated.di

import com.babic.filip.toprated.data.network.TopRatedGamesApiService
import com.babic.filip.toprated.data.repository.TopRatedGamesRepositoryImpl
import com.babic.filip.toprated.domain.interaction.GetTopRatedGamesUseCase
import com.babic.filip.toprated.domain.repository.TopRatedGamesRepository
import com.babic.filip.toprated.ui.TOP_RATED_GAMES_SCOPE
import com.babic.filip.toprated.ui.TopRatedGamesViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val topRatedGamesModule = module(TOP_RATED_GAMES_SCOPE) {
    single { get<Retrofit>().create(TopRatedGamesApiService::class.java) }
    single { TopRatedGamesRepositoryImpl(get()) as TopRatedGamesRepository }
    single { GetTopRatedGamesUseCase(get()) }

    viewModel { TopRatedGamesViewModel(get()) }
}
package com.babic.filip.main.di

import com.babic.filip.main.data.network.GamesApiService
import com.babic.filip.main.data.repository.GamesRepositoryImpl
import com.babic.filip.main.domain.interaction.GetTopRatedGamesUseCase
import com.babic.filip.main.domain.repository.GamesRepository
import com.babic.filip.main.ui.MAIN_SCOPE
import com.babic.filip.main.ui.MainViewModel
import com.babic.filip.main.ui.topRated.TOP_RATED_GAMES_SCOPE
import com.babic.filip.main.ui.topRated.TopRatedGamesViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val mainModule = module(MAIN_SCOPE) {

    single { get<Retrofit>().create(GamesApiService::class.java) }
    single { GamesRepositoryImpl(get()) as GamesRepository }
    single { GetTopRatedGamesUseCase(get()) }

    viewModel { MainViewModel() }

    module(TOP_RATED_GAMES_SCOPE) { viewModel { TopRatedGamesViewModel(get()) } }
}
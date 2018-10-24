package com.babic.filip.toprated.di

import com.babic.filip.toprated.data.network.TopRatedGamesApiService
import com.babic.filip.toprated.data.repository.TopRatedGamesRepositoryImpl
import com.babic.filip.toprated.domain.interaction.GetTopRatedGamesUseCase
import com.babic.filip.toprated.domain.repository.TopRatedGamesRepository
import com.babic.filip.toprated.ui.TOP_RATED_GAMES_SCOPE
import com.babic.filip.toprated.ui.TopRatedGamesPresenter
import org.koin.dsl.module.module
import retrofit2.Retrofit

val topRatedGamesModule = module {

    scope(TOP_RATED_GAMES_SCOPE) { get<Retrofit>().create(TopRatedGamesApiService::class.java) }
    scope(TOP_RATED_GAMES_SCOPE) { TopRatedGamesRepositoryImpl(get()) as TopRatedGamesRepository }
    scope(TOP_RATED_GAMES_SCOPE) { GetTopRatedGamesUseCase(get()) }

    scope(TOP_RATED_GAMES_SCOPE) { TopRatedGamesPresenter(get()) }
}
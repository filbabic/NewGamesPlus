package com.babic.filip.toprated.di

import com.babic.filip.toprated.data.network.TopRatedGamesApiService
import com.babic.filip.toprated.data.repository.TopRatedGamesRepositoryImpl
import com.babic.filip.toprated.domain.interaction.GetTopRatedGamesUseCase
import com.babic.filip.toprated.domain.repository.TopRatedGamesRepository
import com.babic.filip.toprated.ui.TOP_RATED_GAMES_SCOPE
import com.babic.filip.toprated.ui.TopRatedGamesPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val topRatedGamesModule = module {

    scope(named(TOP_RATED_GAMES_SCOPE)) {
        scoped { get<Retrofit>().create(TopRatedGamesApiService::class.java) }
        scoped { TopRatedGamesRepositoryImpl(get()) as TopRatedGamesRepository }
        scoped { GetTopRatedGamesUseCase(get()) }

        scoped { TopRatedGamesPresenter(get()) }
    }
}
package com.filip.babic.data.di

import com.filip.babic.data.coroutineContext.CoroutineContextProvider
import com.filip.babic.data.coroutineContext.CoroutineContextProviderImpl
import com.filip.babic.data.coroutineContext.TestCoroutineContextProviderImpl
import com.filip.babic.data.networking.service.AuthApiService
import com.filip.babic.domain.repository.UserPreferencesRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL: String = "http://api.openweathermap.org/"

const val TEST_CONTEXT = "TEST_CONTEXT_PROVIDER"
const val LIVE_CONTEXT = "LIVE_CONTEXT_PROVIDER"

private const val KEY_AUTHORIZATION = "authorization"

fun networkingModule(isDebug: Boolean) = module {
    single { BASE_URL }

    single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }

    single {
        val interceptor = Interceptor {
            val preferences = get<UserPreferencesRepository>()

            val request = it.request()

            val auth = request.newBuilder()
                    .addHeader(KEY_AUTHORIZATION, preferences.getToken())
                    .build()

            it.proceed(auth)
        }

        interceptor
    }

    single {
        OkHttpClient.Builder().apply {
            if (isDebug) {
                addInterceptor(get<HttpLoggingInterceptor>())
            }

            addInterceptor(get())
        }.build()
    }

    single { GsonConverterFactory.create() }

    single {
        Retrofit.Builder()
                .baseUrl(get<String>())
                .client(get())
                .addConverterFactory(get<GsonConverterFactory>())
                .build()
    }

    single { get<Retrofit>().create(AuthApiService::class.java) }

    single(TEST_CONTEXT) { TestCoroutineContextProviderImpl() as CoroutineContextProvider }
    single(LIVE_CONTEXT) { CoroutineContextProviderImpl() as CoroutineContextProvider }
}
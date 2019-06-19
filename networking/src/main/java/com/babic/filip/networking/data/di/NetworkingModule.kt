package com.babic.filip.networking.data.di

import com.filip.babic.device.repository.UserPreferencesRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL: String = "https://api-endpoint.igdb.com" //todo change this to actual server endpoint
private const val BASE_URL_DEBUG: String = "http://10.0.2.2:3000"

private const val KEY_AUTHORIZATION = "authorization"

private const val TIMEOUT = 10L

fun networkingModule(isDebug: Boolean) = module {
    single { if (isDebug) BASE_URL_DEBUG else BASE_URL }

    single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }

    single {
        val interceptor = Interceptor { chain ->
            val preferences = get<UserPreferencesRepository>()

            val request = chain.request()

            val auth = request.newBuilder()
                    .addHeader(KEY_AUTHORIZATION, preferences.getToken())
                    .build()

            chain.proceed(auth)
        }

        interceptor
    }

    single {
        OkHttpClient.Builder().apply {
            if (isDebug) {
                addInterceptor(get<HttpLoggingInterceptor>())
            }

            addInterceptor(get())
        }.writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    single { GsonConverterFactory.create() }

    single {
        Retrofit.Builder()
                .baseUrl(get<String>())
                .client(get())
                .addConverterFactory(get<GsonConverterFactory>())
                .build()
    }
}
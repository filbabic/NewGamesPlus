package com.filip.babic.newgamesplus.di

import com.babic.filip.core.coroutineContext.CoroutineContextProvider
import com.babic.filip.core.coroutineContext.CoroutineContextProviderImpl
import org.koin.dsl.module

val appModule = module {
    single { CoroutineContextProviderImpl() as CoroutineContextProvider }
}
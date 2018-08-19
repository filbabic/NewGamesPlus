package com.babic.filip.core.di

import com.babic.filip.core.coroutineContext.CoroutineContextProviderImpl
import com.babic.filip.core.coroutineContext.TestCoroutineContextProviderImpl
import org.koin.dsl.module.module

val coreModule = module {

    single { TestCoroutineContextProviderImpl() }
    single { CoroutineContextProviderImpl() }
}
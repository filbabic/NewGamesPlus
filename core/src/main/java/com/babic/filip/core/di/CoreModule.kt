package com.babic.filip.core.di

import com.babic.filip.core.coroutineContext.CoroutineContextProvider
import com.babic.filip.core.coroutineContext.CoroutineContextProviderImpl
import com.babic.filip.core.coroutineContext.TestCoroutineContextProviderImpl
import org.koin.dsl.module.module

const val TEST_CONTEXT = "TEST_CONTEXT_PROVIDER"
const val LIVE_CONTEXT = "LIVE_CONTEXT_PROVIDER"

val coreModule = module {

    single(TEST_CONTEXT) { TestCoroutineContextProviderImpl() as CoroutineContextProvider }
    single(LIVE_CONTEXT) { CoroutineContextProviderImpl() as CoroutineContextProvider }
}
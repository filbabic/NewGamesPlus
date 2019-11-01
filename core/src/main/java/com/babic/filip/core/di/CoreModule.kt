package com.babic.filip.core.di

import com.babic.filip.core.coroutineContext.CoroutineContextProviderImpl
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coreModule = module {

    single(named(DEFAULT_CONTEXT)) { CoroutineContextProviderImpl(Dispatchers.Main) }
    single(named(BACKGROUND_CONTEXT)) { CoroutineContextProviderImpl(Dispatchers.Default) }
    single(named(IO_CONTEXT)) { CoroutineContextProviderImpl(Dispatchers.IO) }
}

const val DEFAULT_CONTEXT = "Default Context"
const val BACKGROUND_CONTEXT = "Background Context"
const val IO_CONTEXT = "I/O Context"
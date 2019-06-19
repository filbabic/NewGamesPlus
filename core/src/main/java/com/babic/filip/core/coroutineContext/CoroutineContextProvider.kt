package com.babic.filip.core.coroutineContext

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlin.coroutines.CoroutineContext

class CoroutineContextProviderImpl : CoroutineContextProvider {

    override val io: CoroutineContext by lazy { Default }
    override val main: CoroutineContext by lazy { Dispatchers.Main }
}

class TestCoroutineContextProviderImpl : CoroutineContextProvider {

    override val io: CoroutineContext by lazy { Unconfined }
    override val main: CoroutineContext by lazy { Unconfined }
}

interface CoroutineContextProvider {

    val main: CoroutineContext

    val io: CoroutineContext
}


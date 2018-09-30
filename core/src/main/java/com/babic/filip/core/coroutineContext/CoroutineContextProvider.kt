package com.babic.filip.core.coroutineContext

import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Dispatchers.Default
import kotlinx.coroutines.experimental.Dispatchers.Unconfined
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

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


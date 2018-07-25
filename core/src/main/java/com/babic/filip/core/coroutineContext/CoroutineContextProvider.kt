package com.babic.filip.core.coroutineContext

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Unconfined
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

class CoroutineContextProviderImpl : CoroutineContextProvider {

    override val io: CoroutineContext by lazy { CommonPool }
    override val main: CoroutineContext by lazy { UI }
}

class TestCoroutineContextProviderImpl : CoroutineContextProvider {

    override val io: CoroutineContext by lazy { Unconfined }
    override val main: CoroutineContext by lazy { Unconfined }
}

interface CoroutineContextProvider {

    val main: CoroutineContext

    val io: CoroutineContext
}


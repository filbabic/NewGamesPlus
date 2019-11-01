package com.babic.filip.core.coroutineContext

import kotlin.coroutines.CoroutineContext

class CoroutineContextProviderImpl(override val context: CoroutineContext) : CoroutineContextProvider

interface CoroutineContextProvider {

    val context: CoroutineContext
}


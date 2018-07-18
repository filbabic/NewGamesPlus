package com.filip.babic.data.api.client

import com.filip.babic.data.coroutineContext.CoroutineContextProvider
import com.filip.babic.data.coroutineContext.CoroutineContextProviderImpl
import com.filip.babic.domain.model.result.Result
import kotlinx.coroutines.experimental.withContext

object ApiClient {

    private val contextProvider: CoroutineContextProvider by lazy { CoroutineContextProviderImpl() }

    suspend fun <T : Any> processRequest(dataProvider: suspend () -> Result<T>): Result<T> {
        return withContext(contextProvider.io) { dataProvider() }
    }
}
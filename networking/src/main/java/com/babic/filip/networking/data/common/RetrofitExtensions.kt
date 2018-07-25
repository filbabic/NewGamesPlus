package com.babic.filip.networking.data.common

import com.babic.filip.networking.data.error.ApiDataTransformationException
import com.babic.filip.networking.data.error.AuthenticationError
import com.babic.filip.networking.data.error.NetworkException
import com.babic.filip.networking.data.error.ServerError
import com.babic.filip.networking.data.model.Failure
import com.babic.filip.networking.data.model.Mappable
import com.babic.filip.networking.data.model.Result
import com.babic.filip.networking.data.model.Success
import com.google.gson.JsonParseException
import kotlinx.coroutines.experimental.delay
import retrofit2.Call
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.util.concurrent.TimeUnit
import kotlin.coroutines.experimental.suspendCoroutine

private const val DEFAULT_RETRY_ATTEMPTS = 3
private const val REPEAT_DELAY = 10L

suspend fun <T : Mappable<R>, R : Any> Call<T>.getResult(): Result<R> {
    val callWrapper: () -> Result<R>? = {
        val call = clone()

        try {
            val response = call.execute()

            val result = response?.body()?.run { if (isValid()) Success(mapToData()) else Failure(mapError(HttpException(response))) }
            val errorResult = response?.errorBody()?.run { Failure(mapError(HttpException(response))) }

            result ?: errorResult
        } catch (error: Throwable) {
            Failure(mapError(error))
        }
    }

    val dataProvider: suspend () -> Result<R> = {
        suspendCoroutine { continuation ->
            val data = callWrapper()

            data?.run { continuation.resume(this) }
        }
    }

    val dataInvalidator: (Result<R>) -> Boolean = { data -> data is Failure && (data.error === NetworkException || data.error == ServerError) }

    repeat(DEFAULT_RETRY_ATTEMPTS - 1) {
        delay(REPEAT_DELAY, TimeUnit.SECONDS)
        //first we try to run the data two times, if it's okay, we return it
        val data = dataProvider()

        if (!dataInvalidator(data)) {
            return data
        }
    }

    return dataProvider() //final attempt
}

private val serverErrorCodes = 500..600
private val authenticationErrorCodes = 400..499

private fun mapError(error: Throwable?): Throwable? = when {
    error is JsonParseException -> ApiDataTransformationException
    error is IOException -> NetworkException
    error is ConnectException -> NetworkException
    error is HttpException && error.code() in serverErrorCodes -> ServerError
    error is HttpException && error.code() in authenticationErrorCodes -> AuthenticationError
    else -> error
}
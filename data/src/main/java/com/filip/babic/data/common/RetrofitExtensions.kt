package com.filip.babic.data.common

import com.filip.babic.data.networking.error.ApiDataTransformationException
import com.filip.babic.data.networking.error.AuthenticationError
import com.filip.babic.data.networking.error.NetworkException
import com.filip.babic.data.networking.error.ServerError
import com.filip.babic.domain.model.result.Failure
import com.filip.babic.domain.model.result.Mappable
import com.filip.babic.domain.model.result.Result
import com.filip.babic.domain.model.result.Success
import com.google.gson.JsonParseException
import retrofit2.Call
import retrofit2.HttpException
import java.io.IOException
import kotlin.coroutines.experimental.suspendCoroutine

private const val DEFAULT_RETRY_ATTEMPTS = 3

suspend fun <T : Mappable<R>, R : Any> Call<T>.getResult(): Result<R> {
    val dataProvider: suspend () -> Result<R> = {
        suspendCoroutine { continuation ->
            try {
                val response = execute()

                response?.body()?.run {
                    val result = if (isValid()) Success(mapToData()) else Failure(ApiDataTransformationException)

                    continuation.resume(result)
                }
            } catch (error: Throwable) {
                continuation.resume(Failure(mapError(error)))
            }
        }
    }

    val dataInvalidator: (Result<R>) -> Boolean = { data -> data is Failure && (data.error == NetworkException || data.error == ServerError) }

    repeat(DEFAULT_RETRY_ATTEMPTS - 1) { //first we try to run the data two times, if it's okay, we return it
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
    error is HttpException && error.code() in serverErrorCodes -> ServerError
    error is HttpException && error.code() in authenticationErrorCodes -> AuthenticationError
    else -> error
}
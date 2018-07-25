package com.babic.filip.networking.data.model

sealed class Result<out T : Any>

data class Success<out T : Any>(val data: T) : Result<T>()

data class Failure(val error: Throwable?) : Result<Nothing>()

fun <T : Any> Result<T>.doOnSuccess(consumer: (T) -> Unit): Result<T> {
    if (this is Success) consumer(data)

    return this
}

fun <T : Any> Result<T>.doOnError(consumer: (Throwable?) -> Unit) {
    if (this is Failure) consumer(error)
}

interface Mappable<out T : Any> {

    fun isValid(): Boolean

    fun mapToData(): T
}

package com.babic.filip.core.domain.interaction

interface GetUseCase<Data : Any> {

    suspend fun get(): Data
}
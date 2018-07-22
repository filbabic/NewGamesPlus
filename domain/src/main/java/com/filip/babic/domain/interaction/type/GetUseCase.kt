package com.filip.babic.domain.interaction.type

interface GetUseCase<Data : Any> {

    suspend fun get(): Data
}
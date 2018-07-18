package com.filip.babic.domain.interaction.type

import com.filip.babic.domain.model.result.Result

interface GetUseCase<Data : Any> {

    suspend fun get(): Result<Data>
}
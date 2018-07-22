package com.filip.babic.domain.interaction.type

import com.filip.babic.domain.model.result.Result

interface GetUseCaseWithParam<Param, Data : Any> {

    suspend fun run(param: Param): Result<Data>
}
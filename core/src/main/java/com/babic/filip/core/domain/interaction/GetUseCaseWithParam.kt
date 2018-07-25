package com.babic.filip.core.domain.interaction

interface GetUseCaseWithParam<Param, Data : Any> {

    suspend fun run(param: Param): Data
}
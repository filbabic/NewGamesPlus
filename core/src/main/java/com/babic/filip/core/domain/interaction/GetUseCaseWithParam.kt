package com.babic.filip.core.domain.interaction

interface GetUseCaseWithParam<Param, Data : Any> {

    suspend operator fun invoke(param: Param): Data
}
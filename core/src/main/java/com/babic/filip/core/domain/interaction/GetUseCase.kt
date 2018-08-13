package com.babic.filip.core.domain.interaction

interface GetUseCase<Data : Any> {

    suspend operator fun invoke(): Data
}
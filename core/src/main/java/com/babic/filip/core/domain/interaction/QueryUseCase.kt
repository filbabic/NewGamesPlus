package com.babic.filip.core.domain.interaction

import kotlinx.coroutines.channels.Channel

interface QueryUseCase<Result> {

    suspend operator fun invoke(): Channel<Result>
}
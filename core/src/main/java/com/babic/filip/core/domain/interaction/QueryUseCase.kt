package com.babic.filip.core.domain.interaction

import kotlinx.coroutines.experimental.channels.Channel

interface QueryUseCase<Result> {

    suspend operator fun invoke(): Channel<Result>
}
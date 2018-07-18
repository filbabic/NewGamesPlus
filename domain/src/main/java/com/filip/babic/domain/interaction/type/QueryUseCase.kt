package com.filip.babic.domain.interaction.type

import kotlinx.coroutines.experimental.channels.Channel

interface QueryUseCase<Result> {

    fun run(): Channel<Result>
}
package com.babic.filip.core.domain.interaction

import kotlinx.coroutines.experimental.channels.Channel

interface CommandUseCase {

    fun execute(): Channel<Unit>
}
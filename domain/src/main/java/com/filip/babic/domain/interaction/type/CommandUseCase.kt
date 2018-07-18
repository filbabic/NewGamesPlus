package com.filip.babic.domain.interaction.type

import kotlinx.coroutines.experimental.channels.Channel

interface CommandUseCase {

    fun execute(): Channel<Unit>
}
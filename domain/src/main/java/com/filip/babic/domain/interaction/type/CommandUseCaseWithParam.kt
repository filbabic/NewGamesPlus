package com.filip.babic.domain.interaction.type

import kotlinx.coroutines.experimental.channels.Channel

interface CommandUseCaseWithParam<in Param> {

    fun execute(param: Param): Channel<Unit>
}
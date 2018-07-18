package com.filip.babic.domain.interaction.type

import kotlinx.coroutines.experimental.channels.Channel

interface QueryUseCaseWithParam<Param, Result> {

    fun run(param: Param): Channel<Result>
}
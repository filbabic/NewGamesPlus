package com.babic.filip.core.domain.interaction

import kotlinx.coroutines.experimental.channels.Channel

interface QueryUseCaseWithParam<Param, Result> {

    fun run(param: Param): Channel<Result>
}
package com.babic.filip.core.domain.interaction

import kotlinx.coroutines.channels.Channel

interface QueryUseCaseWithParam<Param, Result> {

    suspend operator fun invoke(param: Param): Channel<Result>
}
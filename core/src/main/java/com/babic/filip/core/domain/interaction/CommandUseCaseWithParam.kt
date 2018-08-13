package com.babic.filip.core.domain.interaction

interface CommandUseCaseWithParam<in Param> {

    operator fun invoke(param: Param)
}
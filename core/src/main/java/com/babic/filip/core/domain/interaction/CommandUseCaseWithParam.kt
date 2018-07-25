package com.babic.filip.core.domain.interaction

interface CommandUseCaseWithParam<in Param> {

    fun execute(param: Param)
}
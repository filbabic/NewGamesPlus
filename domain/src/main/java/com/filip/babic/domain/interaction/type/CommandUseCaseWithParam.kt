package com.filip.babic.domain.interaction.type

interface CommandUseCaseWithParam<in Param> {

    fun execute(param: Param)
}
package com.filip.babic.domain.validation

interface Validator<T : Any> {

    fun isValid(data: T): Boolean
}
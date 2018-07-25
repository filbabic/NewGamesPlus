package com.babic.filip.core.validation

interface Validator<T : Any> {

    fun isValid(data: T): Boolean
}
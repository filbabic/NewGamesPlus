package com.babic.filip.register

import com.babic.filip.core.error.EmailError
import com.babic.filip.core.error.PasswordError
import com.babic.filip.core.error.UsernameError

data class RegisterViewState(
        var usernameError: UsernameError? = null,
        var emailError: EmailError? = null,
        var passwordError: PasswordError? = null,
        var isValidData: Boolean = false)
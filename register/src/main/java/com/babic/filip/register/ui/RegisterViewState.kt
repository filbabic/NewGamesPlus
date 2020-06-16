package com.babic.filip.register.ui

import com.babic.filip.register.ui.error.EmailError
import com.babic.filip.register.ui.error.PasswordError
import com.babic.filip.register.ui.error.UsernameError

data class RegisterViewState(
        var usernameError: UsernameError? = null,
        var emailError: EmailError? = null,
        var passwordError: PasswordError? = null,
        var isValidData: Boolean = false,
        var isTermsChecked: Boolean)
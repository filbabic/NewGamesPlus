package com.babic.filip.login.ui

import com.babic.filip.login.ui.error.EmailError
import com.babic.filip.login.ui.error.PasswordError

data class LoginViewState(val emailError: EmailError? = null,
                          val passwordError: PasswordError? = null,
                          val isValidData: Boolean = false)
package com.babic.filip.core.validation

import com.babic.filip.core.error.EmailError
import com.babic.filip.core.error.PasswordError
import com.babic.filip.core.error.UsernameError
import java.util.regex.Pattern

private const val USERNAME_MIN_LENGTH = 6
private const val PASSWORD_MIN_LENGTH = 10

fun getUsernameError(username: String) = when {
    username.isBlank() -> UsernameError.Empty
    username.length < USERNAME_MIN_LENGTH -> UsernameError.Invalid
    else -> null
}

private const val EMAIL_REGEX = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"

fun getEmailError(email: String) = when {
    email.isBlank() -> EmailError.Empty
    !Pattern.matches(EMAIL_REGEX, email) -> EmailError.Invalid
    else -> null
}

fun getPasswordError(password: String) = when {
    password.isBlank() -> PasswordError.Empty
    password.length < PASSWORD_MIN_LENGTH -> PasswordError.Invalid
    else -> null
}
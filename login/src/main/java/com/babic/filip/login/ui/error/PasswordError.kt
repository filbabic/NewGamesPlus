package com.babic.filip.login.ui.error

import com.babic.filip.coreui.error.BaseError
import com.babic.filip.login.R

sealed class PasswordError(errorText: Int? = null) : BaseError(errorText) {

    object Empty : PasswordError(R.string.password_empty_error)
    object Invalid : PasswordError(R.string.password_invalid_error)
}
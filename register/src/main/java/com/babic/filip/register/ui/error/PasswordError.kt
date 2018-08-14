package com.babic.filip.register.ui.error

import android.support.annotation.StringRes
import com.babic.filip.coreui.error.BaseError
import com.babic.filip.register.R

sealed class PasswordError(@StringRes errorText: Int? = null) : BaseError(errorText) {

    object Empty : PasswordError(R.string.password_empty_error)
    object Invalid : PasswordError(R.string.password_invalid_error)
}
package com.babic.filip.core.error

import android.support.annotation.StringRes
import com.babic.filip.core.R

sealed class PasswordError(@StringRes errorText: Int? = null) : BaseError(errorText) {

    object Empty : PasswordError(R.string.password_empty_error)
    object Invalid :PasswordError(R.string.password_invalid_error)
}
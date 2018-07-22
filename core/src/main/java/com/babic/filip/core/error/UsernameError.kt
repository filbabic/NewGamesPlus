package com.babic.filip.core.error

import android.support.annotation.StringRes
import com.babic.filip.core.R

sealed class UsernameError(@StringRes errorText: Int? = null) : BaseError(errorText) {

    object Empty : UsernameError(R.string.username_empty_error)
    object Invalid : UsernameError(R.string.username_invalid_error)
}

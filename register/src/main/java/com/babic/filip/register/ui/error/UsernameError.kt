package com.babic.filip.register.ui.error

import android.support.annotation.StringRes
import com.babic.filip.coreui.error.BaseError
import com.babic.filip.register.R

sealed class UsernameError(@StringRes errorText: Int? = null) : BaseError(errorText) {

    object Empty : UsernameError(R.string.username_empty_error)
    object Invalid : UsernameError(R.string.username_invalid_error)
}

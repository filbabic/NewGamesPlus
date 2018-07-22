package com.babic.filip.core.error

import com.babic.filip.core.R

sealed class EmailError(errorText: Int? = null) : BaseError(errorText) {

    object Empty : EmailError(R.string.email_empty_error)
    object Invalid : EmailError(R.string.email_invalid_error)
}
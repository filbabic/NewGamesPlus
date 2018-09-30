package com.babic.filip.login.ui.error

import com.babic.filip.coreui.error.BaseError
import com.babic.filip.login.R

sealed class EmailError(errorText: Int? = null) : BaseError(errorText) {

    object Empty : EmailError(R.string.email_empty_error)
    object Invalid : EmailError(R.string.email_invalid_error)
}
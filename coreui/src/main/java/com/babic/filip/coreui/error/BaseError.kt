package com.babic.filip.coreui.error

import android.content.Context
import android.support.annotation.StringRes

open class BaseError(@StringRes private val errorText: Int? = null) {

    fun getError(context: Context): String? = errorText?.let { context.getString(it) }
}
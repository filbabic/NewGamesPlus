package com.babic.filip.core.common

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

fun Context?.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    this?.run { Toast.makeText(this, message, duration).show() }
}

fun Context?.toast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) {
    this?.run { Toast.makeText(this, message, duration).show() }
}
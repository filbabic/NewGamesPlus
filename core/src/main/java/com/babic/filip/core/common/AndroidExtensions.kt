package com.babic.filip.core.common

import android.content.Context
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.widget.Toast

fun Context?.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    this?.run { Toast.makeText(this, message, duration).show() }
}

fun Context?.toast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) {
    this?.run { Toast.makeText(this, message, duration).show() }
}

fun FragmentManager.replace(fragment: Fragment, containerId: Int) {
    if (!isDestroyed) {
        popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        beginTransaction()
                .replace(containerId, fragment)
                .commit()
    }
}
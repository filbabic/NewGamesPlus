package com.babic.filip.core.common

import android.view.View

inline fun View.onClick(crossinline onClickHandler: () -> Unit) {
    setOnClickListener { onClickHandler() }
}

var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(isVisible) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }
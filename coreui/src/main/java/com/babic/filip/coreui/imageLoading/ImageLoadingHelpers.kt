package com.babic.filip.coreui.imageLoading

import android.content.Context
import android.view.View
import android.widget.ImageView

fun Context.loadImage(path: String, target: ImageView) {
    GlideApp.with(this).load(path).into(target)
}

fun View.loadImage(path: String, target: ImageView) {
    GlideApp.with(context).load(path).into(target)
}

fun ImageView.loadImage(path: String) {
    GlideApp.with(context).load(path).into(this)
}
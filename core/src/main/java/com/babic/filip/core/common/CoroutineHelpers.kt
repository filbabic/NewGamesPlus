package com.babic.filip.core.common

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.launch

inline fun <reified T> ReceiveChannel<T>.subscribe(crossinline consumer: (T) -> Unit) {
    launch(UI) { consumeEach(consumer) }
}
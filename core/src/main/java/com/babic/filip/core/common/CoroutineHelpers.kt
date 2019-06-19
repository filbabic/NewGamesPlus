package com.babic.filip.core.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

inline fun <reified T> ReceiveChannel<T>.subscribe(crossinline consumer: (T) -> Unit) {
    GlobalScope.launch(Dispatchers.Main) { consumeEach(consumer) }
}
package com.babic.filip.core.common

import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.launch

inline fun <reified T> ReceiveChannel<T>.subscribe(crossinline consumer: (T) -> Unit) {
  GlobalScope.launch(Dispatchers.Main) { consumeEach(consumer) }
}
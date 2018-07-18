package com.babic.filip.core.base

import com.babic.filip.core.routing.Router
import com.babic.filip.core.routing.RoutingDispatcher
import kotlinx.coroutines.experimental.channels.ReceiveChannel

interface StateViewModel<Data : Any, View> {

    fun viewReady(view: View)

    fun viewState(): ReceiveChannel<Data>

    fun errorState(): ReceiveChannel<Throwable?>

    fun setRoutingSource(routingDispatcher: RoutingDispatcher<Router>)
}
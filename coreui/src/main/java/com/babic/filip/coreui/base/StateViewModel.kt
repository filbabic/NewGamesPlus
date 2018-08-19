package com.babic.filip.coreui.base

import com.babic.filip.core.coroutineContext.CoroutineContextProvider
import com.babic.filip.coreui.routing.Router
import com.babic.filip.coreui.routing.RoutingDispatcher
import kotlinx.coroutines.experimental.channels.ReceiveChannel

interface StateViewModel<Data : Any, View> {

    fun viewReady(view: View)

    fun viewState(): ReceiveChannel<Data>

    fun setRoutingSource(routingDispatcher: RoutingDispatcher<Router>)

    fun setCoroutineContextProvider(coroutineContextProvider: CoroutineContextProvider)
}
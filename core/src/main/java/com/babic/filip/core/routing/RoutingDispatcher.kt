package com.babic.filip.core.routing

interface RoutingDispatcher<RoutingSource> {

    fun dispatchRoutingAction(action: RoutingSource.() -> Unit)
}
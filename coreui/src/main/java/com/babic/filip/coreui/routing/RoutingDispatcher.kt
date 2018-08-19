package com.babic.filip.coreui.routing

interface RoutingDispatcher<RoutingSource> {

    fun dispatchRoutingAction(action: RoutingSource.() -> Unit)
}
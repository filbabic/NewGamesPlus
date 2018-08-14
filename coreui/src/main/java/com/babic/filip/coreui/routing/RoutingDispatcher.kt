package com.babic.filip.coreui.routing

interface RoutingDispatcher {

    fun dispatchRoutingAction(action: Router.() -> Unit)
}
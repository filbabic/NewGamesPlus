package com.filip.babic.newgamesplus.routing

import com.babic.filip.coreui.routing.Router
import com.babic.filip.coreui.routing.RoutingDispatcher

class RoutingMediator(private val router: NavigationRouter) : RoutingDispatcher<Router> {

    override fun dispatchRoutingAction(action: Router.() -> Unit) = router.action()
}
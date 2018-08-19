package com.babic.filip.main.routing

import com.babic.filip.coreui.routing.RoutingDispatcher

class MainRouterMediator(private val mainNavigationRouter: MainNavigationRouter) : RoutingDispatcher<MainRouter> {

    override fun dispatchRoutingAction(action: MainRouter.() -> Unit) = mainNavigationRouter.action()
}
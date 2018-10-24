package com.babic.filip.main.ui

import com.babic.filip.coreui.base.BasePresenter
import com.babic.filip.coreui.base.EmptyState
import com.babic.filip.coreui.routing.RoutingDispatcher
import com.babic.filip.main.routing.MainRouter

class MainPresenter(private val routingDispatcher: RoutingDispatcher<MainRouter>) : BasePresenter<EmptyState, MainContract.View>(), MainContract.Presenter {

    override fun initialState(): EmptyState = EmptyState

    override fun refreshPage() = dispatchMainRoutingAction { it.refreshPage() }

    override fun showFeed() = dispatchMainRoutingAction { it.showFeed() }
    override fun showMessages() = dispatchMainRoutingAction { it.showMessages() }
    override fun showMyProfile() = dispatchMainRoutingAction { it.showMyProfile() }
    override fun showTopRated() = dispatchMainRoutingAction { it.showTopRated() }
    override fun showUpcoming() = dispatchMainRoutingAction { it.showUpcoming() }

    private fun dispatchMainRoutingAction(routingAction: (MainRouter) -> Unit) = routingDispatcher.dispatchRoutingAction(routingAction)

    override fun start() {
    }
}
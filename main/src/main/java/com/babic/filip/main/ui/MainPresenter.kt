package com.babic.filip.main.ui

import com.babic.filip.core.coroutineContext.CoroutineContextProvider
import com.babic.filip.coreui.base.BasePresenter
import com.babic.filip.coreui.base.EmptyState
import com.babic.filip.coreui.routing.RoutingDispatcher
import com.babic.filip.main.routing.MainRouter

class MainPresenter(
        contextProvider: CoroutineContextProvider,
        private val routingDispatcher: RoutingDispatcher<MainRouter>)
    : BasePresenter<EmptyState, MainContract.View>(contextProvider), MainContract.Presenter {

    override fun initialState(): EmptyState = EmptyState

    override fun refreshPage() = dispatchMainRoutingAction { router -> router.refreshPage() }

    override fun showFeed() = dispatchMainRoutingAction { router -> router.showFeed() }
    override fun showMessages() = dispatchMainRoutingAction { router -> router.showMessages() }
    override fun showMyProfile() = dispatchMainRoutingAction { router -> router.showMyProfile() }
    override fun showTopRated() = dispatchMainRoutingAction { router -> router.showTopRated() }
    override fun showUpcoming() = dispatchMainRoutingAction { router -> router.showUpcoming() }

    private fun dispatchMainRoutingAction(routingAction: (MainRouter) -> Unit) = routingDispatcher.dispatchRoutingAction(routingAction)
}
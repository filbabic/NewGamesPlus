package com.babic.filip.splash.ui

import com.babic.filip.core.coroutineContext.CoroutineContextProvider
import com.babic.filip.coreui.base.BasePresenter
import com.babic.filip.splash.domain.interaction.GetUserLoggedInUseCase

class SplashPresenter(
        contextProvider: CoroutineContextProvider,
        private val getUserLoggedInState: GetUserLoggedInUseCase) :
        BasePresenter<SplashViewState, SplashContract.View>(contextProvider), SplashContract.Presenter {

    override fun initialState(): SplashViewState = SplashViewState()

    override fun checkUserLoginState() = execute {
        val isLoggedIn = getUserLoggedInState()

        if (isLoggedIn) {
            dispatchRoutingAction { it.showMain() }
        } else {
            pushViewState(SplashViewState(isLoggedIn = false))
        }
    }

    override fun login() = dispatchRoutingAction { it.showLogin() }

    override fun register() = dispatchRoutingAction { it.showRegister() }
}
package com.babic.filip.splash.ui

import com.babic.filip.coreui.base.BasePresenter
import com.babic.filip.splash.domain.interaction.GetUserLoggedInUseCase

class SplashPresenter(private val getUserLoggedInState: GetUserLoggedInUseCase) : BasePresenter<SplashViewState, SplashContract.View>(), SplashContract.Presenter {

    override fun initialState(): SplashViewState = SplashViewState()

    override fun checkUserLoginState() = execute {
        val isLoggedIn = getUserLoggedInState()

        if (isLoggedIn) {
            dispatchRoutingAction { it.showMain() }
        } else {
            changeViewState { it.isLoggedIn = false }
        }
    }

    override fun login() = dispatchRoutingAction { it.showLogin() }
    override fun register() = dispatchRoutingAction { it.showRegister() }
}
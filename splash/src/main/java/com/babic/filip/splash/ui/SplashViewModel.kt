package com.babic.filip.splash.ui

import com.babic.filip.coreui.base.BaseViewModel
import com.babic.filip.splash.domain.interaction.GetUserLoggedInUseCase

class SplashViewModel(private val getUserLoggedInState: GetUserLoggedInUseCase) : BaseViewModel<SplashViewState, SplashContract.View>(), SplashContract.ViewModel {

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
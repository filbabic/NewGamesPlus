package com.babic.filip.splash.ui

import com.babic.filip.core.base.BaseViewModel
import com.babic.filip.splash.domain.interaction.GetUserLoggedInUseCase
import kotlinx.coroutines.experimental.launch

class SplashViewModel(private val getUserLoggedInUseCase: GetUserLoggedInUseCase) : BaseViewModel<SplashViewState, SplashContract.View>(), SplashContract.ViewModel {

    override fun initialState(): SplashViewState = SplashViewState()

    override fun checkUserLoginState() {
        launch(main) {
            val isLoggedIn = getUserLoggedInUseCase.get()

            if (isLoggedIn) {
                dispatchRoutingAction { it.showMain() }
            } else {
                changeViewState { it.isLoggedIn = false }
            }
        }
    }

    override fun login() = dispatchRoutingAction { it.showLogin() }
    override fun register() = dispatchRoutingAction { it.showRegister() }
}
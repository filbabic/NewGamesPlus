package com.babic.filip.splash

import com.babic.filip.core.base.BaseViewModel
import com.filip.babic.data.coroutineContext.CoroutineContextProvider
import com.filip.babic.domain.interaction.GetUserLoggedInUseCase
import kotlinx.coroutines.experimental.launch

class SplashViewModel(contextProvider: CoroutineContextProvider,
                      private val getUserLoggedInUseCase: GetUserLoggedInUseCase) : BaseViewModel<SplashViewState, SplashContract.View>(contextProvider), SplashContract.ViewModel {

    override fun initialState(): SplashViewState = SplashViewState()

    override fun checkUserLoginState() {
        launch(main) {
            val isLoggedIn = getUserLoggedInUseCase.get()

            if (isLoggedIn) {
                dispatchRoutingAction { it.showLogin() } //todo change to main when main is built
            } else {
                changeViewState { it.isLoggedIn = false }
            }
        }
    }

    override fun login() = dispatchRoutingAction { it.showLogin() }
    override fun register() = dispatchRoutingAction { it.showRegister() }
}
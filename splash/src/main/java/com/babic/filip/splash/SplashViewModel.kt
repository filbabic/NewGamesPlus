package com.babic.filip.splash

import com.babic.filip.core.base.BaseViewModel
import com.filip.babic.data.coroutineContext.CoroutineContextProvider
import com.filip.babic.device.preferences.PreferencesHelper

class SplashViewModel(contextProvider: CoroutineContextProvider,
                      private val preferencesHelper: PreferencesHelper) : BaseViewModel<SplashViewState, SplashContract.View>(contextProvider), SplashContract.ViewModel {

    override fun initialState(): SplashViewState = SplashViewState()

    override fun checkUserLoginState() {
        val isLoggedIn = preferencesHelper.isLoggedIn()

        if (isLoggedIn) {
            dispatchRoutingAction { it.showLogin() } //todo change to main when main is built
        } else {
            changeStateData { it.isLoggedIn = false }
        }
    }

    override fun login() = dispatchRoutingAction { it.showLogin() }
    override fun register() = dispatchRoutingAction { it.showRegister() }
}
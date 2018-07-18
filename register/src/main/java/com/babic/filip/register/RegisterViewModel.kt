package com.babic.filip.register

import com.babic.filip.core.base.BaseViewModel
import com.filip.babic.data.coroutineContext.CoroutineContextProvider

class RegisterViewModel(contextProvider: CoroutineContextProvider) : BaseViewModel<RegisterViewState, RegisterContract.View>(contextProvider), RegisterContract.ViewModel {

    override fun initialState(): RegisterViewState = RegisterViewState()
}
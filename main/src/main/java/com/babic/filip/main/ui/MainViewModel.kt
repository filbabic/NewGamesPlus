package com.babic.filip.main.ui

import com.babic.filip.core.base.BaseViewModel

class MainViewModel : BaseViewModel<MainViewState, MainContract.View>() {

    override fun initialState(): MainViewState = MainViewState()
}
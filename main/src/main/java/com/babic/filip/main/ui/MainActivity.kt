package com.babic.filip.main.ui

import android.os.Bundle
import com.babic.filip.core.base.BaseActivity
import com.babic.filip.core.base.BaseView
import com.babic.filip.core.base.StateViewModel
import com.babic.filip.main.R
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewState>() {

    private val mainViewModel: StateViewModel<MainViewState, MainContract.View> by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
    }

    private fun initUi() {
    }

    override fun getViewModel(): StateViewModel<MainViewState, BaseView> = mainViewModel as StateViewModel<MainViewState, BaseView>
    override fun getLayout(): Int = R.layout.activity_main
    override fun getScope(): String = MAIN_SCOPE
}

const val MAIN_SCOPE = "Main"
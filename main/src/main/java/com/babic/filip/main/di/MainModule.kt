package com.babic.filip.main.di

import com.babic.filip.coreui.base.BaseActivity
import com.babic.filip.main.routing.MainNavigationRouter
import com.babic.filip.main.routing.MainRouterMediator
import com.babic.filip.main.ui.MAIN_SCOPE
import com.babic.filip.main.ui.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val mainModule = module(MAIN_SCOPE) {

    viewModel {
        val activity: BaseActivity<*> = it[0]

        val mainNavigator = MainNavigationRouter(activity.supportFragmentManager)
        val routingDispatcher = MainRouterMediator(mainNavigator)

        MainViewModel(routingDispatcher)
    }
}
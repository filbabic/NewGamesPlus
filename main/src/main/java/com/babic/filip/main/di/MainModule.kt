package com.babic.filip.main.di

import com.babic.filip.coreui.base.BaseActivity
import com.babic.filip.main.routing.MainNavigationRouter
import com.babic.filip.main.routing.MainRouterMediator
import com.babic.filip.main.ui.MAIN_SCOPE
import com.babic.filip.main.ui.MainPresenter
import org.koin.dsl.module.module

val mainModule = module {

    scope(MAIN_SCOPE) {
        val activity: BaseActivity<*> = it[0]

        val mainNavigator = MainNavigationRouter(activity.supportFragmentManager)
        val routingDispatcher = MainRouterMediator(mainNavigator)

        MainPresenter(routingDispatcher)
    }
}
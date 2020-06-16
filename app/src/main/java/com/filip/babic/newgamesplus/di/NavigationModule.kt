package com.filip.babic.newgamesplus.di

import com.babic.filip.coreui.base.BaseActivity
import com.babic.filip.coreui.routing.Router
import com.babic.filip.coreui.routing.RoutingDispatcher
import com.filip.babic.newgamesplus.routing.NavigationRouter
import com.filip.babic.newgamesplus.routing.RoutingMediator
import org.koin.dsl.module.module

val navigationModule = module {
    factory { parameters ->
        val activity: BaseActivity<*> = parameters[0]

        RoutingMediator(NavigationRouter(activity)) as RoutingDispatcher<Router>
    }
}
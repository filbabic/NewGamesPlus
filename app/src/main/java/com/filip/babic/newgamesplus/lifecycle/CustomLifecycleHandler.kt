package com.filip.babic.newgamesplus.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.babic.filip.core.base.BaseActivity
import com.babic.filip.core.base.BaseViewModel
import com.babic.filip.core.coroutineContext.CoroutineContextProviderImpl
import com.babic.filip.core.routing.Router
import com.babic.filip.core.routing.RoutingDispatcher
import com.filip.babic.newgamesplus.routing.NavigationRouter
import com.filip.babic.newgamesplus.routing.RoutingMediator

class CustomLifecycleHandler : Application.ActivityLifecycleCallbacks {

    //stubs
    override fun onActivityPaused(p0: Activity?) = Unit

    override fun onActivityResumed(p0: Activity?) = Unit
    override fun onActivityStarted(p0: Activity?) = Unit
    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) = Unit
    override fun onActivityStopped(p0: Activity?) = Unit

    override fun onActivityDestroyed(activity: Activity?) {
        val baseActivity = activity as? BaseActivity<*>

        baseActivity?.run {
            val baseViewModel = getViewModel() as? BaseViewModel<*, *>

            baseViewModel?.onDestroy()
        }
    }

    override fun onActivityCreated(activity: Activity?, p1: Bundle?) {
        val baseActivity = activity as? BaseActivity<*>

        baseActivity?.run {
            val baseViewModel = getViewModel() as? BaseViewModel<*, *>

            val routingDispatcher = getRoutingDispatcher(this)

            baseViewModel?.setRoutingSource(routingDispatcher)
            baseViewModel?.setCoroutineContextProvider(getCoroutineContextProvider())
        }
    }

    private fun getRoutingDispatcher(baseActivity: BaseActivity<*>): RoutingDispatcher<Router> {
        val navigator = NavigationRouter(baseActivity, baseActivity.supportFragmentManager)

        return RoutingMediator(navigator)
    }

    private fun getCoroutineContextProvider() = CoroutineContextProviderImpl()
}
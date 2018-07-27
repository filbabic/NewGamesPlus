package com.filip.babic.newgamesplus.lifecycle

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.babic.filip.core.base.BaseActivity
import com.babic.filip.core.base.BaseFragment
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
    override fun onActivityDestroyed(activity: Activity?) = Unit

    override fun onActivityCreated(activity: Activity?, p1: Bundle?) {
        val baseActivity = activity as? BaseActivity<*>

        baseActivity?.run {
            initViewModel(getRoutingDispatcher(this), CoroutineContextProviderImpl())

            setupFragmentLifecycleHandler(this)
        }
    }

    private fun setupFragmentLifecycleHandler(baseActivity: BaseActivity<*>) {
        baseActivity.supportFragmentManager?.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentAttached(fm: FragmentManager, fragment: Fragment, context: Context) {
                super.onFragmentAttached(fm, fragment, context)

                val baseFragment = fragment as? BaseFragment<*>

                baseFragment?.run { initViewModel(getRoutingDispatcher(baseActivity), CoroutineContextProviderImpl()) }
            }
        }, true)
    }

    private fun getRoutingDispatcher(baseActivity: BaseActivity<*>): RoutingDispatcher<Router> {
        val navigator = NavigationRouter(baseActivity, baseActivity.supportFragmentManager)

        return RoutingMediator(navigator)
    }
}
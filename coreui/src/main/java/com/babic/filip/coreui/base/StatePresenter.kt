package com.babic.filip.coreui.base

import android.arch.lifecycle.LiveData
import com.babic.filip.coreui.routing.Router
import com.babic.filip.coreui.routing.RoutingDispatcher

interface StatePresenter<Data : Any, View> {

    fun viewReady(view: View)

    fun viewState(): LiveData<Data>

    fun setRoutingSource(routingDispatcher: RoutingDispatcher<Router>)
}
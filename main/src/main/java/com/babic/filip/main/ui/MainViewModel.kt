package com.babic.filip.main.ui

import com.babic.filip.coreui.base.BaseViewModel
import com.babic.filip.main.R

class MainViewModel : BaseViewModel<MainViewState, MainContract.View>(), MainContract.ViewModel {

    override fun initialState(): MainViewState = MainViewState(R.id.topRated)

    override fun setCurrentPage(selectedPage: Int) = changeViewState { it.activeItemId = selectedPage }

    override fun refreshPage() = dispatchRoutingAction { it.refreshPage() }

    override fun showFeed() = dispatchRoutingAction { it.showFeed() }
    override fun showMessages() = dispatchRoutingAction { it.showMessages() }
    override fun showMyProfile() = dispatchRoutingAction { it.showMyProfile() }
    override fun showTopRated() = dispatchRoutingAction { it.showTopRated() }
    override fun showUpcoming() = dispatchRoutingAction { it.showUpcoming() }
}
package com.babic.filip.main.routing

import android.support.v4.app.FragmentManager
import com.babic.filip.coreui.base.RefreshablePage
import com.babic.filip.coreui.common.replace
import com.babic.filip.main.R
import com.babic.filip.toprated.ui.TopRatedGamesFragment

class MainNavigationRouter(private val fragmentManager: FragmentManager) : MainRouter {

    private var refreshablePage: RefreshablePage? = null
    private val fragmentContainer by lazy { R.id.fragmentContainer }

    override fun showFeed() {
    }

    override fun showMessages() {
    }

    override fun showMyProfile() {
    }

    override fun showTopRated() {
        val newFragment = TopRatedGamesFragment()
        refreshablePage = newFragment

        fragmentManager.replace(newFragment, fragmentContainer)
    }

    override fun showUpcoming() {
    }

    override fun refreshPage() {
        refreshablePage?.refresh()
    }
}
package com.filip.babic.newgamesplus.routing

import android.content.Intent
import android.support.v4.app.FragmentManager
import com.babic.filip.core.base.BaseActivity
import com.babic.filip.core.common.replace
import com.babic.filip.core.routing.Router
import com.babic.filip.login.ui.LoginActivity
import com.babic.filip.main.ui.MainActivity
import com.babic.filip.main.ui.RefreshablePage
import com.babic.filip.main.ui.topRated.TopRatedGamesFragment
import com.babic.filip.register.ui.RegisterActivity
import com.filip.babic.newgamesplus.R

class NavigationRouter(private val activity: BaseActivity<*>, private val fragmentManager: FragmentManager) : Router {

    companion object {
        const val containerId: Int = R.id.fragmentContainer
    }

    private var refreshablePage: RefreshablePage? = null

    /**
     * Activity based transitions
     * **/
    override fun showLogin() = startNextScreen(getIntent<LoginActivity>())

    override fun showRegister() = startNextScreen(getIntent<RegisterActivity>())

    override fun showMain() = startNextScreen(getIntent<MainActivity>().apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP })

    override fun showGameDetails(gameId: String) {
    }

    override fun onUserRegistered() = showMain()

    /**
     * Fragment based transitions
     * **/
    override fun showFeed() {
    }

    override fun showMessages() {
    }

    override fun showMyProfile() {
    }

    override fun showTopRated() {
        val newFragment = TopRatedGamesFragment()
        refreshablePage = newFragment

        fragmentManager.replace(newFragment, containerId)
    }

    override fun showUpcoming() {
    }

    override fun refreshPage() {
        refreshablePage?.refresh()
    }

    /**
     * Base setup
     * **/
    private fun startNextScreen(intent: Intent) = activity.startActivity(intent)

    private inline fun <reified T : BaseActivity<*>> getIntent() = Intent(activity, T::class.java)
}
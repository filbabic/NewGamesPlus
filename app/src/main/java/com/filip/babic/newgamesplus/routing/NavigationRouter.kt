package com.filip.babic.newgamesplus.routing

import android.content.Intent
import android.support.v4.app.FragmentManager
import com.babic.filip.core.R
import com.babic.filip.core.base.BaseActivity
import com.babic.filip.core.routing.Router
import com.babic.filip.login.ui.LoginActivity
import com.babic.filip.main.ui.MainActivity
import com.babic.filip.main.ui.topRated.TopRatedGamesFragment
import com.babic.filip.register.ui.RegisterActivity

class NavigationRouter(private val activity: BaseActivity<*>, private val fragmentManager: FragmentManager) : Router {

    companion object {
        val containerId: Int = R.id.fragment_container
    }

    //activity based transitions
    override fun showLogin() = startNextScreen(getIntent<LoginActivity>())

    override fun showRegister() = startNextScreen(getIntent<RegisterActivity>())

    override fun showMain() = startNextScreen(getIntent<MainActivity>().apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP })

    private fun startNextScreen(intent: Intent) = activity.startActivity(intent)

    override fun onUserRegistered() = showMain()

    private inline fun <reified T : BaseActivity<*>> getIntent() = Intent(activity, T::class.java)


    //fragment based transitions

    override fun showFeed() {
        val newFragment = TopRatedGamesFragment()
    }

    override fun showMessages() {
    }

    override fun showMyProfile() {
    }

    override fun showTopRated() {
    }

    override fun showUpcoming() {
    }


}
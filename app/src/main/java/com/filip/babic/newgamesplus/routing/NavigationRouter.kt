package com.filip.babic.newgamesplus.routing

import android.content.Intent
import com.babic.filip.coreui.base.BaseActivity
import com.babic.filip.coreui.routing.Router
import com.babic.filip.gamedetails.ui.GameDetailsActivity
import com.babic.filip.gamedetails.ui.GameDetailsActivity.Companion.KEY_GAME_ID
import com.babic.filip.login.ui.LoginActivity
import com.babic.filip.main.ui.MainActivity
import com.babic.filip.register.ui.RegisterActivity

class NavigationRouter(private val activity: BaseActivity<*>) : Router {

    /**
     * Activity based transitions
     * **/
    override fun showLogin() = startNextScreen(getIntent<LoginActivity>())

    override fun showRegister() = startNextScreen(getIntent<RegisterActivity>())

    override fun showMain() = startNextScreen(getIntent<MainActivity>().apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP })

    override fun showGameDetails(gameId: String) = startNextScreen(getIntent<GameDetailsActivity>().apply { putExtra(KEY_GAME_ID, gameId) })

    override fun onUserRegistered() = showMain()

    /**
     * Base setup
     * **/
    private fun startNextScreen(intent: Intent) = activity.startActivity(intent)

    private inline fun <reified T : BaseActivity<*>> getIntent() = Intent(activity, T::class.java)
}
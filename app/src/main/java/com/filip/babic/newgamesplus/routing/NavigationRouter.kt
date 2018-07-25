package com.filip.babic.newgamesplus.routing

import android.content.Intent
import com.babic.filip.core.base.BaseActivity
import com.babic.filip.core.routing.Router
import com.babic.filip.login.ui.LoginActivity
import com.babic.filip.register.ui.RegisterActivity

class NavigationRouter(private val activity: BaseActivity<*>) : Router {

    override fun showLogin() = startNextScreen(getIntent<LoginActivity>())

    override fun showRegister() = startNextScreen(getIntent<RegisterActivity>())

    private fun startNextScreen(intent: Intent) = activity.startActivity(intent)

    override fun onUserRegistered() {
    }

    private inline fun <reified T : BaseActivity<*>> getIntent() = Intent(activity, T::class.java)
}
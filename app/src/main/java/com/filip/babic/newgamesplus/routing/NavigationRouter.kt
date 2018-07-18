package com.filip.babic.newgamesplus.routing

import android.content.Intent
import com.babic.filip.core.base.BaseActivity
import com.babic.filip.core.routing.Router
import com.babic.filip.login.LoginActivity
import com.babic.filip.register.RegisterActivity

class NavigationRouter(private val activity: BaseActivity<*>) : Router {

    override fun showLogin() = startNextScreen(getIntent<LoginActivity>())

    override fun showRegister() = startNextScreen(getIntent<RegisterActivity>())

    private fun startNextScreen(intent: Intent) = activity.startActivity(intent)

    private inline fun <reified T : BaseActivity<*>> getIntent() = Intent(activity, T::class.java)
}
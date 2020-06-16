package com.filip.babic.newgamesplus

import android.app.Application
import com.babic.filip.core.di.coreModule
import com.babic.filip.gamedetails.di.gameDetailsModule
import com.babic.filip.login.di.loginModule
import com.babic.filip.main.di.mainModule
import com.babic.filip.myprofile.userModule
import com.babic.filip.networking.data.di.networkingModule
import com.babic.filip.register.di.registerModule
import com.babic.filip.splash.di.splashModule
import com.babic.filip.toprated.di.topRatedGamesModule
import com.filip.babic.device.di.preferenceModule
import com.filip.babic.newgamesplus.di.appModule
import com.filip.babic.newgamesplus.di.navigationModule
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.android.startKoin

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin(this, coreModules + featureModules)

        LeakCanary.install(this)
    }

    private val coreModules = listOf(
            coreModule,
            networkingModule(BuildConfig.DEBUG),
            navigationModule,
            appModule
    )

    private val featureModules = listOf(
            registerModule,
            splashModule,
            loginModule,
            preferenceModule,
            mainModule,
            gameDetailsModule,
            topRatedGamesModule,
            userModule
    )
}
package com.filip.babic.newgamesplus

import android.app.Application
import com.babic.filip.core.di.coreModule
import com.babic.filip.gamedetails.di.gameDetailsModule
import com.babic.filip.login.di.loginModule
import com.babic.filip.main.di.mainModule
import com.babic.filip.networking.data.di.networkingModule
import com.babic.filip.register.di.registerModule
import com.babic.filip.splash.di.splashModule
import com.babic.filip.toprated.di.topRatedGamesModule
import com.filip.babic.device.di.preferenceModule
import com.filip.babic.newgamesplus.di.navigationModule
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(instance)

            modules(coreModules + featureModules)
        }

        LeakCanary.install(this)
    }

    private val coreModules = listOf(
            coreModule,
            networkingModule(BuildConfig.DEBUG),
            navigationModule
    )

    private val featureModules = listOf(
            splashModule,
            registerModule,
            loginModule,
            preferenceModule,
            mainModule,
            gameDetailsModule,
            topRatedGamesModule
    )
}
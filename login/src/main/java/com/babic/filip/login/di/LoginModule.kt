package com.babic.filip.login.di

import com.babic.filip.login.ui.LOGIN_SCOPE
import com.babic.filip.login.ui.LoginPresenter
import org.koin.dsl.module.module

val loginModule = module {

    scope(LOGIN_SCOPE) { LoginPresenter() }
}
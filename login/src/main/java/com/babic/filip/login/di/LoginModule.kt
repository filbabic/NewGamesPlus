package com.babic.filip.login.di

import com.babic.filip.login.ui.LOGIN_SCOPE
import com.babic.filip.login.ui.LoginPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val loginModule = module {

    scope(named(LOGIN_SCOPE)) { scoped { LoginPresenter() } }
}
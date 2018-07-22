package com.babic.filip.core.base

interface BaseView {

    fun showNetworkError()

    fun showServerError()

    fun showParseError()

    fun showAuthenticationError()
}
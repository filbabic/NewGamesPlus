package com.babic.filip.myprofile.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import com.babic.filip.myprofile.UserContract
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope

class UserFragment : Fragment() {

    private val userPresenter: UserContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindScope(getOrCreateScope("USER_SCOPE"))
    }
}


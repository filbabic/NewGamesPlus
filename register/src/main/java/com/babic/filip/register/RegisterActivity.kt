package com.babic.filip.register

import android.os.Bundle
import com.babic.filip.core.base.BaseActivity
import com.babic.filip.core.base.BaseView
import com.babic.filip.core.base.StateViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterActivity : BaseActivity<RegisterViewState>(), RegisterContract.View {

    private val viewModel: RegisterContract.ViewModel by viewModel<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int = R.layout.activity_register

    override fun getScope(): String = REGISTER_SCOPE

    override fun getViewModel(): StateViewModel<RegisterViewState, BaseView> = viewModel as StateViewModel<RegisterViewState, BaseView>
}

const val REGISTER_SCOPE = "Register"
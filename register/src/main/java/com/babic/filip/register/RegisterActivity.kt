package com.babic.filip.register

import android.os.Bundle
import com.babic.filip.core.base.BaseActivity
import com.babic.filip.core.base.BaseView
import com.babic.filip.core.base.StateViewModel
import com.babic.filip.core.common.onClick
import com.babic.filip.core.common.onTextChanged
import com.babic.filip.core.common.subscribe
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterActivity : BaseActivity<RegisterViewState>(), RegisterContract.View {

    private val viewModel: RegisterContract.ViewModel by viewModel<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()

        viewModel.viewState().subscribe(::onDataChanged)
    }

    private fun initUi() {
        usernameInput.onTextChanged(viewModel::usernameChanged)
        emailInput.onTextChanged(viewModel::emailChanged)
        passwordInput.onTextChanged(viewModel::passwordChanged)

        register.onClick(viewModel::registerUser)
    }

    private fun onDataChanged(registerViewState: RegisterViewState) {
        usernameRoot.error = registerViewState.usernameError?.getError(this)
        emailRoot.error = registerViewState.emailError?.getError(this)
        passwordRoot.error = registerViewState.passwordError?.getError(this)

        register.isEnabled = registerViewState.isValidData
    }

    override fun showInvalidCredentialsError() {
    }

    override fun showUserExistsError() {
    }

    override fun showAuthenticationError() {
    }

    override fun showNetworkError() {
    }

    override fun showParseError() {
    }

    override fun showServerError() {
    }

    override fun getLayout(): Int = R.layout.activity_register
    override fun getScope(): String = REGISTER_SCOPE
    override fun getViewModel(): StateViewModel<RegisterViewState, BaseView> = viewModel as StateViewModel<RegisterViewState, BaseView>
}

const val REGISTER_SCOPE = "Register"
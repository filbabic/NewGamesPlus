package com.babic.filip.register.ui

import android.os.Bundle
import com.babic.filip.core.common.subscribe
import com.babic.filip.coreui.base.BaseActivity
import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StatePresenter
import com.babic.filip.coreui.common.onClick
import com.babic.filip.coreui.common.onTextChanged
import com.babic.filip.coreui.common.toast
import com.babic.filip.register.R
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterActivity : BaseActivity<RegisterViewState>(), RegisterContract.View {

    private val viewModel: RegisterContract.Presenter by viewModel<RegisterPresenter>()

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

    override fun showAuthenticationError() = toast(R.string.invalid_credentials_error)
    override fun showNetworkError() = toast(R.string.network_error)
    override fun showParseError() = toast(R.string.data_parse_error)
    override fun showServerError() = toast(R.string.general_server_error)

    override fun getLayout(): Int = R.layout.activity_register
    override fun getScope(): String = REGISTER_SCOPE
    override fun getViewModel(): StatePresenter<RegisterViewState, BaseView> = viewModel as StatePresenter<RegisterViewState, BaseView>
}

const val REGISTER_SCOPE = "Register"
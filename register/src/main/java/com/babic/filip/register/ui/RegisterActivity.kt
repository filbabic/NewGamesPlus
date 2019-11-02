package com.babic.filip.register.ui

import android.os.Bundle
import com.babic.filip.coreui.base.BaseActivity
import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StatePresenter
import com.babic.filip.coreui.common.onClick
import com.babic.filip.coreui.common.onTextChanged
import com.babic.filip.coreui.common.toast
import com.babic.filip.register.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity<RegisterViewState>(), RegisterContract.View {

    private val presenter: RegisterContract.Presenter by lazy { scope.get<RegisterPresenter>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
    }

    private fun initUi() {
        usernameInput.onTextChanged(presenter::usernameChanged)
        emailInput.onTextChanged(presenter::emailChanged)
        passwordInput.onTextChanged(presenter::passwordChanged)

        register.onClick(presenter::registerUser)
    }

    override fun onViewStateChanged(viewState: RegisterViewState) {
        usernameRoot.error = viewState.usernameError?.getError(this)
        emailRoot.error = viewState.emailError?.getError(this)
        passwordRoot.error = viewState.passwordError?.getError(this)

        register.isEnabled = viewState.isValidData
    }

    override fun showAuthenticationError() = toast(R.string.invalid_credentials_error)
    override fun showNetworkError() = toast(R.string.network_error)
    override fun showParseError() = toast(R.string.data_parse_error)
    override fun showServerError() = toast(R.string.general_server_error)

    override fun getLayout(): Int = R.layout.activity_register
    override fun getScope(): String = REGISTER_SCOPE
    override fun getPresenter(): StatePresenter<RegisterViewState, BaseView> = presenter as StatePresenter<RegisterViewState, BaseView>
}

const val REGISTER_SCOPE = "Register"
package com.babic.filip.register.ui

import com.babic.filip.core.base.BaseViewModel
import com.babic.filip.networking.data.error.ApiDataTransformationException
import com.babic.filip.networking.data.error.AuthenticationError
import com.babic.filip.networking.data.error.NetworkException
import com.babic.filip.networking.data.error.ServerError
import com.babic.filip.networking.data.model.doOnError
import com.babic.filip.networking.data.model.doOnSuccess
import com.babic.filip.register.data.model.RegisterData
import com.babic.filip.register.data.validation.RegisterDataValidator
import com.babic.filip.register.domain.interaction.RegisterUserUseCase
import com.babic.filip.register.domain.interaction.SaveUserLoginUseCase
import com.babic.filip.register.domain.model.UserRegistration

class RegisterViewModel(private val registerUserUseCase: RegisterUserUseCase,
                        private val saveUserLoginUseCase: SaveUserLoginUseCase) : BaseViewModel<RegisterViewState, RegisterContract.View>(), RegisterContract.ViewModel {

    private val registerData = RegisterData()

    private val registerDataValidator = RegisterDataValidator()

    override fun initialState(): RegisterViewState = RegisterViewState()

    override fun emailChanged(email: String) {
        registerData.email = email

        changeViewState { it.emailError = registerDataValidator.getEmailError(email) }
        checkData()
    }

    override fun usernameChanged(username: String) {
        registerData.username = username

        changeViewState { it.usernameError = registerDataValidator.getUsernameError(username) }
        checkData()
    }

    override fun passwordChanged(password: String) {
        registerData.password = password

        changeViewState { it.passwordError = registerDataValidator.getPasswordError(password) }
        checkData()
    }

    private fun checkData() = changeViewState { it.isValidData = registerDataValidator.isValid(registerData) }

    override fun registerUser() {
        if (registerDataValidator.isValid(registerData)) {
            processRegistration(registerData)
        }
    }

    private fun processRegistration(registerData: RegisterData) = execute {
        val userRegistrationResult = getData { registerUserUseCase.run(registerData) }

        userRegistrationResult.doOnSuccess(::onUserRegistered).doOnError(::processError)
    }

    private fun processError(throwable: Throwable?) = when (throwable) {
        is ServerError -> showServerError()
        is ApiDataTransformationException -> showDataParseError()
        is NetworkException -> showNetworkError()
        is AuthenticationError -> showAuthenticationError()
        else -> Unit
    }

    private fun onUserRegistered(userRegistration: UserRegistration) {
        saveUserLoginUseCase.execute(userRegistration.token)
        dispatchRoutingAction { it.onUserRegistered() }
    }

}
package com.babic.filip.register

import com.babic.filip.core.base.BaseViewModel
import com.babic.filip.core.validation.getEmailError
import com.babic.filip.core.validation.getPasswordError
import com.babic.filip.core.validation.getUsernameError
import com.filip.babic.data.coroutineContext.CoroutineContextProvider
import com.filip.babic.domain.interaction.RegisterUserUseCase
import com.filip.babic.domain.interaction.SaveUserLoginUseCase
import com.filip.babic.domain.model.UserRegistration
import com.filip.babic.domain.model.request.RegisterData
import com.filip.babic.domain.model.result.doOnError
import com.filip.babic.domain.model.result.doOnSuccess
import com.filip.babic.domain.validation.RegisterDataValidator
import kotlinx.coroutines.experimental.launch

class RegisterViewModel(contextProvider: CoroutineContextProvider,
                        private val registerUserUseCase: RegisterUserUseCase,
                        private val saveUserLoginUseCase: SaveUserLoginUseCase) : BaseViewModel<RegisterViewState, RegisterContract.View>(contextProvider), RegisterContract.ViewModel {

    private val registerData = RegisterData()

    private val registerDataValidator = RegisterDataValidator()

    override fun initialState(): RegisterViewState = RegisterViewState()

    override fun emailChanged(email: String) {
        registerData.email = email

        changeViewState { it.emailError = getEmailError(email) }
        checkData()
    }

    override fun usernameChanged(username: String) {
        registerData.username = username

        changeViewState { it.usernameError = getUsernameError(username) }
        checkData()
    }

    override fun passwordChanged(password: String) {
        registerData.password = password

        changeViewState { it.passwordError = getPasswordError(password) }
        checkData()
    }

    private fun checkData() = changeViewState { it.isValidData = registerDataValidator.isValid(registerData) }

    override fun registerUser() {
        if (registerDataValidator.isValid(registerData)) {
            processRegistration(registerData)
        } else {
            view.showInvalidCredentialsError()
        }
    }

    private fun processRegistration(registerData: RegisterData) = launch(main) {
        val userRegistrationResult = registerUserUseCase.run(registerData)

        userRegistrationResult.doOnSuccess(::onUserRegistered).doOnError(::processError)
    }

    private fun onUserRegistered(userRegistration: UserRegistration) {
        saveUserLoginUseCase.execute(userRegistration.token)
        dispatchRoutingAction { it.onUserRegistered() }
    }

}
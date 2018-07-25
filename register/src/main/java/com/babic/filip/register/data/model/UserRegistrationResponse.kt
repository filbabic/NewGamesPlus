package com.babic.filip.register.data.model

import com.babic.filip.networking.data.model.Mappable
import com.babic.filip.register.domain.model.UserRegistration

class UserRegistrationResponse(val token: String = "") : Mappable<UserRegistration> {

    override fun isValid(): Boolean = token.isNotBlank()

    override fun mapToData(): UserRegistration = UserRegistration(token)
}
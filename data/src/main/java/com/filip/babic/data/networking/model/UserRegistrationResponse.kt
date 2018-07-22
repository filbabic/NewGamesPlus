package com.filip.babic.data.networking.model

import com.filip.babic.domain.model.UserRegistration
import com.filip.babic.domain.model.result.Mappable

class UserRegistrationResponse(val token: String = "") : Mappable<UserRegistration> {

    override fun isValid(): Boolean = token.isNotBlank()

    override fun mapToData(): UserRegistration = UserRegistration(token)

}
package com.babic.filip.myprofile

import com.babic.filip.myprofile.domain.UserRepository

class UserPresenter(private val userRepository: UserRepository, private val userId: String?) : UserContract.Presenter {
}
package com.babic.filip.myprofile

import com.babic.filip.myprofile.api.UserApiService
import com.babic.filip.myprofile.data.UserRepositoryImpl
import com.babic.filip.myprofile.domain.UserRepository
import org.koin.dsl.module.module
import retrofit2.Retrofit

val userModule = module {
    single { get<Retrofit>().create(UserApiService::class.java) }

    single { UserRepositoryImpl(userApiService = get()) as UserRepository }

    scope("USER_SCOPE") {
        UserPresenter(userRepository = get()) as UserContract.Presenter
    }
}
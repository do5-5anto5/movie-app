package com.do55anto5.movieapp.domain.usecase.user

import com.do55anto5.movieapp.domain.model.user.User
import com.do55anto5.movieapp.domain.repository.user.UserRepository
import javax.inject.Inject

class UserUpdateUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        userRepository.update(user)
    }

}
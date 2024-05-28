package com.do55anto5.movieapp.domain.usecase.auth

import com.do55anto5.movieapp.data.repository.auth.FirebaseAuthenticationImpl
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val firebaseAuthenticationImpl: FirebaseAuthenticationImpl
) {

    suspend operator fun invoke(email: String, password: String) {
        firebaseAuthenticationImpl.register(email, password)
    }
}
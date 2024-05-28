package com.do55anto5.movieapp.data.repository.auth


import com.do55anto5.movieapp.domain.repository.auth.FirebaseAuthentication
import com.google.firebase.auth.FirebaseAuth
import kotlin.coroutines.suspendCoroutine

class FirebaseAuthenticationImpl(
    private val firebaseAuth: FirebaseAuth
) : FirebaseAuthentication {
    override suspend fun login(email: String, password: String) {
        return suspendCoroutine { continuation ->
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resumeWith(Result.success(Unit))
                    } else {
                        task.exception?.let { continuation.resumeWith(Result.failure(it)) }
                    }
                }
        }
    }

    override suspend fun register(email: String, password: String) {
        return suspendCoroutine { continuation ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resumeWith(Result.success(Unit))
                    } else {
                        task.exception?.let { continuation.resumeWith(Result.failure(it)) }
                    }
                }
        }
    }

    override suspend fun forgot(email: String) {
        return suspendCoroutine { continuation ->
            firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resumeWith(Result.success(Unit))
                    } else {
                        task.exception?.let { continuation.resumeWith(Result.failure(it)) }
                    }
                }
        }
    }
}
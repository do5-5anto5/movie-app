package com.do55anto5.movieapp.data.repository.user

import com.do55anto5.movieapp.domain.model.user.User
import com.do55anto5.movieapp.domain.repository.user.UserRepository
import com.do55anto5.movieapp.util.FirebaseHelper
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : UserRepository {

    private val profileRef = firebaseDatabase.reference
        .child("profile")

    override suspend fun update(user: User) {
        return suspendCoroutine { continuation ->
            profileRef
                .child(FirebaseHelper.getUserId())
                .setValue(user)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resumeWith(Result.success(Unit))
                    } else {
                        task.exception?.let { continuation.resumeWith(Result.failure(it)) }
                    }
                }
        }
    }

    override suspend fun get(): User {
        return suspendCoroutine { continuation ->
            profileRef
                .child(FirebaseHelper.getUserId())
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = task.result?.getValue(User::class.java)
                        if (user != null) {
                            continuation.resumeWith(Result.success(user))
                        } else {
                                continuation.resumeWithException(Exception("User not found"))
                        }
                    } else {
                        task.exception?.let { continuation.resumeWithException(it) }
                    }
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

}
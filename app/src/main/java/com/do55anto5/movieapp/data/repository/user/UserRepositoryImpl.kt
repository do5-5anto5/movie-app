package com.do55anto5.movieapp.data.repository.user

import com.do55anto5.movieapp.domain.model.user.User
import com.do55anto5.movieapp.domain.repository.user.UserRepository
import com.do55anto5.movieapp.util.FirebaseHelper
import com.do55anto5.movieapp.util.safeResume
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
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
        return suspendCancellableCoroutine { continuation ->
            profileRef
                .child(FirebaseHelper.getUserId())
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val user = snapshot.getValue(User::class.java)
                        user?.let {
                        continuation.safeResume(it)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        error.toException().let {
                            continuation.resumeWith(Result.failure(it))
                        }
                    }

                })
        }
    }

}
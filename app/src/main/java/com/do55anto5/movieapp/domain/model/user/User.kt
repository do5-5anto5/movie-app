package com.do55anto5.movieapp.domain.model.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String? = null,
    val photoUrl: String? = null,
    val firstName: String? = null,
    val surname: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val gender: String? = null,
    val country: String? = null
) : Parcelable

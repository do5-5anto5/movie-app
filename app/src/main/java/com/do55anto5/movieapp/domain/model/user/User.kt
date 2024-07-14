package com.do55anto5.movieapp.domain.model.user

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@Parcelize
@IgnoreExtraProperties
data class User(
    val id: String? = "",
    val photoUrl: String? = "",
    val firstName: String? = "",
    val surname: String? = "",
    val email: String? = "",
    val phoneNumber: String? = "",
    val gender: String? = "",
    val country: String? = ""
) : Parcelable

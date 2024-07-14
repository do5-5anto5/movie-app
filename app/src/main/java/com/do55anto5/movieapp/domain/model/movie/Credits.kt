package com.do55anto5.movieapp.domain.model.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Credits(
    val cast: List<Person>?
) : Parcelable

package com.do55anto5.movieapp.presenter.model

import android.os.Parcelable
import com.do55anto5.movieapp.domain.model.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenrePresentation(
    val id: Int?,
    val name: String?,
    val movies: List<Movie>?
): Parcelable

package com.do55anto5.movieapp.domain.repository.movie

import com.do55anto5.movieapp.data.model.CreditsResponse
import com.do55anto5.movieapp.data.model.MovieResponse

interface MovieDetailsRepository {

    suspend fun getMovieDetails(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ) : MovieResponse

    suspend fun getMovieCredits(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ) : CreditsResponse

}
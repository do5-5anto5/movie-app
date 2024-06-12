package com.do55anto5.movieapp.domain.repository.movie

import com.do55anto5.movieapp.data.model.CreditsResponse
import com.do55anto5.movieapp.data.model.MovieResponse
import com.do55anto5.movieapp.data.model.MovieReviewResponse

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

    suspend fun getSimilar(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ) : List<MovieResponse>

    suspend fun getMovieReviews(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ) : List<MovieReviewResponse>

}
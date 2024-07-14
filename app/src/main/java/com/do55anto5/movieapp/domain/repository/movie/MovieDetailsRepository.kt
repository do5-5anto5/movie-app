package com.do55anto5.movieapp.domain.repository.movie

import com.do55anto5.movieapp.data.model.movie.CreditsResponse
import com.do55anto5.movieapp.data.model.movie.MovieResponse
import com.do55anto5.movieapp.data.model.movie.MovieReviewResponse

interface MovieDetailsRepository {

    suspend fun getMovieDetails(movieId: Int?): MovieResponse

    suspend fun getMovieCredits(movieId: Int?): CreditsResponse

    suspend fun getSimilar(movieId: Int?): List<MovieResponse>

    suspend fun getMovieReviews(movieId: Int?): List<MovieReviewResponse>

}
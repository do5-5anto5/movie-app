package com.do55anto5.movieapp.data.repository.movie

import com.do55anto5.movieapp.data.api.ServiceApi
import com.do55anto5.movieapp.data.model.CreditsResponse
import com.do55anto5.movieapp.data.model.MovieResponse
import com.do55anto5.movieapp.data.model.MovieReviewResponse
import com.do55anto5.movieapp.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
): MovieDetailsRepository {
    override suspend fun getMovieDetails(
        movieId: Int?
    ): MovieResponse {
        return serviceApi.getMovieDetails(
            movieId = movieId
        )
    }

    override suspend fun getMovieCredits(
        movieId: Int?
    ): CreditsResponse {
        return serviceApi.getMovieCredits(
            movieId = movieId
        )
    }

    override suspend fun getSimilar(
        movieId: Int?
    ): List<MovieResponse> {
        return serviceApi.getSimilar(
            movieId = movieId
        ).results ?: emptyList()
    }

    override suspend fun getMovieReviews(
        movieId: Int?
    ): List<MovieReviewResponse> {
        return serviceApi.getMovieReviews(
            movieId = movieId
        ).results ?: emptyList()
    }
}
package com.do55anto5.movieapp.data.repository.movie

import com.do55anto5.movieapp.data.api.ServiceApi
import com.do55anto5.movieapp.data.model.CreditsResponse
import com.do55anto5.movieapp.data.model.MovieResponse
import com.do55anto5.movieapp.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
): MovieDetailsRepository {
    override suspend fun getMovieDetails(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ): MovieResponse {
        return serviceApi.getMovieDetails(
            movieId = movieId,
            apiKey = apiKey,
            language = language
        )
    }

    override suspend fun getMovieCredits(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ): CreditsResponse {
        return serviceApi.getMovieCredits(
            movieId = movieId,
            apiKey = apiKey,
            language = language
        )
    }
}
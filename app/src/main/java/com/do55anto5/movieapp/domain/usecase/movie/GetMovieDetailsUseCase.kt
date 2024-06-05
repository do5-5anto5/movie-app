package com.do55anto5.movieapp.domain.usecase.movie

import com.do55anto5.movieapp.data.mapper.toDomain
import com.do55anto5.movieapp.domain.model.Movie
import com.do55anto5.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(
        apiKey: String, language: String?, movieId: Int?
    ): Movie {
        return repository.getMovieDetails(
            apiKey = apiKey,
            language = language,
            movieId = movieId
        ).toDomain()
    }

}
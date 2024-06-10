package com.do55anto5.movieapp.domain.usecase.movie

import com.do55anto5.movieapp.data.mapper.toDomain
import com.do55anto5.movieapp.domain.model.Movie
import com.do55anto5.movieapp.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject

class GetSimilarUseCase @Inject constructor(
    private val repository: MovieDetailsRepository
) {

    suspend operator fun invoke(
        apiKey: String, language: String?, movieId: Int?
    ): List<Movie> {
        return repository.getSimilar(
            apiKey = apiKey,
            language = language,
            movieId = movieId
        ).map {it.toDomain() }.filter { it.posterPath != null }
    }

}
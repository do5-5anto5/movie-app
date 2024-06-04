package com.do55anto5.movieapp.domain.usecase.movie

import com.do55anto5.movieapp.data.mapper.toDomain
import com.do55anto5.movieapp.domain.model.Movie
import com.do55anto5.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(
        apiKey: String, language: String?, query: String?
    ): List<Movie> {
        return repository.searchMovies(
            apiKey = apiKey,
            language = language,
            query = query
        ).filter { it.backdropPath != null }.map { it.toDomain() }
    }

}
package com.do55anto5.movieapp.domain.usecase.movie

import com.do55anto5.movieapp.data.mapper.toDomain
import com.do55anto5.movieapp.domain.model.movie.Movie
import com.do55anto5.movieapp.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject

class GetSimilarUseCase @Inject constructor(
    private val repository: MovieDetailsRepository
) {

    suspend operator fun invoke(movieId: Int?
    ): List<Movie> {
        return repository.getSimilar(
            movieId = movieId
        ).map {it.toDomain() }
            .filter { it.posterPath != null }
    }

}
package com.do55anto5.movieapp.domain.local.usecase

import com.do55anto5.movieapp.data.mapper.toDomain
import com.do55anto5.movieapp.domain.local.repository.MovieLocalRepository
import com.do55anto5.movieapp.domain.model.movie.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieLocalRepository
) {

    operator fun invoke(): Flow<List<Movie>> {
        return repository.getMovies().map { moviesList ->
            moviesList.map { it.toDomain() }
        }
    }

}
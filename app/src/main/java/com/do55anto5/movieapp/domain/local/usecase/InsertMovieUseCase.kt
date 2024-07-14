package com.do55anto5.movieapp.domain.local.usecase

import com.do55anto5.movieapp.data.mapper.toEntity
import com.do55anto5.movieapp.domain.local.repository.MovieLocalRepository
import com.do55anto5.movieapp.domain.model.movie.Movie
import javax.inject.Inject

class InsertMovieUseCase @Inject constructor(
    private val repository: MovieLocalRepository
) {

    suspend operator fun invoke(movie: Movie) {
        return repository.insertMovie(movie.toEntity())
    }

}
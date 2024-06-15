package com.do55anto5.movieapp.domain.local.usecase

import com.do55anto5.movieapp.domain.local.repository.MovieLocalRepository
import javax.inject.Inject

class DeleteMovieUseCase @Inject constructor(
    private val repository: MovieLocalRepository
) {

    suspend operator fun invoke(movieId: Int?) {
        return repository.deleteMovie(movieId)
    }

}
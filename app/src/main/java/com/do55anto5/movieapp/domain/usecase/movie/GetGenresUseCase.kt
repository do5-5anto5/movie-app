package com.do55anto5.movieapp.domain.usecase.movie

import com.do55anto5.movieapp.data.mapper.toDomain
import com.do55anto5.movieapp.domain.model.movie.Genre
import com.do55anto5.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(): List<Genre> {
        return repository.getGenres().genres.map { it.toDomain() }
    }

}
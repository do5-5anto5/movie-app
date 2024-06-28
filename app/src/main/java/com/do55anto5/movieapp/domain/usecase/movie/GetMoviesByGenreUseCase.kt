package com.do55anto5.movieapp.domain.usecase.movie

import com.do55anto5.movieapp.data.model.MovieResponse
import com.do55anto5.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetMoviesByGenreUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(genreId: Int?): List<MovieResponse> {
        return repository.getMoviesByGenre(genreId).results ?: emptyList()
    }

}
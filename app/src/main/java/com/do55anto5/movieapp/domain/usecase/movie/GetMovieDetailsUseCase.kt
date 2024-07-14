package com.do55anto5.movieapp.domain.usecase.movie

import com.do55anto5.movieapp.data.mapper.toDomain
import com.do55anto5.movieapp.domain.model.movie.Movie
import com.do55anto5.movieapp.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieDetailsRepository
) {

    suspend operator fun invoke(movieId: Int?): Movie {
        return repository.getMovieDetails(
            movieId = movieId
        ).toDomain()
    }

}
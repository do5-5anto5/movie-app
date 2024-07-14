package com.do55anto5.movieapp.domain.usecase.movie

import com.do55anto5.movieapp.data.mapper.toDomain
import com.do55anto5.movieapp.domain.model.movie.Credits
import com.do55anto5.movieapp.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject

class GetCreditsUseCase @Inject constructor(
    private val repository: MovieDetailsRepository
) {

    suspend operator fun invoke(movieId: Int?): Credits {
        return repository.getMovieCredits(
            movieId = movieId
        ).toDomain()
    }

}
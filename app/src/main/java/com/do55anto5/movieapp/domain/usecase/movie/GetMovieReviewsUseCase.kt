package com.do55anto5.movieapp.domain.usecase.movie

import com.do55anto5.movieapp.data.mapper.toDomain
import com.do55anto5.movieapp.domain.model.MovieReview
import com.do55anto5.movieapp.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject

class GetMovieReviewsUseCase @Inject constructor(
    private val repository: MovieDetailsRepository
) {

    suspend operator fun invoke(movieId: Int?): List<MovieReview> {
        return repository.getMovieReviews(
            movieId = movieId
        ).map { it.toDomain() }
    }

}
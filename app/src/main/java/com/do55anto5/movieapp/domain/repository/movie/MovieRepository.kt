package com.do55anto5.movieapp.domain.repository.movie

import com.do55anto5.movieapp.data.model.GenresResponse
import com.do55anto5.movieapp.data.model.MovieResponse

interface MovieRepository {

    suspend fun getGenres(apiKey: String, language: String?): GenresResponse

    suspend fun getMoviesByGenre(
        apiKey: String,
        language: String?,
        genreId: Int?
    ): List<MovieResponse>
}
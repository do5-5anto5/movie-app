package com.do55anto5.movieapp.domain.repository.movie

import androidx.paging.PagingSource
import com.do55anto5.movieapp.data.model.GenresResponse
import com.do55anto5.movieapp.data.model.MovieResponse

interface MovieRepository {

    suspend fun getGenres(apiKey: String?, language: String?): GenresResponse

    fun getMoviesByGenre(
        apiKey: String?,
        language: String?,
        genreId: Int?
    ): PagingSource<Int, MovieResponse>

    fun searchMovies(
        apiKey: String?,
        language: String?,
        query: String?
    ) : PagingSource<Int, MovieResponse>

}
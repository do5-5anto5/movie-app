package com.do55anto5.movieapp.domain.repository.movie

import androidx.paging.PagingSource
import com.do55anto5.movieapp.data.model.GenresResponse
import com.do55anto5.movieapp.data.model.MovieResponse

interface MovieRepository {

    suspend fun getGenres(): GenresResponse

    fun getMoviesByGenre(
        genreId: Int?
    ): PagingSource<Int, MovieResponse>

    fun searchMovies(query: String?): PagingSource<Int, MovieResponse>

}
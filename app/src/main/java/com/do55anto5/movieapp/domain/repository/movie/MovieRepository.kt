package com.do55anto5.movieapp.domain.repository.movie

import androidx.paging.PagingSource
import com.do55anto5.movieapp.data.model.GenresResponse
import com.do55anto5.movieapp.data.model.MovieResponse
import com.do55anto5.movieapp.data.model.RemoteBasePagination

interface MovieRepository {

    suspend fun getGenres(): GenresResponse

    fun getMoviesByGenrePagination(genreId: Int?): PagingSource<Int, MovieResponse>

    suspend fun getMoviesByGenre(genreId: Int?): RemoteBasePagination<List<MovieResponse>>

    fun searchMovies(query: String?): PagingSource<Int, MovieResponse>

}
package com.do55anto5.movieapp.data.repository.movie

import androidx.paging.PagingSource
import com.do55anto5.movieapp.data.api.ServiceApi
import com.do55anto5.movieapp.data.model.GenresResponse
import com.do55anto5.movieapp.data.model.MovieResponse
import com.do55anto5.movieapp.data.paging.MovieByGenrePagingSource
import com.do55anto5.movieapp.data.paging.SearchMoviePagingSource
import com.do55anto5.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
): MovieRepository {
    override suspend fun getGenres(): GenresResponse {
        return serviceApi.getGenres()
    }

    override fun getMoviesByGenre(genreId: Int?): PagingSource<Int, MovieResponse> {
        return MovieByGenrePagingSource(serviceApi, genreId)
    }

    override fun searchMovies(query: String?): PagingSource<Int, MovieResponse> {
        return SearchMoviePagingSource(serviceApi, query)
    }

}
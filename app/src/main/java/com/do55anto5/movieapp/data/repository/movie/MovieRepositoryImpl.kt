package com.do55anto5.movieapp.data.repository.movie

import com.do55anto5.movieapp.data.api.ServiceApi
import com.do55anto5.movieapp.data.model.GenresResponse
import com.do55anto5.movieapp.data.model.MovieResponse
import com.do55anto5.movieapp.domain.repository.movie.MovieRepository
import com.do55anto5.movieapp.util.Constants
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
): MovieRepository {
    override suspend fun getGenres(apiKei: String, language: String?): GenresResponse {
        return serviceApi.getGenres(
            apiKey = apiKei,
            language = Constants.Movie.LANGUAGE)
    }

    override suspend fun getMoviesByGenre(
        apiKey: String,
        language: String?,
        genreId: Int
    ): List<MovieResponse> {

        return serviceApi.getMoviesByGenre(
            apiKey = apiKey,
            language = language,
            genreId = genreId
        ).results ?: emptyList()
    }


}
package com.do55anto5.movieapp.data.api

import com.do55anto5.movieapp.data.model.movie.CreditsResponse
import com.do55anto5.movieapp.data.model.movie.GenresResponse
import com.do55anto5.movieapp.data.model.movie.MovieResponse
import com.do55anto5.movieapp.data.model.movie.MovieReviewResponse
import com.do55anto5.movieapp.data.model.movie.RemoteBasePagination
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {

    @GET("genre/movie/list")
    suspend fun getGenres(
    ) : GenresResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenrePagination(
        @Query("with_genres") genreId: Int?,
        @Query("page") page: Int?
    ) : RemoteBasePagination<List<MovieResponse>>

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("with_genres") genreId: Int?
    ) : RemoteBasePagination<List<MovieResponse>>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String?,
        @Query("page") page: Int?
    ) : RemoteBasePagination<List<MovieResponse>>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int?,
    ) : MovieResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int?,
    ) : CreditsResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(
        @Path("movie_id") movieId: Int?,
    ) : RemoteBasePagination<List<MovieResponse>>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int?,
    ) : RemoteBasePagination<List<MovieReviewResponse>>

}
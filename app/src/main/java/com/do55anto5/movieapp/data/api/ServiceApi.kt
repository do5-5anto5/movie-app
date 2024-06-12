package com.do55anto5.movieapp.data.api

import com.do55anto5.movieapp.data.model.CreditsResponse
import com.do55anto5.movieapp.data.model.GenresResponse
import com.do55anto5.movieapp.data.model.MovieResponse
import com.do55anto5.movieapp.data.model.MovieReviewResponse
import com.do55anto5.movieapp.data.model.RemoteBasePagination
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ) : GenresResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("with_genres") genreId: Int?
    ) : RemoteBasePagination<List<MovieResponse>>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("query") query: String?
    ) : RemoteBasePagination<List<MovieResponse>>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ) : MovieResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ) : CreditsResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(
        @Path("movie_id") movieId: Int?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ) : RemoteBasePagination<List<MovieResponse>>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ) : RemoteBasePagination<List<MovieReviewResponse>>

}
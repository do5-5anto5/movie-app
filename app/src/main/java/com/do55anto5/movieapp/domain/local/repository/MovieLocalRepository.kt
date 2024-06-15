package com.do55anto5.movieapp.domain.local.repository

import com.do55anto5.movieapp.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieLocalRepository {

    fun getMovies() : Flow<List<MovieEntity>>

    suspend fun insertMovie(movieEntity: MovieEntity)

    suspend fun deleteMovie(movieId : Int?)

}
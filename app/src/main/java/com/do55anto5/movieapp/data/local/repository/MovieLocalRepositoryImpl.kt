package com.do55anto5.movieapp.data.local.repository

import com.do55anto5.movieapp.data.local.dao.MovieDao
import com.do55anto5.movieapp.data.local.entity.MovieEntity
import com.do55anto5.movieapp.domain.local.repository.MovieLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieLocalRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieLocalRepository {
    override fun getMovies(): Flow<List<MovieEntity>> {
        return movieDao.getMovies()
    }

    override suspend fun insertMovie(movieEntity: MovieEntity) {
        movieDao.insertMovies(movieEntity)
    }

    override suspend fun deleteMovie(movieId: Int?) {
        movieDao.deleteMovie(movieId)
    }
}
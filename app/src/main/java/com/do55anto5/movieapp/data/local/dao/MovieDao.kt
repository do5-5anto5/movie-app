package com.do55anto5.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.do55anto5.movieapp.data.local.entity.MovieEntity
import com.do55anto5.movieapp.util.DBConstants.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM ${Tables.MOVIE_TABLE} ORDER BY ${Columns.MOVIE_INSERTION_COLUMN} DESC")
    fun getMovies() : Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movieEntity: MovieEntity)

    @Query("DELETE FROM ${Tables.MOVIE_TABLE} WHERE ${Columns.MOVIE_ID_COLUMN} = :movieId")
    suspend fun deleteMovie(movieId : Int?)

}
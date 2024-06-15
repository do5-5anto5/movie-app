package com.do55anto5.movieapp.dependency_injection

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.do55anto5.movieapp.data.local.dao.MovieDao
import com.do55anto5.movieapp.data.local.db.AppDatabase
import com.do55anto5.movieapp.util.DBConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context
    ) : RoomDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DBConstants.Database.MOVIE_DATABASE
    ).build()

    @Provides
    fun providesMovieDao(database: AppDatabase) : MovieDao = database.movieDao()

}
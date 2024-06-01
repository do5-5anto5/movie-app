package com.do55anto5.movieapp.dependency_injection

import com.do55anto5.movieapp.data.repository.auth.FirebaseAuthenticationImpl
import com.do55anto5.movieapp.data.repository.movie.MovieRepositoryImpl
import com.do55anto5.movieapp.domain.repository.auth.FirebaseAuthentication
import com.do55anto5.movieapp.domain.repository.movie.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsFirebaseAuthenticationImpl(
        firebaseAuthenticationImpl: FirebaseAuthenticationImpl
    ): FirebaseAuthentication

    @Binds
    abstract fun bindsMovieRepositoryImpl(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}
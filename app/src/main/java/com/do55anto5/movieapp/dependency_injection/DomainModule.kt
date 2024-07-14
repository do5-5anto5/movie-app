package com.do55anto5.movieapp.dependency_injection

import com.do55anto5.movieapp.data.local.repository.MovieLocalRepositoryImpl
import com.do55anto5.movieapp.data.repository.auth.FirebaseAuthenticationImpl
import com.do55anto5.movieapp.data.repository.movie.MovieDetailsRepositoryImpl
import com.do55anto5.movieapp.data.repository.movie.MovieRepositoryImpl
import com.do55anto5.movieapp.data.repository.user.UserRepositoryImpl
import com.do55anto5.movieapp.domain.local.repository.MovieLocalRepository
import com.do55anto5.movieapp.domain.repository.auth.FirebaseAuthentication
import com.do55anto5.movieapp.domain.repository.movie.MovieDetailsRepository
import com.do55anto5.movieapp.domain.repository.movie.MovieRepository
import com.do55anto5.movieapp.domain.repository.user.UserRepository
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

    @Binds
    abstract fun bindsMovieDetailsRepositoryImpl(
        movieDetailsRepositoryImpl: MovieDetailsRepositoryImpl
    ): MovieDetailsRepository

    @Binds
    abstract fun bindsMovieLocalRepositoryImpl(
        movieDetailsRepositoryImpl: MovieLocalRepositoryImpl
    ): MovieLocalRepository

    @Binds
    abstract fun bindsUserRepositoryImpl(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}
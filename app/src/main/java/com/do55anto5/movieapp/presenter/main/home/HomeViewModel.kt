package com.do55anto5.movieapp.presenter.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.do55anto5.movieapp.BuildConfig
import com.do55anto5.movieapp.data.mapper.toPresentation
import com.do55anto5.movieapp.domain.usecase.movie.GetGenresUseCase
import com.do55anto5.movieapp.domain.usecase.movie.GetMoviesByGenreUseCase
import com.do55anto5.movieapp.util.Constants
import com.do55anto5.movieapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase,
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase
): ViewModel() {

    fun getGenres() = liveData(Dispatchers.IO) {
        try {

            emit(StateView.Loading())

            val genres = getGenresUseCase.invoke(
                BuildConfig.API_KEY,
                Constants.Movie.LANGUAGE
            ).map { it.toPresentation() }


            emit(StateView.Success(genres))

        } catch (e: HttpException) {
            e.printStackTrace()
            emit(StateView.Error(e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(e.message))
        }
    }

}
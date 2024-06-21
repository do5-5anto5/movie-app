package com.do55anto5.movieapp.presenter.main.movie_genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.paging.PagingData
import com.do55anto5.movieapp.BuildConfig
import com.do55anto5.movieapp.domain.model.Movie
import com.do55anto5.movieapp.domain.usecase.movie.GetMoviesByGenreUseCase
import com.do55anto5.movieapp.domain.usecase.movie.SearchMoviesUseCase
import com.do55anto5.movieapp.util.Constants
import com.do55anto5.movieapp.util.Constants.Movie.LANGUAGE
import com.do55anto5.movieapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MovieGenreViewModel @Inject constructor(
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel(){

    private val _moviesList = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val moviesList
        get () = _moviesList.asStateFlow()

    private var currentGenreId: Int? = null

    fun initializeGetMoviesByGenre(genreId: Int?, forceRequest: Boolean) {
        if (genreId != currentGenreId || forceRequest) {
            currentGenreId = genreId
            getMoviesByGenreUseCase(
                apiKey = BuildConfig.API_KEY,
                language = LANGUAGE,
                genreId = genreId
            )
        }
    }

    fun searchMovies(query: String?) = liveData(Dispatchers.IO) {
        try {

            emit(StateView.Loading())

            val movies = searchMoviesUseCase.invoke(
                apiKey = BuildConfig.API_KEY,
                language = LANGUAGE,
                query = query
            )


            emit(StateView.Success(movies))

        } catch (e: HttpException) {
            e.printStackTrace()
            emit(StateView.Error(e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(e.message))
        }
    }

}
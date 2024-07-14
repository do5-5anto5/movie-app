package com.do55anto5.movieapp.presenter.main.bottom_bar.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.do55anto5.movieapp.data.mapper.toDomain
import com.do55anto5.movieapp.domain.model.movie.Genre
import com.do55anto5.movieapp.domain.usecase.movie.GetGenresUseCase
import com.do55anto5.movieapp.domain.usecase.movie.GetMoviesByGenreUseCase
import com.do55anto5.movieapp.presenter.model.MoviesByGenre
import com.do55anto5.movieapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase,
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase
) : ViewModel() {

    private val _moviesList = MutableLiveData<List<MoviesByGenre>>()
    val moviesList: LiveData<List<MoviesByGenre>> get() = _moviesList

    private val _homeState = MutableLiveData<StateView<Unit>>()
    val homeState: LiveData<StateView<Unit>> get() = _homeState

    init {
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            try {
                _homeState.postValue(StateView.Loading())

                val genres = getGenresUseCase()
                getMoviesByGenre(genres)

            } catch (e: HttpException) {
                e.printStackTrace()
                _homeState.postValue(StateView.Error(e.message))
            }
        }
    }

    private fun getMoviesByGenre(genres: List<Genre>) {
        val moviesByGenre: MutableList<MoviesByGenre> = mutableListOf()
        viewModelScope.launch {
            genres.forEach { genre ->
                try {
                    val movies = getMoviesByGenreUseCase(genre.id)
                    val movieByGenre = MoviesByGenre(
                        id = genre.id,
                        name = genre.name,
                        movies = movies.map { it.toDomain() }.take(5)
                    )
                    moviesByGenre.add(movieByGenre)

                    if(moviesByGenre.size == genres.size) {
                        _moviesList.postValue(moviesByGenre)
                        _homeState.postValue(StateView.Success(Unit))
                    }

                } catch (e: HttpException) {
                    e.printStackTrace()
                    _homeState.postValue(StateView.Error(e.message))
                }
            }
        }
    }

}
package com.do55anto5.movieapp.presenter.main.bottom_bar.download

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.do55anto5.movieapp.domain.local.usecase.DeleteMovieUseCase
import com.do55anto5.movieapp.domain.local.usecase.GetMoviesUseCase
import com.do55anto5.movieapp.domain.model.movie.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase
): ViewModel() {

    private val _moviesList = MutableLiveData(mutableListOf<Movie>())
    var moviesList: LiveData<MutableList<Movie>> = _moviesList

    private val _moviesSearchList = MutableLiveData(mutableListOf<Movie>())
    var moviesSearchList: LiveData<MutableList<Movie>> = _moviesSearchList

    fun getMovies() = viewModelScope.launch {
        getMoviesUseCase().collect{ movies ->
            _moviesList.postValue(movies.toMutableList())
        }
    }

    fun deleteMovie(movieId: Int?) = viewModelScope.launch {
        deleteMovieUseCase(movieId)

        val newMoviesList = _moviesList.value?.apply {
            removeIf { it.id == movieId }
        }

       _moviesList.postValue(newMoviesList)
    }

    fun searchMovie(search: String) = viewModelScope.launch {
        val newMoviesList = _moviesList.value?.filter {
            it.title?.contains(search, true) == true
        }
        _moviesSearchList.postValue(newMoviesList?.toMutableList())
    }

}
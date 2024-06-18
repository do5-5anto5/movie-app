package com.do55anto5.movieapp.presenter.main.bottom_bar.download

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.do55anto5.movieapp.domain.local.usecase.GetMoviesUseCase
import com.do55anto5.movieapp.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
): ViewModel() {

    private val _moviesList = MutableLiveData(mutableListOf<Movie>())
    var moviesList: LiveData<MutableList<Movie>> = _moviesList

    fun getMovies() = viewModelScope.launch {
        getMoviesUseCase().collect{ movies ->
            _moviesList.postValue(movies.toMutableList())
        }
    }

}
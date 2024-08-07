package com.do55anto5.movieapp.presenter.main.movie_genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.do55anto5.movieapp.domain.model.movie.Movie
import com.do55anto5.movieapp.domain.usecase.movie.GetMoviesByGenrePaginationUseCase
import com.do55anto5.movieapp.domain.usecase.movie.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieGenreViewModel @Inject constructor(
    private val getMoviesByGenrePaginationUseCase: GetMoviesByGenrePaginationUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel(){

    private val _moviesList = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val moviesList
        get () = _moviesList.asStateFlow()

    private var currentGenreId: Int? = null

    fun getMoviesByGenrePagination(genreId: Int?, forceRequest: Boolean)  = viewModelScope.launch {
        if (genreId != currentGenreId || forceRequest) {
            currentGenreId = genreId
            getMoviesByGenrePaginationUseCase(
                genreId = genreId
            ).cachedIn(viewModelScope).collectLatest {
                _moviesList.emit(it)
            }
        }
    }

    fun searchMovies(query: String?): Flow<PagingData<Movie>> {
        return searchMoviesUseCase(
            query = query
        ).cachedIn(viewModelScope)
    }

}
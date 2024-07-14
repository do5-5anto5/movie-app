package com.do55anto5.movieapp.presenter.main.bottom_bar.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.do55anto5.movieapp.domain.model.movie.Movie
import com.do55anto5.movieapp.domain.usecase.movie.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    fun searchMovies(query: String?): Flow<PagingData<Movie>> {
        return searchMoviesUseCase(
            query = query
        ).cachedIn(viewModelScope)
    }
}
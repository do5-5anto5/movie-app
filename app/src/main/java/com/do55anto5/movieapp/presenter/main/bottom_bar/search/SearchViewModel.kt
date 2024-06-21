package com.do55anto5.movieapp.presenter.main.bottom_bar.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.do55anto5.movieapp.BuildConfig.API_KEY
import com.do55anto5.movieapp.domain.model.Movie
import com.do55anto5.movieapp.domain.usecase.movie.SearchMoviesUseCase
import com.do55anto5.movieapp.util.Constants.Movie.LANGUAGE
import com.do55anto5.movieapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    private val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>> get() = _moviesList

    private val _searchState = MutableLiveData<StateView<Unit>>()
    val searchState: LiveData<StateView<Unit>> get() = _searchState

    fun searchMovies(query: String?) {
       viewModelScope.launch {
           try {

               _searchState.postValue(StateView.Loading())

               val movies = searchMoviesUseCase(
                   apiKey = API_KEY,
                   language = LANGUAGE,
                   query = query
               )
               _moviesList.postValue(movies)

               _searchState.postValue(StateView.Success(Unit))

           } catch (e: HttpException) {
               e.printStackTrace()
               _searchState.postValue(StateView.Error(e.message))
           } catch (e: Exception) {
               e.printStackTrace()
               _searchState.postValue(StateView.Error(e.message))
           }
       }
    }
}
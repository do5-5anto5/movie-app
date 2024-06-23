package com.do55anto5.movieapp.presenter.main.movie_details.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.do55anto5.movieapp.domain.usecase.movie.GetMovieReviewsUseCase
import com.do55anto5.movieapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val getCommentsUseCase: GetMovieReviewsUseCase
) : ViewModel() {

    fun getMovieReviews(movieId: Int?) = liveData(Dispatchers.IO) {
        try {

            emit(StateView.Loading())

            val genres = getCommentsUseCase.invoke(
                movieId = movieId
            )

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
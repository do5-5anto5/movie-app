package com.do55anto5.movieapp.presenter.auth.forgot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.do55anto5.movieapp.domain.usecase.auth.ForgotUseCase
import com.do55anto5.movieapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ForgotViewModel @Inject constructor(
    private val forgotUseCase: ForgotUseCase
) : ViewModel() {

    fun forgot(email: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            forgotUseCase.invoke(email)

            emit(StateView.Success(Unit))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(e.message))
        }
    }

}
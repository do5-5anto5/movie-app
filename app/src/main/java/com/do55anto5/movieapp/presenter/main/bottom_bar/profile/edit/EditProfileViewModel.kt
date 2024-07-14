package com.do55anto5.movieapp.presenter.main.bottom_bar.profile.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.do55anto5.movieapp.domain.model.user.User
import com.do55anto5.movieapp.domain.usecase.user.GetUserUseCase
import com.do55anto5.movieapp.domain.usecase.user.UserUpdateUseCase
import com.do55anto5.movieapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userUpdateUseCase: UserUpdateUseCase,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    fun update(user: User) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            userUpdateUseCase(user)

            kotlinx.coroutines.delay(3000)

            emit(StateView.Success(Unit))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(e.message))
        }
    }
    fun get() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val user = getUserUseCase()

            kotlinx.coroutines.delay(3000)

            emit(StateView.Success(user))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(e.message))
        }
    }

}
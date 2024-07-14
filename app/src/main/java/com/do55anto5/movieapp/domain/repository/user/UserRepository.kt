package com.do55anto5.movieapp.domain.repository.user

import com.do55anto5.movieapp.domain.model.user.User

interface UserRepository {

    suspend fun update(user: User)

}
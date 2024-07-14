package com.do55anto5.movieapp.data.model.movie

import com.google.gson.annotations.SerializedName

data class AuthorDetailsResponse(

    @SerializedName("name")
    val name: String?,

    @SerializedName("username")
    val username: String?,

    @SerializedName("avatar_path")
    val avatarPath: String?,

    @SerializedName("rating")
    val rating: Int?
)

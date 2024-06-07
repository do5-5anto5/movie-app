package com.do55anto5.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class CreditsResponse(
    @SerializedName("cast")
    val cast: List<PersonResponse>?
)

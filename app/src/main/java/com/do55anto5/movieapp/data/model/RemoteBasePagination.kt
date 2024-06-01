package com.do55anto5.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class RemoteBasePagination<out T>(

    @SerializedName("page")
    val page: Int?,

    @SerializedName("results")
    val results:T?,

    @SerializedName("total_results")
    val totalResults: Int?,

    @SerializedName("total_pages")
    val totalPages: Int?
)

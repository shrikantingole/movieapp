package com.network.shared.domain.exception.movie


import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@kotlinx.parcelize.Parcelize
data class MovieListRes(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var results: List<MovieResult>?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
) : Parcelable
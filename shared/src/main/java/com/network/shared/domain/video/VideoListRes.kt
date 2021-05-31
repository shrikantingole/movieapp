package com.network.shared.domain.video


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoListRes(
    @SerializedName("resultCount")
    var resultCount: Int?,
    @SerializedName("results")
    var results: List<VideoItem>?
) : Parcelable
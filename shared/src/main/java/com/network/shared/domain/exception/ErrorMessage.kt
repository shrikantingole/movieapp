package com.network.shared.domain.exception


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorMessage(
    @SerializedName("code")
    var code: Int? = null,

    @SerializedName("error")
    var errors: String? = null,
) : Parcelable, Exception() {
    override fun toString(): String {
        return super.toString()
    }
}
package com.ex.scandanggerarea.data.model


import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("data")
    val `data`: Data
) {
    data class Data(
        @SerializedName("email")
        val email: String,
        @SerializedName("token")
        val token: String,
        @SerializedName("userID")
        val userID: Int
    )
}
package com.ex.scandanggerarea.data.model


import com.google.gson.annotations.SerializedName

data class BodyLogin(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
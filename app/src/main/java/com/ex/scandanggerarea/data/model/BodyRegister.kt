package com.ex.scandanggerarea.data.model


import com.google.gson.annotations.SerializedName

data class BodyRegister(
    @SerializedName("address")
    val address: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("nik")
    val nik: String,
    @SerializedName("password")
    val password: String
)
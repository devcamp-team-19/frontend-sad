package com.ex.scandanggerarea.data.model


import com.google.gson.annotations.SerializedName

data class BodyFeeds(
    @SerializedName("category")
    val category: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("eventTitle")
    val eventTitle: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("radius")
    val radius: Int
)
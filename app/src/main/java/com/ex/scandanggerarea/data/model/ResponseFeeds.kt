package com.ex.scandanggerarea.data.model


import com.google.gson.annotations.SerializedName

data class ResponseFeeds(
    @SerializedName("data")
    val `data`: List<Data>
) {
    data class Data(
        @SerializedName("category")
        val category: Int,
        @SerializedName("comments")
        val comments: Any,
        @SerializedName("CreatedAt")
        val createdAt: String,
        @SerializedName("DeletedAt")
        val deletedAt: Any,
        @SerializedName("description")
        val description: String,
        @SerializedName("eventTitle")
        val eventTitle: String,
        @SerializedName("ID")
        val iD: Int,
        @SerializedName("imageUrl")
        val imageUrl: String,
        @SerializedName("latitude")
        val latitude: String,
        @SerializedName("longitude")
        val longitude: String,
        @SerializedName("radius")
        val radius: Double,
        @SerializedName("UpdatedAt")
        val updatedAt: String,
        @SerializedName("UserID")
        val userID: Int,
        @SerializedName("userVotes")
        val userVotes: Any
    )
}
package com.ex.scandanggerarea.data.remote

import com.ex.scandanggerarea.data.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("api/v1/login")
    fun login(@Body bodyLogin: BodyLogin): Single<ResponseLogin>

    @POST("api/v1/register")
    fun register(@Body bodyRegister: BodyRegister): Single<ResponseRegister>

    @GET("api/v1/reports")
    fun feeds(@Header("Token") token: String): Single<ResponseFeeds>
}
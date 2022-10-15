package com.ex.scandanggerarea.data

import com.ex.scandanggerarea.data.model.*
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun login(bodyLogin: BodyLogin): Single<ResponseLogin>
    fun register(bodyRegister: BodyRegister): Single<ResponseRegister>
    fun getFeeds(): Single<ResponseFeeds>
    fun isLogin(): Boolean
}
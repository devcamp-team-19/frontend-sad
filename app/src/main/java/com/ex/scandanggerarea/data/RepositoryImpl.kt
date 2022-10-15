package com.ex.scandanggerarea.data

import com.ex.scandanggerarea.data.local.SharedPref
import com.ex.scandanggerarea.data.model.*
import com.ex.scandanggerarea.data.remote.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryImpl constructor(
    private val service: ApiService,
    private val sharedPref: SharedPref
) : Repository {
    override fun login(bodyLogin: BodyLogin): Single<ResponseLogin> =
        service.login(bodyLogin)
            .singleIo()
            .map {
                sharedPref.token = it.data.token
                sharedPref.isLogin = true
                it
            }

    override fun register(bodyRegister: BodyRegister): Single<ResponseRegister> =
        service.register(bodyRegister)
            .singleIo()

    override fun getFeeds(): Single<ResponseFeeds> =
        service.feeds(sharedPref.token)
            .singleIo()

    override fun isLogin(): Boolean = sharedPref.isLogin

}


fun <T : Any> Single<T>.singleIo(): Single<T> = compose {
    it.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
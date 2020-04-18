package com.lloyd.shwethalkitchen.view.network

import com.lloyd.shwethalkitchen.view.models.LoginRequestBody
import com.lloyd.shwethalkitchen.view.models.LoginResponseModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("/login")
    fun getLoginResponse(@Body loginRequestBody: LoginRequestBody): Single<LoginResponseModel>
}
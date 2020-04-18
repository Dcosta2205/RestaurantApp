package com.lloyd.shwethalkitchen.view.repositories

import com.lloyd.shwethalkitchen.view.models.LoginRequestBody
import com.lloyd.shwethalkitchen.view.models.LoginResponseModel
import com.lloyd.shwethalkitchen.view.network.ApiInterface
import com.lloyd.shwethalkitchen.view.network.RetrofitService
import io.reactivex.Single

class LoginRepository {

    private val apiInterface =
        RetrofitService.getRetrofitService().create(ApiInterface::class.java)

    fun callLoginApi(loginRequestBody: LoginRequestBody): Single<LoginResponseModel> {
        return apiInterface.getLoginResponse(loginRequestBody)
    }
}
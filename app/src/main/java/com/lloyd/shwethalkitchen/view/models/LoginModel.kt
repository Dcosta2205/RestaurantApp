package com.lloyd.shwethalkitchen.view.models

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("status") val status: String,
    @SerializedName("reason") val reason: String?
)

data class LoginRequestBody(val email: String?, val password: String?)
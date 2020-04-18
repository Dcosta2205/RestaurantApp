package com.lloyd.shwethalkitchen.view.models

sealed class LoginUIModel {
    class OnSuccess(val s :String) : LoginUIModel()

    class OnFailure(val s: String?) : LoginUIModel()
}
package com.lloyd.shwethalkitchen.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lloyd.shwethalkitchen.view.models.LoginRequestBody
import com.lloyd.shwethalkitchen.view.models.LoginResponseModel
import com.lloyd.shwethalkitchen.view.models.LoginUIModel
import com.lloyd.shwethalkitchen.view.repositories.LoginRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel() : ViewModel() {

    private val loginRepository = LoginRepository()
    private var mutableLiveData: MutableLiveData<LoginUIModel> = MutableLiveData()
    val liveData: LiveData<LoginUIModel> = mutableLiveData
    private var disposable: Disposable? = null

    fun callLoginApi(loginRequestBody: LoginRequestBody) {
        disposable = loginRepository
            .callLoginApi(loginRequestBody)
            /*    .map<LoginUIModel> {
                    return@map LoginUIModel.OnSuccess(it.reason!!)
                }
                .onErrorReturn {
                   return@onErrorReturn LoginUIModel.OnFailure(it.localizedMessage!!)
                }*/
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe({
                mutableLiveData.value = LoginUIModel.OnSuccess(it.status)
            }, {
                mutableLiveData.value = LoginUIModel.OnFailure(it?.localizedMessage)
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}
package com.lloyd.shwethalkitchen.view.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private val base_url: String = "http://ec2-18-223-125-139.us-east-2.compute.amazonaws.com:5000"
    private lateinit var mRetrofit: Retrofit

    fun getRetrofitService(): Retrofit {
        if (::mRetrofit.isInitialized.not()) {
            return Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build()
                ).build()
        }
        return mRetrofit
    }
}
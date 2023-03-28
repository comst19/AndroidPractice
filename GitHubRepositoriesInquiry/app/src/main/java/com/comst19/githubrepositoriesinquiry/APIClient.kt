package com.comst19.githubrepositoriesinquiry

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("Authorization","Bearer <your token>")
                .build()
            it.proceed(request)
        }
        .build()

    private  const val BASE_URL = "https://api.github.com/"
    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        //.client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}

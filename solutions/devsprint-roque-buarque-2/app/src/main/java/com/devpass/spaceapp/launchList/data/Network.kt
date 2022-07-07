package com.devpass.spaceapp.launchList.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Network {

    fun retrofitInstance(): Retrofit {

        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    inline fun <reified T> createService () : T {
        return retrofitInstance().create()
    }
}
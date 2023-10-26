package com.example.springbootquiz.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Utils.base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }


}
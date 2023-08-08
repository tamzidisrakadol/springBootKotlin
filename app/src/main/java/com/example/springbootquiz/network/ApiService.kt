package com.example.springbootquiz.network

import com.example.springbootquiz.modal.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/auth/login")
    fun userLogin(@Query(value = "email")email:String,@Query(value = "password")password:String):Call<LoginResponse>


    companion object Builder{
        private const val baseURl="http://192.168.31.131:8080"
        fun create():ApiService{
            val retrofit=Retrofit.Builder()
                .baseUrl(baseURl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}
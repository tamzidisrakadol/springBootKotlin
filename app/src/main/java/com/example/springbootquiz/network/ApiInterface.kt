package com.example.springbootquiz.network

import com.example.springbootquiz.modal.LoginResponse
import com.example.springbootquiz.modal.UserLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("/auth/login")
    fun getAccessLoginInfo(@Body loginData:UserLogin): Call<LoginResponse>
}
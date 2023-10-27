package com.example.springbootquiz.network

import com.example.springbootquiz.modal.CustomerInfo
import com.example.springbootquiz.modal.LoginResponse
import com.example.springbootquiz.modal.UserLogin
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    @POST("/auth/login")
    suspend fun getAccessLoginInfo(@Body loginData:UserLogin): Response<LoginResponse>

    @GET("/home/admin/showall")
    suspend fun getAllCustomerInfo(@Header("Authorization") jwtToken:String):Response<List<CustomerInfo>>
}
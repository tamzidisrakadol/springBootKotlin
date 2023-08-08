package com.example.springbootquiz.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.springbootquiz.R
import com.example.springbootquiz.databinding.ActivityMain2Binding
import org.json.JSONException
import org.json.JSONObject

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.loginBtn.setOnClickListener {

            var email = binding.emailETV.text?.toString()
            var password=binding.passETV.text?.toString()


            if (email != null && password != null) {
                userLogin(email,password)
            }
        }



    }

    private fun userLogin(email:String,password:String){

        val apiUrl = "http://192.168.31.131:8080/auth/login" // Replace with your API URL

        val requestQueue = Volley.newRequestQueue(this)

        val jsonBody = JSONObject()
        try {
            jsonBody.put("email", email)
            jsonBody.put("password", password)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, apiUrl, jsonBody,
            { response ->
                try {
                    val token = response.getString("jwtToken")
                    Log.d("token", token)
                    // Handle the token (e.g., store it securely)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                // Handle error
            })

        requestQueue.add(jsonObjectRequest)






    }



}
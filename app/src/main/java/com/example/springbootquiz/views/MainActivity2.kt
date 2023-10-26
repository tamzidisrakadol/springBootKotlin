package com.example.springbootquiz.views

import android.content.ContextParams
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.springbootquiz.R
import com.example.springbootquiz.databinding.ActivityMain2Binding
import com.example.springbootquiz.modal.LoginResponse
import com.example.springbootquiz.modal.User
import com.example.springbootquiz.modal.UserLogin
import com.example.springbootquiz.network.ApiClient
import com.example.springbootquiz.network.ApiInterface
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private var userList = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val retrofit = ApiClient.getClient().create(ApiInterface::class.java)


        binding.loginBtn.setOnClickListener {

            var email = binding.emailETV.text?.toString() ?: ""
            var password = binding.passETV.text?.toString() ?: ""


            if (email.isNotEmpty() && password.isNotEmpty()) {

                val loginData = UserLogin(email, password)
                try {
                    val call = retrofit.getAccessLoginInfo(loginData)
                    call.enqueue(object:Callback<LoginResponse>{

                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: retrofit2.Response<LoginResponse>
                        ) {
                            if (response.isSuccessful){
                                Log.d("body", "${response.body()}")
                            }else{
                                Log.d("body", response.errorBody().toString())
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Log.d("body","${t.message}")
                        }

                    })
                }catch (e:Exception){
                    Log.d("body","${e.message}")
                }



            }
        }


    }


}







//
//    private fun userLogin(email: String, password: String) {
//
//        val apiUrl = "http://192.168.31.131:8080/auth/login" // Replace with your API URL
//
//        val requestQueue = Volley.newRequestQueue(this)
//
//        val jsonBody = JSONObject()
//        try {
//            jsonBody.put("email", email)
//            jsonBody.put("password", password)
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//
//        val jsonObjectRequest = JsonObjectRequest(
//            Request.Method.POST, apiUrl, jsonBody,
//            { response ->
//                try {
//                    val token = response.getString("jwtToken")
//                    Log.d("token", token)
//                    // Handle the token (e.g., store it securely)
//                    sendTokenToAccess(token)
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            },
//            { error ->
//                // Handle error
//            })
//
//        requestQueue.add(jsonObjectRequest)
//
//    }
//
//    private fun sendTokenToAccess(token: String) {
//        val header = HashMap<String, String>()
//        header["Authorization"] = "Bearer $token"
//
//
//        val apiUrl = "http://192.168.31.131:8080/home/admin/showall"
//
//        val secondRequestQueue = Volley.newRequestQueue(this)
//
//        val secondJsonObjectRequest = object : JsonObjectRequest(
//            Method.GET, apiUrl, null,
//            { response ->
//                Log.d("token", "list size: ${userList.size}")
//                val jsonArray = JSONArray(response)
//                for (i in 0 until jsonArray.length()) {
//                    val jsonObject = jsonArray.getJSONObject(i)
//                    val user = User()
//                    user.id = jsonObject.getInt("id")
//                    user.name = jsonObject.getString("name")
//                    user.email = jsonObject.getString("email")
//                    user.password = jsonObject.getString("password")
//                    user.role = jsonObject.getString("role")
//                    userList.add(user)
//                    Log.d("token", "${userList.size}")
//                }
//                Log.d("token", "${userList.size}")
//            },
//            { error ->
//                // Handle error
//            }) {
//            override fun getHeaders(): MutableMap<String, String> {
//                val headers = HashMap<String, String>()
//                headers["Authorization"] = "Bearer $token"
//                return headers
//            }
//        }
//
//        secondRequestQueue.add(secondJsonObjectRequest)
//    }



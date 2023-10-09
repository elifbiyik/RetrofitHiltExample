package com.eb.retrofithiltexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var apiService: ApiService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // List<User> dönüş tipi
        lifecycleScope.launch {
            try {
                val users = apiService.getAllUser()
                Log.d("Users (List<User>)", users.toString())
            } catch (e: Exception) {
                Log.d("Users (List<User>) Hata", e.message.toString())
            }
        }


        // Call<User> dönüş tipi
        val call = apiService.getAllUserCall()
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val users = response.body()
                    Log.d("Users (Call<User>)", users.toString())
                } else {
                    Log.e("Users (Call<User>) Hata", response.code().toString())
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("Users (Call<User>) Hata", t.message.toString())
            }
        })


        // Call<List<User>> dönüş tipi
        val callList = apiService.getAllUserCallList()
        callList.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val users = response.body()
                    Log.d("Users (Call<List) ", users.toString())
                } else {
                    Log.e("Users (Call<List) Hata", response.code().toString())
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("Users (Call<List) Hata", t.message.toString())
            }
        })


        // Response<List<User>> dönüş tipi
        lifecycleScope.launch {
            try {
                val response = apiService.getAllUserResponse()

                if (response.isSuccessful) {
                    val users = response.body()
                    val usersResponse = response
                    Log.d("Users (Response) Body", users.toString())
                    Log.d("Users (Response) Response", usersResponse.toString())
                } else {
                    Log.e("Users Response Hata", response.code().toString())
                }
            } catch (e: Exception) {
                Log.e("Users Response Hata", e.message.toString())
            }
        }

    }
}
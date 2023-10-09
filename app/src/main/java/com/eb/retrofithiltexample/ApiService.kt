package com.eb.retrofithiltexample

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/users")
    suspend fun getAllUser(): List<User>

    @GET("/users")
    fun getAllUserCallList(): Call<List<User>>

    @GET("/users")
    fun getAllUserCall(): Call<User>

    @GET("/users")
    suspend fun getAllUserResponse(): Response<List<User>>

}

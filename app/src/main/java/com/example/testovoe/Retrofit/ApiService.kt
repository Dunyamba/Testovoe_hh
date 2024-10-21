package com.example.testovoe.Retrofit

import com.example.testovoe.Domain.TestRepo
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://193.32.177.49/"
object ApiService {

    var gson = GsonBuilder()
        .setLenient()
        .create()



    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()



    val testRepo: TestRepo = retrofit.create(TestRepo::class.java)



}

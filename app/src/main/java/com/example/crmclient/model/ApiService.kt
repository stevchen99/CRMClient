package com.example.crmclient.model

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface  ApiService {

    @GET("GetClient.php")
    fun getClient(): Call<List<TheClient>>

    companion object {

        var BASE_URL = "https://le-esp.fr/CRM/"

        fun create() : ApiService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiService::class.java)

        }
    }
}
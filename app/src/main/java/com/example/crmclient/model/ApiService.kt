package com.example.crmclient.model

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface  ApiService {

    @GET("GetClient.php")
    fun getClient(): Call<List<TheClient>>

    @Headers("Content-Type: application/json")
    @POST("HistoCreateUpdJob.php")
    fun postHisto(@Body userData: TheHisto): Call<TheHisto>
    //fun postHisto(@Body userData: TheHisto): Call<Void>
    //fun postHisto(@Body requestBody: RequestBody): Response<ResponseBody>

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
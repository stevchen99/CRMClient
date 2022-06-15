package com.example.crmclient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crmclient.model.Client
import com.example.crmclient.model.GetData
import com.example.crmclient.model.RetrofitClient
import retrofit2.Call

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Create handle for the RetrofitInstance interface*/
        val service: GetData = RetrofitClient.getRetrofitInstance().create(
            GetData::class.java
        )
        val call: Call<List<Client>> = service.getClientList()
    }
}
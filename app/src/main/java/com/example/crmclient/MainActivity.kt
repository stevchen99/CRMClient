package com.example.crmclient

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crmclient.model.ApiService
import com.example.crmclient.model.TheClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var Cleint : ArrayList<TheClient> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var spinner: Spinner = findViewById(R.id.spinnerClient)

        val apiInterface = ApiService.create().getClient()

        apiInterface.enqueue( object : Callback<List<TheClient>> {
            override fun onResponse(call: Call<List<TheClient>>?, response: Response<List<TheClient>>?) {

                if(response?.body() != null)
                    Cleint = response.body() as ArrayList<TheClient>
                    var data : MutableList<String> = ArrayList()
                Cleint.forEach {
                    data.add(it.Id,it.Company.toString())
                }
                spinner.adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, data)


            }

            override fun onFailure(call: Call<List<TheClient>>?, t: Throwable?) {
                Toast.makeText(applicationContext, t?.message ?: "empty", Toast.LENGTH_LONG).show()
            }
        })
    }



}
package com.example.crmclient

import android.os.Bundle
import android.util.Log
import android.widget.*

import androidx.appcompat.app.AppCompatActivity

import com.example.crmclient.model.ApiService
import com.example.crmclient.model.TheClient
import com.example.crmclient.model.TheHisto
import org.json.JSONObject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var Cleint: ArrayList<TheClient> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var spinner: Spinner = findViewById(R.id.spinnerClient)
        val btn_clickTache: Button = findViewById(R.id.buttonTache)
        var MontantHr: EditText = findViewById(R.id.editTextNumber)

        val apiInterface = ApiService.create().getClient()


        apiInterface.enqueue(
            object : Callback<List<TheClient>> {
                override fun onResponse(
                    call: Call<List<TheClient>>?,
                    response: Response<List<TheClient>>?
                ) {
                    if (response?.body() != null)
                        Cleint = response.body() as ArrayList<TheClient>
                    spinner.adapter =
                        ArrayAdapter(
                            this@MainActivity,
                            android.R.layout.simple_spinner_item,
                            Cleint
                        )
                }

                override fun onFailure(call: Call<List<TheClient>>?, t: Throwable?) {
                    Toast.makeText(applicationContext, t?.message ?: "empty", Toast.LENGTH_LONG)
                        .show()
                }
            })


        btn_clickTache.setOnClickListener {
            // your code to perform when the user clicks on the button
            val LeClient = spinner.getSelectedItem() as TheClient
            var oHisto = TheHisto(LeClient.IdClient, MontantHr.text.toString().toInt())

            // Create JSON using JSONObject
            val jsonObject = JSONObject()
            jsonObject.put("IdJob", "88")
            jsonObject.put("HeureTache", "56")

            // Convert JSONObject to String
            val jsonObjectString = jsonObject.toString()

            // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
 //           val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

//            CoroutineScope(Dispatchers.IO).launch {
//                // Do the POST request and get response
//                val response = service.createEmployee(requestBody)
//
//                withContext(Dispatchers.Main) {
//                    if (response.isSuccessful) {
//
//                        // Convert raw JSON to pretty JSON using GSON library
//                        val gson = GsonBuilder().setPrettyPrinting().create()
//                        val prettyJson = gson.toJson(
//                            JsonParser.parseString(
//                                response.body()
//                                    ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
//                            )
//                        )
//
//                        Log.d("Pretty Printed JSON :", prettyJson)
//
//                    } else {
//
//                        Log.e("RETROFIT_ERROR", response.code().toString())
//
//                    }
//                }
//            }


            val apiCreate = ApiService.create().postHisto(oHisto)

            apiCreate.enqueue(
                object : Callback<TheHisto> {
                    override fun onFailure(call: Call<TheHisto>, t: Throwable) {
                    }
                    override fun onResponse(call: Call<TheHisto>, response: Response<TheHisto>) {
                        val addedUser = response.body()
                        val codeRep = response.code()
                        Log.i("", "post submitted to API." + addedUser)
                        if (response.isSuccessful()) {
                            Log.i("", "post registration to API" + response.body()!!.toString())
                        }
                    }
                }
            )

            Toast.makeText(
                this@MainActivity,
                LeClient.IdClient.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}
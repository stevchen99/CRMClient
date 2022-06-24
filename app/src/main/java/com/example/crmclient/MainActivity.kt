package com.example.crmclient

import android.os.Bundle
import android.util.Log
import android.widget.*

import androidx.appcompat.app.AppCompatActivity

import com.example.crmclient.model.ApiService
import com.example.crmclient.model.TheClient
import com.example.crmclient.model.TheHisto
import com.example.crmclient.model.TheTaches
import org.json.JSONObject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var Cleint: ArrayList<TheClient> = ArrayList()
    private var Taches: ArrayList<TheTaches> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var spinner: Spinner = findViewById(R.id.spinnerClient)
        val btn_clickTache: Button = findViewById(R.id.buttonTache)
        var MontantHr: EditText = findViewById(R.id.editTextNumber)

        val apiInterface = ApiService.create().getClient()
        val apiInterfaceTaches = ApiService.create().getTaches()

         apiInterfaceTaches.enqueue(
             object : Callback<List<TheTaches>> {
                 override fun onResponse(
                     call: Call<List<TheTaches>>,
                     response: Response<List<TheTaches>>
                 ) {
                     if (response.body() != null)
                     {
                         Taches = response.body() as ArrayList<TheTaches>
                         spinner.adapter = ArrayAdapter(this@MainActivity , android.R.layout.simple_spinner_item, Taches)
                     }
                 }

                 override fun onFailure(call: Call<List<TheTaches>>, t: Throwable) {
                     Toast.makeText(applicationContext, t?.message ?: "empty", Toast.LENGTH_LONG)
                         .show()
                 }
             }
         )





        btn_clickTache.setOnClickListener {
            // your code to perform when the user clicks on the button
          //  val LeClient = spinner.getSelectedItem() as TheClient
            val LeTaches = spinner.getSelectedItem() as TheTaches
            var oHisto = TheHisto(LeTaches.IdTache, MontantHr.text.toString().toInt())

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
                LeTaches.IdTache.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}
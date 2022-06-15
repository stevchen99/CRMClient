package com.example.crmclient.model;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface  GetData {

    @GET("GetClient.php")
    Call<List<Client>> getClientList();
}

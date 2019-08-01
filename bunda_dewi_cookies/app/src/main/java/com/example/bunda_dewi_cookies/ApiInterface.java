package com.example.bunda_dewi_cookies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("getdata.php")
    Call<List<GetData>> getData(
            @Query("item_type") String item_type,
            @Query("key") String keyword
    );
}

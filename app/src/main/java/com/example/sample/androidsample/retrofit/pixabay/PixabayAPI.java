package com.example.sample.androidsample.retrofit.pixabay;

import com.example.sample.androidsample.retrofit.pixabay.models.PixabayRootObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface PixabayAPI {
    @GET("api")
    Call<PixabayRootObject> baseApiCall(@QueryMap Map<String, String> parameter);
}




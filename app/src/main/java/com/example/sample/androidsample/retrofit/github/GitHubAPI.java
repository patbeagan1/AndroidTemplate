package com.example.sample.androidsample.retrofit.github;

import com.example.sample.androidsample.retrofit.github.models.RootObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GitHubAPI {
    @GET("users/{user}/repos")
    Call<List<RootObject>> listRepos(@Path("user") String user);

    interface ListReposCallBack {
        void performAction(Response<List<RootObject>> listResponse);
    }
}




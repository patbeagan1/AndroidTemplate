package com.example.sample.androidsample.retrofit.github;

import com.example.sample.androidsample.retrofit.github.models.GithubRootObject;
import com.example.sample.androidsample.retrofit.pixabay.models.PixabayRootObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface GitHubAPI {
    @GET("users/{user}/repos")
    Call<List<GithubRootObject>> listRepos(@Path("user") String user);
}




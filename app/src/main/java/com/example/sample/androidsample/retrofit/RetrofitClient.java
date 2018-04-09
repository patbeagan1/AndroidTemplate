package com.example.sample.androidsample.retrofit;

import com.example.sample.androidsample.retrofit.github.GitHubAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static GitHubAPI gitHubAPI;

    private static Retrofit getClient(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static GitHubAPI getGithubService() {
        if (gitHubAPI == null) {
            gitHubAPI = getClient("https://api.github.com").create(GitHubAPI.class);
        }
        return gitHubAPI;
    }
}

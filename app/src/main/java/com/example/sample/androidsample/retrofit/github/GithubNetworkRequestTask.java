package com.example.sample.androidsample.retrofit.github;

import android.os.AsyncTask;

import com.example.sample.androidsample.retrofit.RetrofitClient;
import com.example.sample.androidsample.retrofit.github.models.RootObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class GithubNetworkRequestTask extends AsyncTask<String, Integer, Response<List<RootObject>>> {
    GitHubAPI.ListReposCallBack callBack;

    public GithubNetworkRequestTask(GitHubAPI.ListReposCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected Response<List<RootObject>> doInBackground(String... userNames) {
        try {
            String userName = userNames[0];
            return RetrofitClient
                    .getGithubService()
                    .listRepos(userName)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Response<List<RootObject>> listResponse) {
        super.onPostExecute(listResponse);
        callBack.performAction(listResponse);
    }
}

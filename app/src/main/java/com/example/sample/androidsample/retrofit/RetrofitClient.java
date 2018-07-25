    package com.example.sample.androidsample.retrofit;

    import com.example.sample.androidsample.retrofit.github.GitHubAPI;
    import com.example.sample.androidsample.retrofit.pixabay.PixabayAPI;

    import retrofit2.Retrofit;
    import retrofit2.converter.gson.GsonConverterFactory;

    public class RetrofitClient {

        private static GitHubAPI gitHubAPI;
        private static PixabayAPI pixabayAPI;

        private static Retrofit getClient(String baseUrl) {
            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        public static GitHubAPI getGithubService() {
            if (gitHubAPI == null) {
                // Getting a client for https://api.github.com
                gitHubAPI = getClient("https://api.github.com").create(GitHubAPI.class);
            }
            return gitHubAPI;
        }

        public static PixabayAPI getPixabayService() {
            if (pixabayAPI == null) {
                // Getting a client for https://pixabay.com
                pixabayAPI = getClient("https://pbay.com").create(PixabayAPI.class);
            }
            return pixabayAPI;
        }
    }

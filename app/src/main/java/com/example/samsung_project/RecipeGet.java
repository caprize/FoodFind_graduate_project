package com.example.samsung_project;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecipeGet extends AsyncTask<String, Integer, String> {

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        HttpUrl localUrl = HttpUrl.parse(url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    String prep_url(String name){
        String baseurl = "https://www.allrecipes.com/recipe/";
        return baseurl+name;
    }


    @Override
    protected String doInBackground(String... strings) {
        String result = null;
        try {
            result = run(strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return null;
    }
}

package com.example.samsung_project.Network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Dictionary;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RecipeGet extends AsyncTask<String, Integer, String> {
    private  byte[] toBitmap;
    public static final MediaType Json
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    Gson gson;

    public Dictionary postRecipe(String url, String json) throws IOException {
        HttpUrl localUrl = new HttpUrl.Builder()
                .scheme("http")
                .host(url)
                .addPathSegment("recipe/")
                .build();
        RequestBody body = RequestBody.create(Json, json);
        Request request = new Request.Builder()
                .url(localUrl)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return gson.fromJson(response.body().toString(), Dictionary.class);
    }

    public byte[] post_GetImage(String url, String json) throws IOException {
        HttpUrl localUrl = new HttpUrl.Builder()
                .scheme("http")
                .host(url)
                .addPathSegment("dish_image/")
                .build();
        RequestBody body = RequestBody.create(Json, json);
        Request request = new Request.Builder()
                .url(localUrl)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().bytes();
    }


    public String prep_url(String name){
        String baseurl = "https://www.allrecipes.com/recipe/";
        return baseurl+name;
    }

    public Bitmap prep_image(){
        Bitmap bm = BitmapFactory.decodeByteArray(getToBitmap(),0,getToBitmap().length);
        return bm;
    }


    @Override
    protected String doInBackground(String... strings) {
        String result = null;
        try {
            if (strings[2] == "post_image")
//                result = post_GetImage(strings[0],strings[1]);
                System.out.println("ahah");
            else if (strings[2] == "get_recipe")
                System.out.println("ahah");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void setToBitmap(byte[] toBitmap){
        this.toBitmap=toBitmap;
    }

    public byte[] getToBitmap() {
        return toBitmap;
    }
}

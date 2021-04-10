package com.example.samsung_project.Network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RecipeGet extends AsyncTask<String, Integer, String> {
    private String toBitmap;
    public static final MediaType Json
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();


    public String postRecipe(String url, String json) throws IOException {
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
        return response.body().string();
    }

    public String post_GetImage(String url, String json) throws IOException {
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
        return response.body().string();
    }


    public String prep_url(String name){
        String baseurl = "https://www.allrecipes.com/recipe/";
        return baseurl+name;
    }

    public Bitmap prep_image(){
        byte[] bytes_data = toBitmap.getBytes();
        Bitmap bm = BitmapFactory.decodeByteArray(bytes_data,0,bytes_data.length);
        return bm;
    }


    @Override
    protected String doInBackground(String... strings) {
        String result = null;
        try {
            if (strings[2]=="post_image")
                result = post_GetImage(strings[0],strings[1]);
            else if (strings[2] =="get_recipe")
                result = postRecipe(strings[0],strings[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }
    protected void onPostExecute(String response) {

        super.onPostExecute(response);
    }

    public String getToBitmap() {
        return toBitmap;
    }

    public void setToBitmap(String toBitmap){
        this.toBitmap=toBitmap;
    }
}

package com.example.samsung_project;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.system.Os;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ImagePostReq extends AsyncTask<String, Integer, String> {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");



    OkHttpClient client = new OkHttpClient.Builder()
            .build();
    String post1(String url, String json) throws IOException {
        System.out.println(url);
        HttpUrl localUrl = new HttpUrl.Builder()
                .scheme("http")
                .host(url)
                .addPathSegment("image")
                .build();
        System.out.println(localUrl);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(localUrl)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }



    public String PrepImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = null;
        try {
            result=post1(strings[0],strings[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}

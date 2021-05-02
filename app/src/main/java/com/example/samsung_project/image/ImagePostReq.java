package com.example.samsung_project.image;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;

import java.io.ByteArrayOutputStream;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ImagePostReq extends AsyncTask<String, Integer, String> {

    public static final MediaType Json
            = MediaType.get("application/json; charset=utf-8");



    OkHttpClient client = new OkHttpClient.Builder()
            .build();
    public String post1(String url, byte[] json) throws IOException {
        HttpUrl localUrl = new HttpUrl.Builder()
                .scheme("http")
                .host(url)
                .addPathSegment("image/")
                .build();
        RequestBody body = RequestBody.create(Json, json);
        Request request = new Request.Builder()
                .url(localUrl)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }



    public byte[] PrepImage(Bitmap image) {
        int x = image.getWidth();
        int y = image.getHeight();
//        image.getPixels(intArray, 0, x, 0, 0, x, y);
        int[] intArray = new int[x * y];

        image.getPixels(intArray, 0, x,
                0, 0, x, y);
        for (int i=0;i<x*y;i++){
            intArray[i]=intArray[i] ;

        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(intArray.length * 4);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(intArray);

        byte[] array = byteBuffer.array();

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] imageBytes = baos.toByteArray();
//        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return array;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = null;
        System.out.println("dd");

        return result;
    }

    protected void onPostExecute(String response) {

        super.onPostExecute(response);
    }

}

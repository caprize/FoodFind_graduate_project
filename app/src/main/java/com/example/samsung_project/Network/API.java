package com.example.samsung_project.Network;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.toolbox.HttpResponse;
import com.example.samsung_project.image.ImageBody;
import com.example.samsung_project.image.ImagePost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public Retrofit retrofit;
    public ImagePost api;
    public API() {
        this.retrofit = new Retrofit.Builder()

                .baseUrl("http://10.0.2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.api = retrofit.create(ImagePost.class);
    }

    public void postimage(String req, Context context) {
        ImageBody body = new ImageBody();
        body.setBody(req);
        api.sendImage(body.getBody()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "onFailure called ", Toast.LENGTH_SHORT).show();
                System.out.println(call);
                call.cancel();
            }
        });
//        Call<String> call = api.sendImage(body.getBody());
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful()) {
//                    System.out.println("YES");
//                    System.out.println(response.raw());
//                } else {
//                    System.out.println(response.code());
//                    System.out.println("No(");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(context.getApplicationContext(), "onFailure called ", Toast.LENGTH_SHORT).show();
//                System.out.println(call);
//                call.cancel();
//            }
//
//        });
    }

}

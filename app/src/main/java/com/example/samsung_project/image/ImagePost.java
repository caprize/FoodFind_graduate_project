package com.example.samsung_project.image;

import com.android.volley.toolbox.HttpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.example.samsung_project.image.ImagePredicted.dishes;

public interface ImagePost {
        @POST("image/")
        Call<String> sendImage(@Body String body);
}

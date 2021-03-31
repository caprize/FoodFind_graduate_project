package com.example.samsung_project.image;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ImagePost {
        @POST("image")
        Call<ImageBody> sendImage(@Body ImageBody body);
}

package com.example.herring.test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("photos")
    Call<List<PhotoModel>> getPhotos();
}

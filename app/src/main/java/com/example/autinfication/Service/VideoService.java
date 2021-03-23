package com.example.autinfication.Service;

import com.example.autinfication.Movie;
import com.example.autinfication.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VideoService {
    @GET("/movies/{movieId}/episodes")
    Call<List<Video>> getData(@Path("movieId")int movieId);



}

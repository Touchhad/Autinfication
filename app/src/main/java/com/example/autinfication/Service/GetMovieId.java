package com.example.autinfication.Service;

import com.example.autinfication.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetMovieId {
    @GET("/movies/{movieId}")
    Call<Movie> getDate(@Path("movieId") int movieId);
}

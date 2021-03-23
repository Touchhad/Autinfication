package com.example.autinfication.Service;

import com.example.autinfication.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UserInterface {
    @GET("/user")
    Call<List<User>> getDate(@Header("Authorization") int token);
}

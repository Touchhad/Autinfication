package com.example.autinfication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewDebug;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autinfication.Activity.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Intent intent = getIntent();

        int fName = intent.getIntExtra("movieId", 0);


        textView = findViewById(R.id.movieId);

        Call<Movie> call = ApiClient.getMovieId().getDate(fName);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()){
                    Movie movie = response.body();
                    textView.setText(movie.getName());
                }else {
                    Toast.makeText(MovieActivity.this, response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }
}
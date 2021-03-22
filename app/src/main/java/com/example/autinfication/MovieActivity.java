package com.example.autinfication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewDebug;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autinfication.Activity.LoginActivity;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {

    TextView textView, Name, Description,TagName,age;
    ImageView Image;
    LinearLayout linearLayout;
    Context context;
    private final static String PHOTO_URL = "http://cinema.areas.su/up/images/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Intent intent = getIntent();

        int fName = intent.getIntExtra("movieId", 0);


        textView = findViewById(R.id.movieId);
        linearLayout = (LinearLayout)findViewById(R.id.linerLayout);
        Name = (TextView) findViewById(R.id.tvName);
        Description = (TextView) findViewById(R.id.description1);
        TagName = (TextView) findViewById(R.id.tvTag);
        Image = (ImageView) findViewById(R.id.image1);
        age = (TextView) findViewById(R.id.age);

        Call<Movie> call = ApiClient.getMovieId().getDate(fName);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()){
                    Movie movie = response.body();
                    textView.setText(movie.getName());
                    Description.setText(movie.getDescription());
                    age.setText(movie.getAge());

                    Picasso.with(context)
                            .load(PHOTO_URL + movie.getPoster())
                            .into(Image);
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
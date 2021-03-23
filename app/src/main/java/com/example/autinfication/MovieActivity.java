package com.example.autinfication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewDebug;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.autinfication.Activity.LoginActivity;
import com.example.autinfication.Service.VideoService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {

    TextView textView, Name, Description,TagName,age;
    ImageView Image;
    LinearLayout linearLayout;
    Context context;
    VideoView videoView;
    private final static String PHOTO_URL = "http://cinema.areas.su/up/images/";
    private final static String VIDEO_URL = "http://cinema.areas.su/up/video/";

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
        videoView = (VideoView) findViewById(R.id.videoView);

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



        Call<List<Video>> call1 = ApiClient.getVideo().getData(fName);
        call1.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                if (response.isSuccessful()){
                    List<Video> movie = response.body();

                    if (TextUtils.isEmpty(movie.get(0).getPreview())){

                        Toast.makeText(MovieActivity.this, "Отсутствует видео!", Toast.LENGTH_LONG).show();
                    }else {
                        videoView.setVideoPath(VIDEO_URL + movie.get(0).getPreview());
                        videoView.start();
                    }
                }else{
                    Toast.makeText(MovieActivity.this, response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                Toast.makeText(MovieActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
}
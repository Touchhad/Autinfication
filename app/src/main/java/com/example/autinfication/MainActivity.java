package com.example.autinfication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.autinfication.Fragments.Favorite;
import com.example.autinfication.Fragments.NewFrag;
import com.example.autinfication.Fragments.Trend;
import com.example.autinfication.Service.ScrollApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    RecyclerView mRecyclerView, mRecyclerView1;

    List<Movie> mMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        mMovie = new ArrayList<>();


        mRecyclerView1 = (RecyclerView) findViewById(R.id.recyclerView1) ;
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        mRecyclerView1.setLayoutManager(layoutManager1);



        ScrollAdapter adapter1 = new ScrollAdapter(mMovie);
        mRecyclerView1.setAdapter(adapter1);



        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, Favorite.class,null)
                    .commit();
        }


        ScrollApi scrollApi = ScrollApi.retrofit.create(ScrollApi.class);
        final Call<List<Movie>> call1 = scrollApi.getData();
        call1.enqueue(new Callback<List<Movie>>() {
                          @Override
                          public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                              // response.isSuccessfull() возвращает true если код ответа 2xx
                              if (response.isSuccessful()) {
                                  mMovie.addAll(response.body());
                                  mRecyclerView1.getAdapter().notifyDataSetChanged();

                              } else {
                                  // Обрабатываем ошибку
                                  ResponseBody errorBody = response.errorBody();
                                  try {
                                      Toast.makeText(MainActivity.this, errorBody.string(),
                                              Toast.LENGTH_SHORT).show();

                                  } catch (IOException e) {
                                      e.printStackTrace();
                                  }
                              }
                          }

                          @Override
                          public void onFailure(Call<List<Movie>> call, Throwable throwable) {
                              Toast.makeText(MainActivity.this, "Что-то пошло не так",
                                      Toast.LENGTH_SHORT).show();

                          }
                      }
        );
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_favorite:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment, Favorite.class, null)
                        .commit();
                break;
            case R.id.btn_new:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment, NewFrag.class, null)
                        .commit();
                break;
            case R.id.btn_trend:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment, Trend.class, null)
                        .commit();
                break;
        }
    }
}
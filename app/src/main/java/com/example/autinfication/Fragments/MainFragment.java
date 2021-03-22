package com.example.autinfication.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.autinfication.Fragments.Favorite;
import com.example.autinfication.Fragments.NewFrag;
import com.example.autinfication.Fragments.Trend;
import com.example.autinfication.Movie;
import com.example.autinfication.R;
import com.example.autinfication.ScrollAdapter;
import com.example.autinfication.Service.MovieApi;
import com.example.autinfication.Service.ScrollApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment {
    Context context;
    Button btn_favorite,btn_new,btn_trend;
    private ProgressBar mProgressBar;
    RecyclerView mRecyclerView, mRecyclerView1;

    List<Movie> mMovie;

    public MainFragment() {
        super(R.layout.fragment_main);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        btn_favorite = view.findViewById(R.id.btn_favorite);
        btn_new = view.findViewById(R.id.btn_new);
        btn_trend = view.findViewById(R.id.btn_trend);

        mMovie = new ArrayList<>();


        mRecyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerView1) ;
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true);
        mRecyclerView1.setLayoutManager(layoutManager1);



        ScrollAdapter adapter1 = new ScrollAdapter(mMovie);
        mRecyclerView1.setAdapter(adapter1);



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
                                      Toast.makeText(context, errorBody.string(),
                                              Toast.LENGTH_SHORT).show();

                                  } catch (IOException e) {
                                      e.printStackTrace();
                                  }
                              }
                          }

                          @Override
                          public void onFailure(Call<List<Movie>> call, Throwable throwable) {
                              Toast.makeText(context, "Что-то пошло не так",
                                      Toast.LENGTH_SHORT).show();

                          }
                      }
        );
        if (savedInstanceState == null){
         getChildFragmentManager().beginTransaction()
                    .replace(R.id.fragmentView, Favorite.class,null)
                    .commit();
        }




        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.fragmentView, Favorite.class, null)
                        .commit();
            }
        });
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.fragmentView, NewFrag.class, null)
                        .commit();
            }
        });
        btn_trend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.fragmentView, Trend.class, null)
                        .commit();
            }
        });



        return view;


    }

}
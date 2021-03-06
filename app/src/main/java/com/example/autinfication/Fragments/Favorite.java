package com.example.autinfication.Fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.autinfication.Movie;
import com.example.autinfication.MovieAdapter;
import com.example.autinfication.R;
import com.example.autinfication.Service.MovieApi;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Favorite extends Fragment {
    Context context;
    private ProgressBar mProgressBar;
    RecyclerView mRecyclerView, mRecyclerView1;

    List<Movie> mMovie;

    public Favorite() {
       super(R.layout.fragment_favorite);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);



        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);

        mMovie = new ArrayList<>();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        mRecyclerView.setLayoutManager(layoutManager);



        MovieAdapter adapter = new MovieAdapter(mMovie);
        mRecyclerView.setAdapter(adapter);

        mProgressBar.setVisibility(View.VISIBLE);

        MovieApi movieApi = MovieApi.retrofit.create(MovieApi.class);
        final Call<List<Movie>> call = movieApi.getData();
        call.enqueue(new Callback<List<Movie>>() {
                         @Override
                         public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                             // response.isSuccessfull() ???????????????????? true ???????? ?????? ???????????? 2xx
                             if (response.isSuccessful()) {
                                 mMovie.addAll(response.body());
                                 mRecyclerView.getAdapter().notifyDataSetChanged();
                                 mProgressBar.setVisibility(View.INVISIBLE);
                             } else {
                                     mProgressBar.setVisibility(View.INVISIBLE);
                             }
                         }

                         @Override
                         public void onFailure(Call<List<Movie>> call, Throwable throwable) {
                             mProgressBar.setVisibility(View.INVISIBLE);
                         }
                     }
        );

        return view;

    }
}
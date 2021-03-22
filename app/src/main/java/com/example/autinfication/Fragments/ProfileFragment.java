package com.example.autinfication.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.autinfication.Movie;
import com.example.autinfication.R;
import com.example.autinfication.ScrollAdapter;
import com.example.autinfication.Service.ScrollApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {
    Context context;
    Button btn_favorite, btn_new, btn_trend;
    private ProgressBar mProgressBar;
    RecyclerView mRecyclerView, mRecyclerView1;

    List<Movie> mMovie;

    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


      return view;

    }
}

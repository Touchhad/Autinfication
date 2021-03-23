package com.example.autinfication.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autinfication.Activity.LoginActivity;
import com.example.autinfication.ApiClient;
import com.example.autinfication.Movie;
import com.example.autinfication.MovieActivity;
import com.example.autinfication.MovieAdapter;
import com.example.autinfication.ProfileAdapter;
import com.example.autinfication.R;
import com.example.autinfication.Request.LoginRequest;
import com.example.autinfication.ScrollAdapter;
import com.example.autinfication.Service.MovieApi;
import com.example.autinfication.Service.ScrollApi;
import com.example.autinfication.User;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    Context context;
    TextView email, first, last;
    ImageView Image;
    SharedPreferences sPref;

    final String SAVED_TEXT = "saved_text";


    List<Movie> mMovie;

    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }
    private final static String PHOTO_URL = "http://cinema.areas.su/up/images/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);




        Intent intent = getActivity().getIntent();

        int fName = intent.getIntExtra("UserId", 0);



        email = (TextView) view.findViewById(R.id.tvEmailName);
        first = (TextView) view.findViewById(R.id.tvFirstName);
        last = (TextView) view.findViewById(R.id.tvLastName);
        Image = (ImageView) view.findViewById(R.id.imageView3);



        sPref = getContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        int savedText = sPref.getInt(SAVED_TEXT, 0);

        Call<List<User>> userCall = ApiClient.getUser().getDate(savedText);
        userCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> user = response.body();
                if (response.isSuccessful()){

                }else {
                    ResponseBody responseBody = response.errorBody();
                    try {
                        Toast.makeText(context, responseBody.string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        return view;

    }
}

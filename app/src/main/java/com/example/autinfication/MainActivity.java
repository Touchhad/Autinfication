package com.example.autinfication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import android.view.View;
import android.widget.ProgressBar;


import com.example.autinfication.Fragments.MainFragment;
import com.example.autinfication.Fragments.NewFrag;
import com.example.autinfication.Fragments.ProfileFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    RecyclerView mRecyclerView, mRecyclerView1;

    List<Movie> mMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);






        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentView, MainFragment.class,null)
                    .commit();
        }



    }


    public void onClickW(View view){
        switch (view.getId()){
            case R.id.profile:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentView, ProfileFragment.class, null)
                        .commit();
                break;
            case R.id.movie:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentView, MainFragment.class, null)
                        .commit();
                break;
        }
    }
}
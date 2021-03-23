package com.example.autinfication;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    ConstraintLayout constraintLayout;
    private final static String PHOTO_URL = "http://cinema.areas.su/up/images/";
    private final static String VIDEO_URL = "https://app.swaggerhub.com/up/video/";
    private List<User> mUser;
    private Context mContext;

    public ProfileAdapter(List<User> movies) {
        this.mUser = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       User user = mUser.get(position);
        Picasso.with(mContext)
                .load(PHOTO_URL + user.getAvatar())
                .into(holder.Image);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieActivity.class).putExtra("UserId", user.getUserId());
                mContext.startActivity(intent);
            }
        });
        holder.Name.setText(user.getEmail());
        holder.FirstName.setText(user.getFirstName());
        holder.LastName.setText(user.getLastName());

    }

    @Override
    public int getItemCount() {
        if (mUser == null) {
            return 0;
        }
        return mUser.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView Name, Description, FirstName, LastName, Email;
        ImageView Image;


        ViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linerLayout);
            LastName = (TextView) itemView.findViewById(R.id.tvLastName);

            FirstName = (TextView) itemView.findViewById(R.id.tvFirstName);
            Email = (TextView) itemView.findViewById(R.id.tvEmailName);
            Image = (ImageView) itemView.findViewById(R.id.imageView3);

        }
    }

}

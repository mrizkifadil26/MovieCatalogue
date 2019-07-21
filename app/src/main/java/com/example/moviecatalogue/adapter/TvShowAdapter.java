package com.example.moviecatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.activity.TvDetailActivity;
import com.example.moviecatalogue.model.TvShow;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private Context context;
    private ArrayList<TvShow> tvShows;

    public TvShowAdapter(Context context) {
        this.context = context;
        tvShows = new ArrayList<>();
    }

    public void setTvShows(ArrayList<TvShow> tvShows) {
        this.tvShows = tvShows;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv, parent, false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, final int position) {
        TvShow tvShow = tvShows.get(position);

        Picasso.get()
                .load(tvShow.getTvPoster())
                .into(holder.tvPoster);

        holder.tvTitle.setText(tvShow.getTvTitle());
        holder.tvGenre.setText(tvShow.getTvGenre());
        holder.tvRating.setText(tvShow.getTvRating());

        holder.tvParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(context, TvDetailActivity.class);
                mIntent.putParcelableArrayListExtra(TvDetailActivity.EXTRA_TV, tvShows.get(position));
                context.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }


    class TvShowViewHolder extends RecyclerView.ViewHolder {

        ImageView tvPoster;
        TextView tvTitle;
        TextView tvGenre;
        TextView tvRating;
        CardView tvParent;

        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            tvParent = itemView.findViewById(R.id.tv_parent);
            tvPoster = itemView.findViewById(R.id.tv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvGenre = itemView.findViewById(R.id.tv_genre);
            tvRating = itemView.findViewById(R.id.tv_rating);
        }

    }
}

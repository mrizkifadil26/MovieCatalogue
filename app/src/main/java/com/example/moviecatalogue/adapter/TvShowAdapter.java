package com.example.moviecatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
import com.example.moviecatalogue.model.TvShowGenre;
import com.example.moviecatalogue.model.TvShowResults;
import com.example.moviecatalogue.util.Config;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private Context context;
    private List<TvShowResults> tvShows;
    private List<TvShowGenre> tvGenres;

    public TvShowAdapter(Context context) {
        this.context = context;
    }

    public void setTvShows(List<TvShowResults> tvShows, List<TvShowGenre> tvShowGenres) {
        this.tvShows = tvShows;
        this.tvGenres = tvShowGenres;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv, parent, false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, final int position) {
        final TvShowResults tvShow = tvShows.get(position);
        String imageUrl = Config.IMAGE_URL + tvShow.getTvPoster();

        Picasso.get()
                .load(imageUrl)
                .into(holder.tvPoster);

        holder.tvTitle.setText(tvShow.getTvTitle());
        holder.tvRating.setText(String.valueOf(tvShow.getTvRating()));

        if (tvGenres != null) {
            holder.tvGenre.setText(holder.getTvGenres(tvShows.get(position).getTvGenreId()));
        }

        holder.tvParent.setOnClickListener(view -> {
            Intent mIntent = new Intent(context, TvDetailActivity.class);
            mIntent.putExtra(TvDetailActivity.EXTRA_TV, tvShows.get(position));
            context.startActivity(mIntent);
        });
    }

    @Override
    public int getItemCount() {
        if (tvShows != null) {
            return tvShows.size();
        } else {
            return 0;
        }
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

        private String getTvGenres(List<Integer> genreIds) {
            List<String> tvShowGenres = new ArrayList<>();
            for (Integer genreId : genreIds) {
                for (TvShowGenre genre : tvGenres) {
                    if (genre.getId() == genreId) {
                        tvShowGenres.add(genre.getName());
                        break;
                    }
                }
            }
            return TextUtils.join(" | ", tvShowGenres);
        }
    }
}

package com.example.moviecatalogue.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.model.TvShow;
import com.squareup.picasso.Picasso;

public class TvDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TV = "extra_tv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);

        ImageView detailTvPoster = findViewById(R.id.detail_tv_poster);
        TextView detailTvTitle = findViewById(R.id.detail_tv_title);
        TextView detailTvReleaseDate = findViewById(R.id.detail_tv_release_date);
        TextView detailTvGenre = findViewById(R.id.detail_tv_genre);
        TextView detailTvDuration = findViewById(R.id.detail_tv_duration);
        TextView detailTvRating = findViewById(R.id.detail_tv_rating);
        TextView detailTvSeason = findViewById(R.id.detail_tv_season);
        TextView detailTvEpisode = findViewById(R.id.detail_tv_episode);
        TextView detailTvDirector = findViewById(R.id.detail_tv_director);
        TextView detailTvActor = findViewById(R.id.detail_tv_actor);
        TextView detailTvDescription = findViewById(R.id.detail_tv_description);
        TextView detailTvPlot = findViewById(R.id.detail_tv_plot);

        TvShow tvShow = getIntent().getParcelableExtra(EXTRA_TV);

        detailTvTitle.setText(tvShow.getTvTitle());
        detailTvReleaseDate.setText(tvShow.getTvReleaseDate());
        detailTvGenre.setText(tvShow.getTvGenre());
        detailTvDuration.setText(tvShow.getTvDuration());
        detailTvRating.setText(tvShow.getTvRating());
        detailTvSeason.setText(tvShow.getTvSeason());
        detailTvEpisode.setText(tvShow.getTvEpisode());
        detailTvDirector.setText(tvShow.getTvDirector());
        detailTvActor.setText(tvShow.getTvActor());
        detailTvDescription.setText(tvShow.getTvDescription());
        detailTvPlot.setText(tvShow.getTvPlot());

        Picasso.get()
                .load(tvShow.getTvPoster())
                .into(detailTvPoster);
    }
}

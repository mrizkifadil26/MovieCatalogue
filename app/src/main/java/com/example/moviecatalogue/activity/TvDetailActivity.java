package com.example.moviecatalogue.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.model.TvShowDetail;
import com.example.moviecatalogue.model.TvShowGenre;
import com.example.moviecatalogue.model.TvShowResults;
import com.example.moviecatalogue.util.Config;
import com.example.moviecatalogue.viewmodel.TvShowViewModel;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TvDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TV = "extra_tv";

    private int tvShowId;

    private ProgressBar detailTvProgress;

    private ImageView detailTvPoster;
    private TextView detailTvTitle;
    private TextView detailTvAiring;
    private TextView detailTvGenre;
    private TextView detailTvRating;
    private TextView detailTvPopularity;
    private TextView detailTvDescription;
    private TextView detailTvSeasons;
    private TextView detailTvEpisodes;

    private TextView textTvMaxRating;
    private TextView textTvAiring;
    private TextView textTvPopularity;
    private TextView textTvOverview;
    private TextView textTvGenre;
    private TextView textTvSeasons;
    private TextView textTvEpisodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);

        detailTvProgress = findViewById(R.id.progress_tv_detail);

        detailTvPoster = findViewById(R.id.detail_tv_poster);
        detailTvTitle = findViewById(R.id.detail_tv_title);
        detailTvGenre = findViewById(R.id.detail_tv_genre);
        detailTvRating = findViewById(R.id.detail_tv_rating);
        detailTvAiring = findViewById(R.id.detail_tv_airing);
        detailTvPopularity = findViewById(R.id.detail_tv_popularity);
        detailTvDescription = findViewById(R.id.detail_tv_description);
        detailTvSeasons = findViewById(R.id.detail_tv_seasons);
        detailTvEpisodes = findViewById(R.id.detail_tv_episodes);

        initializing();

        TvShowResults tvShows = getIntent().getParcelableExtra(EXTRA_TV);
        if (tvShows != null) {
            tvShowId = tvShows.getId();
        }

        TvShowViewModel tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        tvShowViewModel.getTvDetailFromRetrofit(tvShowId, Config.API_KEY).observe(this, getTvShowDetail);
    }

    private final Observer<TvShowDetail> getTvShowDetail = this::detailTvShows;

    private void detailTvShows(TvShowDetail detail) {
        detailTvTitle.setText(detail.getTvTitle());
        detailTvAiring.setText(changeDate(detail.getTvReleaseDate()));
        detailTvRating.setText(String.valueOf(detail.getTvRating()));
        detailTvDescription.setText(detail.getTvDescription());
        detailTvPopularity.setText(String.valueOf(detail.getTvPopularity()));
        detailTvSeasons.setText(String.valueOf(detail.getTvSeasons()));
        detailTvEpisodes.setText(String.valueOf(detail.getTvEpisodes()));

        String imageUrl = Config.IMAGE_URL + detail.getTvPoster();
        Picasso.get()
                .load(imageUrl)
                .into(detailTvPoster);

        StringBuilder genreBuilder = new StringBuilder();
        for (int i = 0; i < detail.getTvGenres().size(); i++) {
            TvShowGenre tvGenres = detail.getTvGenres().get(i);
            if (i < detail.getTvGenres().size() - 1) {
                genreBuilder.append(tvGenres.getName()).append("\n");
                detailTvGenre.append(tvGenres.getName() + "\n");
            } else {
                detailTvGenre.append(tvGenres.getName());
                genreBuilder.append(tvGenres.getName());
            }
        }

        ending();
    }

    private String changeDate(String date) {
        String dateStringifier = "";
        Date dateFormatted;
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            dateFormatted = inputFormat.parse(date);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
            if (dateFormatted != null) {
                dateStringifier = outputFormat.format(dateFormatted);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStringifier;
    }

    private void initializing() {
        textTvMaxRating = findViewById(R.id.text_tv_max_rating);
        textTvAiring = findViewById(R.id.text_tv_airing);
        textTvOverview = findViewById(R.id.text_tv_overview);
        textTvGenre = findViewById(R.id.text_tv_genre);
        textTvPopularity = findViewById(R.id.text_tv_popularity);
        textTvSeasons = findViewById(R.id.text_tv_seasons);
        textTvEpisodes = findViewById(R.id.text_tv_episodes);

        detailTvProgress.setVisibility(View.VISIBLE);

        textTvMaxRating.setVisibility(View.GONE);
        textTvAiring.setVisibility(View.GONE);
        textTvOverview.setVisibility(View.GONE);
        textTvPopularity.setVisibility(View.GONE);
        textTvGenre.setVisibility(View.GONE);
        textTvSeasons.setVisibility(View.GONE);
        textTvEpisodes.setVisibility(View.GONE);
    }

    private void ending() {
        detailTvProgress.setVisibility(View.GONE);

        textTvMaxRating.setVisibility(View.VISIBLE);
        textTvAiring.setVisibility(View.VISIBLE);
        textTvOverview.setVisibility(View.VISIBLE);
        textTvPopularity.setVisibility(View.VISIBLE);
        textTvGenre.setVisibility(View.VISIBLE);
        textTvSeasons.setVisibility(View.VISIBLE);
        textTvEpisodes.setVisibility(View.VISIBLE);
    }
}

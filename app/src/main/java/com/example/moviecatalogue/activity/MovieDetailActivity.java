package com.example.moviecatalogue.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.lifecycle.Observer;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.model.MovieDetail;
import com.example.moviecatalogue.model.MovieGenre;
import com.example.moviecatalogue.model.MovieResults;
import com.example.moviecatalogue.util.Config;
import com.example.moviecatalogue.viewmodel.MovieViewModel;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    private int movieId;

    private ProgressBar detailMovieProgress;

    private ImageView detailMoviePoster;
    private TextView detailMovieTitle;
    private TextView detailMovieReleaseDate;
    private TextView detailMovieGenre;
    private TextView detailMovieRating;
    private TextView detailMoviePopularity;
    private TextView detailMovieDescription;

    private TextView textMaxRating;
    private TextView textRelease;
    private TextView textOverview;
    private TextView textPopularity;
    private TextView textGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        detailMovieProgress = findViewById(R.id.progress_movie_detail);

        detailMoviePoster = findViewById(R.id.detail_movie_poster);
        detailMovieTitle = findViewById(R.id.detail_movie_title);
        detailMovieReleaseDate = findViewById(R.id.detail_movie_release);
        detailMovieGenre = findViewById(R.id.detail_movie_genre);
        detailMovieRating = findViewById(R.id.detail_movie_rating);
        detailMoviePopularity = findViewById(R.id.detail_movie_popularity);
        detailMovieDescription = findViewById(R.id.detail_movie_description);

        initializing();

        MovieResults movies = getIntent().getParcelableExtra(EXTRA_MOVIE);
        if (movies != null) {
            movieId = movies.getId();
        }

        MovieViewModel movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovieDetailFromRetrofit(movieId, Config.API_KEY).observe(this, getMovieDetail);
    }

    private final Observer<MovieDetail> getMovieDetail = this::detailMovies;

    private void detailMovies(MovieDetail detail) {
        detailMovieTitle.setText(detail.getMovieTitle());
        detailMovieRating.setText(String.valueOf(detail.getMovieRating()));
        detailMovieDescription.setText(detail.getMovieDescription());
        detailMovieReleaseDate.setText(changeDate(detail.getMovieReleaseDate()));
        detailMoviePopularity.setText(String.valueOf(detail.getMoviePopularity()));

        String imageUrl = Config.IMAGE_URL + detail.getMoviePoster();
        Picasso.get()
                .load(imageUrl)
                .into(detailMoviePoster);

        StringBuilder genreBuilder = new StringBuilder();
        for (int i = 0; i < detail.getGenres().size(); i++) {
            MovieGenre movieGenres = detail.getGenres().get(i);
            if (i < detail.getGenres().size() - 1) {
                genreBuilder.append(movieGenres.getName()).append("\n");
                detailMovieGenre.append(movieGenres.getName() + "\n");
            } else {
                detailMovieGenre.append(movieGenres.getName());
                genreBuilder.append(movieGenres.getName());
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
        textMaxRating = findViewById(R.id.text_movie_max_rating);
        textRelease= findViewById(R.id.text_movie_release_date);
        textOverview = findViewById(R.id.text_movie_overview);
        textGenre = findViewById(R.id.text_movie_genre);
        textPopularity = findViewById(R.id.text_movie_popularity);

        detailMovieProgress.setVisibility(View.VISIBLE);

        textMaxRating.setVisibility(View.GONE);
        textRelease.setVisibility(View.GONE);
        textOverview.setVisibility(View.GONE);
        textPopularity.setVisibility(View.GONE);
        textGenre.setVisibility(View.GONE);
    }

    private void ending() {
        detailMovieProgress.setVisibility(View.GONE);

        textMaxRating.setVisibility(View.VISIBLE);
        textRelease.setVisibility(View.VISIBLE);
        textOverview.setVisibility(View.VISIBLE);
        textPopularity.setVisibility(View.VISIBLE);
        textGenre.setVisibility(View.VISIBLE);
    }
}

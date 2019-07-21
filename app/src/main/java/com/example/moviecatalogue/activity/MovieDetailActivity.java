package com.example.moviecatalogue.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView detailMoviePoster = findViewById(R.id.detail_movie_poster);
        TextView detailMovieTitle = findViewById(R.id.detail_movie_title);
        TextView detailMovieReleaseDate = findViewById(R.id.detail_movie_release_date);
        TextView detailMovieGenre = findViewById(R.id.detail_movie_genre);
        TextView detailMovieDuration = findViewById(R.id.detail_movie_duration);
        TextView detailMovieRating = findViewById(R.id.detail_movie_rating);
        TextView detailMovieDirector = findViewById(R.id.detail_movie_director);
        TextView detailMovieActor = findViewById(R.id.detail_movie_actor);
        TextView detailMovieDescription = findViewById(R.id.detail_movie_description);
        TextView detailMoviePlot = findViewById(R.id.detail_movie_plot);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        detailMovieTitle.setText(movie.getMovieTitle());
        detailMovieReleaseDate.setText(movie.getMovieReleaseDate());
        detailMovieGenre.setText(movie.getMovieGenre());
        detailMovieDuration.setText(movie.getMovieDuration());
        detailMovieRating.setText(movie.getMovieRating());
        detailMovieDirector.setText(movie.getMovieDirector());
        detailMovieActor.setText(movie.getMovieActor());
        detailMovieDescription.setText(movie.getMovieDescription());
        detailMoviePlot.setText(movie.getMoviePlot());

        Picasso.get()
                .load(movie.getMoviePoster())
                .into(detailMoviePoster);
    }
}

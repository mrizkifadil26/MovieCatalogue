package com.example.moviecatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.activity.MovieDetailActivity;
import com.example.moviecatalogue.model.Movie;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        final Movie movie = movies.get(position);

        Picasso.get()
                .load(movie.getMoviePoster())
                .into(holder.moviePoster);

        holder.movieTitle.setText(movie.getMovieTitle());
        holder.movieDescription.setText(movie.getMovieDescription());
        holder.movieGenre.setText(movie.getMovieGenre());
        holder.movieDuration.setText(movie.getMovieDuration());
        holder.movieReleaseDate.setText(movie.getMovieReleaseDate());
        if (!movie.getMovieRating().equals("")) {
            holder.movieRating.setText(movie.getMovieRating());
        } else {
            holder.movieRating.setText(R.string.default_rating);
        }

        holder.movieParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(context, MovieDetailActivity.class);
                mIntent.putParcelableArrayListExtra(MovieDetailActivity.EXTRA_MOVIE, movies.get(position));
                context.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePoster;
        TextView movieTitle;
        TextView movieGenre;
        TextView movieDuration;
        TextView movieReleaseDate;
        TextView movieRating;
        TextView movieDescription;
        MaterialCardView movieParent;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieParent = itemView.findViewById(R.id.movie_parent);
            moviePoster = itemView.findViewById(R.id.item_poster);
            movieTitle = itemView.findViewById(R.id.item_title);
            movieGenre = itemView.findViewById(R.id.item_genre);
            movieDuration = itemView.findViewById(R.id.item_duration);
            movieReleaseDate = itemView.findViewById(R.id.item_release_date);
            movieRating = itemView.findViewById(R.id.item_rating);
            movieDescription = itemView.findViewById(R.id.item_description);
        }
    }

}

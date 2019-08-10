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
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.activity.MovieDetailActivity;
import com.example.moviecatalogue.model.MovieGenre;
import com.example.moviecatalogue.model.MovieResults;
import com.example.moviecatalogue.util.Config;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<MovieResults> movies;
    private List<MovieGenre> genres;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setMovies(List<MovieResults> movies, List<MovieGenre> genres) {
        this.movies = movies;
        this.genres = genres;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        final MovieResults movie = movies.get(position);
        String imageUrl = Config.IMAGE_URL + movie.getMoviePoster();

        Picasso.get()
                .load(imageUrl)
                .into(holder.moviePoster);

        holder.movieTitle.setText(movie.getMovieTitle());
        holder.movieRating.setText(String.valueOf(movie.getMovieRating()));

        if (genres != null) {
            holder.movieGenre.setText(holder.getMovieGenres(movies.get(position).getGenreId()));
        }

        holder.movieParent.setOnClickListener(view -> {
            Intent mIntent = new Intent(context, MovieDetailActivity.class);
            mIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movies.get(position));
            context.startActivity(mIntent);
        });
    }

    @Override
    public int getItemCount() {
        if (movies != null) {
            return movies.size();
        } else {
            return 0;
        }
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePoster;
        TextView movieTitle;
        TextView movieGenre;
        TextView movieRating;
        MaterialCardView movieParent;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieParent = itemView.findViewById(R.id.movie_parent);
            moviePoster = itemView.findViewById(R.id.movie_poster);
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieGenre = itemView.findViewById(R.id.movie_genre);
            movieRating = itemView.findViewById(R.id.movie_rating);
        }

        private String getMovieGenres(List<Integer> genreIds) {
            List<String> movieGenres = new ArrayList<>();
            for (Integer genreId : genreIds) {
                for (MovieGenre genre : genres) {
                    if (genre.getId() == genreId) {
                        movieGenres.add(genre.getName());
                        break;
                    }
                }
            }
            return TextUtils.join(" | ", movieGenres);
        }
    }
}

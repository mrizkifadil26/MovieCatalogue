package com.example.moviecatalogue.fragment;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.adapter.MovieAdapter;
import com.example.moviecatalogue.model.Movie;

import java.util.ArrayList;

public class MovieFragment extends Fragment {

    private String[] movieTitle;
    private String[] movieGenre;
    private String[] movieDuration;
    private String[] movieReleaseDate;
    private String[] movieRating;
    private String[] movieDescription;
    private String[] movieDirector;
    private String[] movieActor;
    private String[] moviePlot;
    private TypedArray moviePoster;

    private MovieAdapter movieAdapter;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentLayout = inflater.inflate(R.layout.fragment_movie, container, false);
        RecyclerView recyclerMovie = parentLayout.findViewById(R.id.item_movie);

        movieAdapter = new MovieAdapter(getActivity());
        recyclerMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerMovie.setAdapter(movieAdapter);

        prepare();
        addItem();

        return parentLayout;
    }

    private void prepare() {
        moviePoster = getResources().obtainTypedArray(R.array.movie_poster);
        movieTitle = getResources().getStringArray(R.array.movie_title);
        movieReleaseDate = getResources().getStringArray(R.array.movie_release_date);
        movieGenre = getResources().getStringArray(R.array.movie_genre);
        movieDuration = getResources().getStringArray(R.array.movie_duration);
        movieRating = getResources().getStringArray(R.array.movie_rating);
        movieDescription = getResources().getStringArray(R.array.movie_description);
        movieDirector = getResources().getStringArray(R.array.movie_director);
        movieActor = getResources().getStringArray(R.array.movie_actor);
        moviePlot = getResources().getStringArray(R.array.movie_plot);
    }

    private void addItem() {

        ArrayList<Movie> movies = new ArrayList<>();

        for (int i = 0; i < movieTitle.length; i++) {
            Movie movie = new Movie();
            movie.setMoviePoster(moviePoster.getResourceId(i, -1));
            movie.setMovieTitle(movieTitle[i]);
            movie.setMovieGenre(movieGenre[i]);
            movie.setMovieDuration(movieDuration[i]);
            movie.setMovieReleaseDate(movieReleaseDate[i]);
            movie.setMovieRating(movieRating[i]);
            movie.setMovieDescription(movieDescription[i]);
            movie.setMovieDirector(movieDirector[i]);
            movie.setMovieActor(movieActor[i]);
            movie.setMoviePlot(moviePlot[i]);
            movies.add(movie);
        }

        movieAdapter.setMovies(movies);
    }
}

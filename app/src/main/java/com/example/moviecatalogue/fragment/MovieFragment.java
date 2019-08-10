package com.example.moviecatalogue.fragment;


import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.adapter.MovieAdapter;
import com.example.moviecatalogue.model.MovieGenre;
import com.example.moviecatalogue.model.MovieResults;
import com.example.moviecatalogue.viewmodel.MovieViewModel;

import java.util.List;

public class MovieFragment extends Fragment {

    private MovieAdapter movieAdapter;
    private ProgressBar movieProgress;
    private List<MovieGenre> genres;

    private RecyclerView movieRecycler;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int orientation = getResources().getConfiguration().orientation;
        movieRecycler = view.findViewById(R.id.item_movie);
        movieProgress = view.findViewById(R.id.progress_movie);

        movieProgress.setVisibility(View.VISIBLE);
        if (orientation != Configuration.ORIENTATION_LANDSCAPE) {
            movieRecycler.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        } else {
            movieRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayout.HORIZONTAL, false));
        }

        movieAdapter = new MovieAdapter(view.getContext());

        MovieViewModel movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovieGenreFromRetrofit().observe(this, getGenreData);
        movieViewModel.getMovieFromRetrofit().observe(this, getMovieData);
    }

    private final Observer<List<MovieResults>> getMovieData = new Observer<List<MovieResults>>() {
        @Override
        public void onChanged(List<MovieResults> movieResults) {
            movieAdapter.setMovies(movieResults, genres);
            movieAdapter.notifyDataSetChanged();
            movieRecycler.setAdapter(movieAdapter);
            movieProgress.setVisibility(View.GONE);
        }
    };

    private final Observer<List<MovieGenre>> getGenreData = new Observer<List<MovieGenre>>() {
        @Override
        public void onChanged(List<MovieGenre> genreResults) {
            genres = genreResults;
        }
    };

}

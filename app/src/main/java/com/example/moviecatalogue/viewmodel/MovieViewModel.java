package com.example.moviecatalogue.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.model.MovieDetail;
import com.example.moviecatalogue.model.MovieGenre;
import com.example.moviecatalogue.model.MovieResults;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<List<MovieResults>> movies;
    private MutableLiveData<List<MovieGenre>> genres;
    private Movie movie;

    public MovieViewModel() {
        movie = Movie.getInstance();
        movies = movie.getMovieFromRetrofit();
        genres = movie.getMovieGenreFromRetrofit();
    }

    public LiveData<List<MovieResults>> getMovieFromRetrofit() {
        return movies;
    }

    public LiveData<List<MovieGenre>> getMovieGenreFromRetrofit() {
        return genres;
    }

    public LiveData<MovieDetail> getMovieDetailFromRetrofit(int id, String apiKey) {
        return movie.getMovieDetailFromRetrofit(id, apiKey);
    }
}

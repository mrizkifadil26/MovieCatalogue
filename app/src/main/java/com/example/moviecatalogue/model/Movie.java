package com.example.moviecatalogue.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.moviecatalogue.service.ApiService;
import com.example.moviecatalogue.service.RetrofitService;
import com.example.moviecatalogue.util.Config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movie {

    private ApiService apiService;
    private static Movie movie;

    private Movie(ApiService apiService) {
        this.apiService = apiService;
    }

    public static Movie getInstance() {
        if (movie == null) {
            movie = new Movie(RetrofitService.createService(ApiService.class));
        }
        return movie;
    }

    public MutableLiveData<List<MovieResults>> getMovieFromRetrofit() {
        final MutableLiveData<List<MovieResults>> movies = new MutableLiveData<>();
        apiService.getMovieFromApi(Config.API_KEY).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        movies.setValue(response.body().getResults());
                    }
                }
                Log.d("Movie Results", "onResponse: " + response.message());
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                Log.e("Movie Model", "onFailure: " + t.getMessage());
            }
        });
        return movies;
    }

    public MutableLiveData<List<MovieGenre>> getMovieGenreFromRetrofit() {
        final MutableLiveData<List<MovieGenre>> genres = new MutableLiveData<>();
        apiService.getMovieGenreFromApi(Config.API_KEY).enqueue(new Callback<MovieGenreResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieGenreResponse> call, @NonNull Response<MovieGenreResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        genres.setValue(response.body().getGenres());
                    }
                }
                Log.d("Movie Genre Results", "onResponse: " + response.message());
            }

            @Override
            public void onFailure(@NonNull Call<MovieGenreResponse> call, @NonNull Throwable t) {
                Log.e("Genre Model", "onFailure: " + t.getMessage());
            }
        });
        return genres;
    }

    public MutableLiveData<MovieDetail> getMovieDetailFromRetrofit(int id, String apiKey) {
        final MutableLiveData<MovieDetail> movieDetail = new MutableLiveData<>();
        apiService.getMovieDetailFromApi(id, apiKey).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetail> call, @NonNull Response<MovieDetail> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        movieDetail.setValue(response.body());
                    }
                }
                Log.d("Movie Detail Results", "onResponse: " + response.message());
            }

            @Override
            public void onFailure(@NonNull Call<MovieDetail> call, @NonNull Throwable t) {
                Log.e("Movie Detail", "onFailure: " + t.getMessage());
            }
        });
        return movieDetail;
    }
}
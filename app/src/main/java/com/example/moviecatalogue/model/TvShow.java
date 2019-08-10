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

public class TvShow {

    private ApiService apiService;
    private static TvShow tvShow;

    private TvShow(ApiService apiService) {
        this.apiService = apiService;
    }

    public static TvShow getInstance() {
        if (tvShow == null) {
            tvShow = new TvShow(RetrofitService.createService(ApiService.class));
        }
        return tvShow;
    }

    public MutableLiveData<List<TvShowResults>> getTvFromRetrofit() {
        final MutableLiveData<List<TvShowResults>> tvShows = new MutableLiveData<>();
        apiService.getTvFromApi(Config.API_KEY).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                if (response.body() != null) {
                    tvShows.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                Log.e("TvShow Model", "onFailure: " + t.getMessage());
            }
        });
        return tvShows;
    }

    public MutableLiveData<List<TvShowGenre>> getTvGenreFromRetrofit() {
        final MutableLiveData<List<TvShowGenre>> tvGenres = new MutableLiveData<>();
        apiService.getTvGenreFromApi(Config.API_KEY).enqueue(new Callback<TvShowGenreResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowGenreResponse> call, @NonNull Response<TvShowGenreResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        tvGenres.setValue(response.body().getTvShowGenres());
                    }
                }
                Log.d("Genre Results", "onResponse: " + response.message());
            }

            @Override
            public void onFailure(@NonNull Call<TvShowGenreResponse> call, @NonNull Throwable t) {
                Log.e("Tv Genre Model", "onFailure: " + t.getMessage());
            }
        });
        return tvGenres;
    }

    public MutableLiveData<TvShowDetail> getTvDetailFromRetrofit(int id, String apiKey) {
        final MutableLiveData<TvShowDetail> tvDetails = new MutableLiveData<>();
        apiService.getTvDetailFromApi(id, apiKey).enqueue(new Callback<TvShowDetail>() {
            @Override
            public void onResponse(@NonNull Call<TvShowDetail> call, @NonNull Response<TvShowDetail> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        tvDetails.setValue(response.body());
                    }
                }
                Log.d("Tv Detail Results", "onResponse: " + response.message());
            }

            @Override
            public void onFailure(@NonNull Call<TvShowDetail> call, @NonNull Throwable t) {
                Log.e("Tv Detail", "onFailure: " + t.getMessage());
            }
        });
        return tvDetails;
    }

}

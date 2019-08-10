package com.example.moviecatalogue.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviecatalogue.model.TvShow;
import com.example.moviecatalogue.model.TvShowDetail;
import com.example.moviecatalogue.model.TvShowGenre;
import com.example.moviecatalogue.model.TvShowResults;

import java.util.List;

public class TvShowViewModel extends ViewModel {

    private MutableLiveData<List<TvShowResults>> tvShows;
    private MutableLiveData<List<TvShowGenre>> tvGenres;
    private TvShow tvShow;

    public TvShowViewModel() {
        tvShow = TvShow.getInstance();
        tvShows = tvShow.getTvFromRetrofit();
        tvGenres = tvShow.getTvGenreFromRetrofit();
    }

    public LiveData<List<TvShowResults>> getTvFromRetrofit() {
        return tvShows;
    }

    public LiveData<List<TvShowGenre>> getTvGenreFromRetrofit() {
        return tvGenres;
    }

    public LiveData<TvShowDetail> getTvDetailFromRetrofit(int id, String apiKey) {
        return tvShow.getTvDetailFromRetrofit(id, apiKey);
    }

}

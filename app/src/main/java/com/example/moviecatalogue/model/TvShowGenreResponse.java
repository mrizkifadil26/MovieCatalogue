package com.example.moviecatalogue.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowGenreResponse {

    @SerializedName("genres")
    @Expose
    private List<TvShowGenre> genres;

    List<TvShowGenre> getTvShowGenres() {
        return genres;
    }

    public void setTvShowGenres(List<TvShowGenre> genres) {
        this.genres = genres;
    }

}

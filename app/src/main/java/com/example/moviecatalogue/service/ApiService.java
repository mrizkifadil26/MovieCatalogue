package com.example.moviecatalogue.service;

import com.example.moviecatalogue.model.MovieDetail;
import com.example.moviecatalogue.model.MovieGenreResponse;
import com.example.moviecatalogue.model.MovieResponse;
import com.example.moviecatalogue.model.TvShowDetail;
import com.example.moviecatalogue.model.TvShowGenreResponse;
import com.example.moviecatalogue.model.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("discover/movie")
    Call<MovieResponse> getMovieFromApi(@Query("api_key") String apiKey);

    @GET("genre/movie/list")
    Call<MovieGenreResponse> getMovieGenreFromApi(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieDetail> getMovieDetailFromApi(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("discover/tv")
    Call<TvShowResponse> getTvFromApi(@Query("api_key") String apiKey);

    @GET("genre/tv/list")
    Call<TvShowGenreResponse> getTvGenreFromApi(@Query("api_key") String apiKey);

    @GET("tv/{id}")
    Call<TvShowDetail> getTvDetailFromApi(@Path("id") int id, @Query("api_key") String apiKey);
}

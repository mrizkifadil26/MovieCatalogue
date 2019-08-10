package com.example.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieDetail implements Parcelable {

    @SerializedName("vote_average")
    private double movieRating;

    @SerializedName("title")
    private String movieTitle;

    @SerializedName("poster_path")
    private String moviePoster;

    @SerializedName("overview")
    private String movieDescription;

    @SerializedName("release_date")
    private String movieReleaseDate;

    @SerializedName("popularity")
    private double moviePopularity;

    @SerializedName("genres")
    private List<MovieGenre> genres;

    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(double movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public double getMoviePopularity() {
        return moviePopularity;
    }

    public void setMoviePopularity(double moviePopularity) {
        this.moviePopularity = moviePopularity;
    }

    public List<MovieGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<MovieGenre> genres) {
        this.genres = genres;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.movieRating);
        dest.writeString(this.movieTitle);
        dest.writeString(this.moviePoster);
        dest.writeString(this.movieDescription);
        dest.writeString(this.movieReleaseDate);
        dest.writeDouble(this.moviePopularity);
        dest.writeList(this.genres);
        dest.writeInt(this.id);
    }

    public MovieDetail() {
    }

    private MovieDetail(Parcel in) {
        this.movieRating = in.readDouble();
        this.movieTitle = in.readString();
        this.moviePoster = in.readString();
        this.movieDescription = in.readString();
        this.movieReleaseDate = in.readString();
        this.moviePopularity = in.readDouble();
        this.genres = new ArrayList<>();
        in.readList(this.genres, MovieGenre.class.getClassLoader());
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<MovieDetail> CREATOR = new Parcelable.Creator<MovieDetail>() {
        @Override
        public MovieDetail createFromParcel(Parcel source) {
            return new MovieDetail(source);
        }

        @Override
        public MovieDetail[] newArray(int size) {
            return new MovieDetail[size];
        }
    };
}

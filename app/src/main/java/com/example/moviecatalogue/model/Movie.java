package com.example.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Movie extends ArrayList<Parcelable> implements Parcelable {

    private int moviePoster;
    private String movieTitle;
    private String movieGenre;
    private String movieDuration;
    private String movieReleaseDate;
    private String movieRating;
    private String movieDescription;

    private String movieActor;
    private String movieDirector;
    private String moviePlot;

    public Movie() {
    }

    private Movie(Parcel in) {
        moviePoster = in.readInt();
        movieTitle = in.readString();
        movieGenre = in.readString();
        movieDuration = in.readString();
        movieReleaseDate = in.readString();
        movieRating = in.readString();
        movieDescription = in.readString();
        movieActor = in.readString();
        movieDirector = in.readString();
        moviePlot = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(int moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getMovieActor() {
        return movieActor;
    }

    public void setMovieActor(String movieActor) {
        this.movieActor = movieActor;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMoviePlot() {
        return moviePlot;
    }

    public void setMoviePlot(String moviePlot) {
        this.moviePlot = moviePlot;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(moviePoster);
        parcel.writeString(movieTitle);
        parcel.writeString(movieGenre);
        parcel.writeString(movieDuration);
        parcel.writeString(movieReleaseDate);
        parcel.writeString(movieRating);
        parcel.writeString(movieDescription);
        parcel.writeString(movieActor);
        parcel.writeString(movieDirector);
        parcel.writeString(moviePlot);
    }
}

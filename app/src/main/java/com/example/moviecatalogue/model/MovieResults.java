package com.example.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieResults implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("vote_average")
    private double movieRating;

    @SerializedName("title")
    private String movieTitle;

    @SerializedName("poster_path")
    private String moviePoster;

    @SerializedName("genre_ids")
    private List<Integer> genreId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getGenreId() {
        return genreId;
    }

    public void setGenreId(List<Integer> genreId) {
        this.genreId = genreId;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeDouble(this.movieRating);
        dest.writeString(this.movieTitle);
        dest.writeString(this.moviePoster);
        dest.writeList(this.genreId);
    }

    public MovieResults() {
    }

    private MovieResults(Parcel in) {
        this.id = in.readInt();
        this.movieRating = in.readDouble();
        this.movieTitle = in.readString();
        this.moviePoster = in.readString();
        this.genreId = new ArrayList<>();
        in.readList(this.genreId, Integer.class.getClassLoader());
    }

    public static final Creator<MovieResults> CREATOR = new Creator<MovieResults>() {
        @Override
        public MovieResults createFromParcel(Parcel source) {
            return new MovieResults(source);
        }

        @Override
        public MovieResults[] newArray(int size) {
            return new MovieResults[size];
        }
    };
}

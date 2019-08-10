package com.example.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TvShowResults implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("vote_average")
    private double tvRating;

    @SerializedName("name")
    private String tvTitle;

    @SerializedName("poster_path")
    private String tvPoster;

    @SerializedName("genre_ids")
    private List<Integer> tvGenreId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTvRating() {
        return tvRating;
    }

    public void setTvRating(double tvRating) {
        this.tvRating = tvRating;
    }

    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
    }

    public String getTvPoster() {
        return tvPoster;
    }

    public void setTvPoster(String tvPoster) {
        this.tvPoster = tvPoster;
    }

    public List<Integer> getTvGenreId() {
        return tvGenreId;
    }

    public void setTvGenreId(List<Integer> tvGenreId) {
        this.tvGenreId = tvGenreId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeDouble(this.tvRating);
        dest.writeString(this.tvTitle);
        dest.writeString(this.tvPoster);
        dest.writeList(this.tvGenreId);
    }

    public TvShowResults() {
    }

    private TvShowResults(Parcel in) {
        this.id = in.readInt();
        this.tvRating = in.readDouble();
        this.tvTitle = in.readString();
        this.tvPoster = in.readString();
        this.tvGenreId = new ArrayList<>();
        in.readList(this.tvGenreId, Integer.class.getClassLoader());
    }

    public static final Creator<TvShowResults> CREATOR = new Creator<TvShowResults>() {
        @Override
        public TvShowResults createFromParcel(Parcel source) {
            return new TvShowResults(source);
        }

        @Override
        public TvShowResults[] newArray(int size) {
            return new TvShowResults[size];
        }
    };
}

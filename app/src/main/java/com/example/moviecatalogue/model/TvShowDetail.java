package com.example.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TvShowDetail implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("vote_average")
    private double tvRating;

    @SerializedName("name")
    private String tvTitle;

    @SerializedName("poster_path")
    private String tvPoster;

    @SerializedName("overview")
    private String tvDescription;

    @SerializedName("first_air_date")
    private String tvReleaseDate;

    @SerializedName("popularity")
    private double tvPopularity;

    @SerializedName("genres")
    private List<TvShowGenre> tvGenres;

    @SerializedName("number_of_seasons")
    private int tvSeasons;

    @SerializedName("number_of_episodes")
    private int tvEpisodes;

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

    public String getTvDescription() {
        return tvDescription;
    }

    public void setTvDescription(String tvDescription) {
        this.tvDescription = tvDescription;
    }

    public String getTvReleaseDate() {
        return tvReleaseDate;
    }

    public void setTvReleaseDate(String tvReleaseDate) {
        this.tvReleaseDate = tvReleaseDate;
    }

    public double getTvPopularity() {
        return tvPopularity;
    }

    public void setTvPopularity(double tvPopularity) {
        this.tvPopularity = tvPopularity;
    }

    public List<TvShowGenre> getTvGenres() {
        return tvGenres;
    }

    public void setTvGenres(List<TvShowGenre> tvGenres) {
        this.tvGenres = tvGenres;
    }

    public int getTvSeasons() {
        return tvSeasons;
    }

    public void setTvSeasons(int tvSeasons) {
        this.tvSeasons = tvSeasons;
    }

    public int getTvEpisodes() {
        return tvEpisodes;
    }

    public void setTvEpisodes(int tvEpisodes) {
        this.tvEpisodes = tvEpisodes;
    }

    public TvShowDetail() {
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
        dest.writeString(this.tvDescription);
        dest.writeString(this.tvReleaseDate);
        dest.writeDouble(this.tvPopularity);
        dest.writeList(this.tvGenres);
        dest.writeInt(this.tvSeasons);
        dest.writeInt(this.tvEpisodes);
    }

    protected TvShowDetail(Parcel in) {
        this.id = in.readInt();
        this.tvRating = in.readDouble();
        this.tvTitle = in.readString();
        this.tvPoster = in.readString();
        this.tvDescription = in.readString();
        this.tvReleaseDate = in.readString();
        this.tvPopularity = in.readDouble();
        this.tvGenres = new ArrayList<>();
        in.readList(this.tvGenres, TvShowGenre.class.getClassLoader());
        this.tvSeasons = in.readInt();
        this.tvEpisodes = in.readInt();
    }

    public static final Creator<TvShowDetail> CREATOR = new Creator<TvShowDetail>() {
        @Override
        public TvShowDetail createFromParcel(Parcel source) {
            return new TvShowDetail(source);
        }

        @Override
        public TvShowDetail[] newArray(int size) {
            return new TvShowDetail[size];
        }
    };
}

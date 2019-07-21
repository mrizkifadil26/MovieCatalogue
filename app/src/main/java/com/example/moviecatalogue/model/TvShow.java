package com.example.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class TvShow extends ArrayList<Parcelable> implements Parcelable {

    private int tvPoster;
    private String tvTitle;
    private String tvGenre;
    private String tvDuration;
    private String tvReleaseDate;
    private String tvRating;
    private String tvDescription;

    private String tvActor;
    private String tvDirector;
    private String tvSeason;
    private String tvEpisode;
    private String tvPlot;

    public TvShow() {
    }

    public int getTvPoster() {
        return tvPoster;
    }

    public void setTvPoster(int tvPoster) {
        this.tvPoster = tvPoster;
    }

    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
    }

    public String getTvGenre() {
        return tvGenre;
    }

    public void setTvGenre(String tvGenre) {
        this.tvGenre = tvGenre;
    }

    public String getTvDuration() {
        return tvDuration;
    }

    public void setTvDuration(String tvDuration) {
        this.tvDuration = tvDuration;
    }

    public String getTvReleaseDate() {
        return tvReleaseDate;
    }

    public void setTvReleaseDate(String tvReleaseDate) {
        this.tvReleaseDate = tvReleaseDate;
    }

    public String getTvRating() {
        return tvRating;
    }

    public void setTvRating(String tvRating) {
        this.tvRating = tvRating;
    }

    public String getTvDescription() {
        return tvDescription;
    }

    public void setTvDescription(String tvDescription) {
        this.tvDescription = tvDescription;
    }

    public String getTvActor() {
        return tvActor;
    }

    public void setTvActor(String tvActor) {
        this.tvActor = tvActor;
    }

    public String getTvDirector() {
        return tvDirector;
    }

    public void setTvDirector(String tvDirector) {
        this.tvDirector = tvDirector;
    }

    public String getTvSeason() {
        return tvSeason;
    }

    public void setTvSeason(String tvSeason) {
        this.tvSeason = tvSeason;
    }

    public String getTvEpisode() {
        return tvEpisode;
    }

    public void setTvEpisode(String tvEpisode) {
        this.tvEpisode = tvEpisode;
    }

    public String getTvPlot() {
        return tvPlot;
    }

    public void setTvPlot(String tvPlot) {
        this.tvPlot = tvPlot;
    }

    private TvShow(Parcel in) {
        tvPoster = in.readInt();
        tvTitle = in.readString();
        tvGenre = in.readString();
        tvDuration = in.readString();
        tvReleaseDate = in.readString();
        tvRating = in.readString();
        tvDescription = in.readString();
        tvActor = in.readString();
        tvDirector = in.readString();
        tvSeason = in.readString();
        tvEpisode = in.readString();
        tvPlot = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(tvPoster);
        dest.writeString(tvTitle);
        dest.writeString(tvGenre);
        dest.writeString(tvDuration);
        dest.writeString(tvReleaseDate);
        dest.writeString(tvRating);
        dest.writeString(tvDescription);
        dest.writeString(tvActor);
        dest.writeString(tvDirector);
        dest.writeString(tvSeason);
        dest.writeString(tvEpisode);
        dest.writeString(tvPlot);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}

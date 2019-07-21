package com.example.moviecatalogue.fragment;


import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.adapter.TvShowAdapter;
import com.example.moviecatalogue.model.TvShow;

import java.util.ArrayList;

public class TvShowFragment extends Fragment {

    private String[] tvTitle;
    private String[] tvGenre;
    private String[] tvDuration;
    private String[] tvReleaseDate;
    private String[] tvRating;
    private String[] tvSeason;
    private String[] tvEpisode;
    private String[] tvDescription;
    private String[] tvCreator;
    private String[] tvActor;
    private String[] tvPlot;
    private TypedArray tvPoster;

    private TvShowAdapter tvAdapter;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int orientation = getResources().getConfiguration().orientation;

        View rootView = inflater.inflate(R.layout.fragment_tv_shows, container, false);
        RecyclerView recyclerTv = rootView.findViewById(R.id.item_tv);

        tvAdapter = new TvShowAdapter(getActivity());
        if (orientation != Configuration.ORIENTATION_LANDSCAPE) {
            recyclerTv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            recyclerTv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false));
        }
        recyclerTv.setAdapter(tvAdapter);

        prepare();
        addItem();

        return rootView;
    }

    private void prepare() {
        tvPoster  = getResources().obtainTypedArray(R.array.tv_poster);
        tvTitle = getResources().getStringArray(R.array.tv_title);
        tvReleaseDate = getResources().getStringArray(R.array.tv_release_date);
        tvGenre = getResources().getStringArray(R.array.tv_genre);
        tvDuration = getResources().getStringArray(R.array.tv_duration);
        tvRating = getResources().getStringArray(R.array.tv_rating);
        tvSeason = getResources().getStringArray(R.array.tv_season);
        tvEpisode = getResources().getStringArray(R.array.tv_episode);
        tvDescription = getResources().getStringArray(R.array.tv_description);
        tvCreator = getResources().getStringArray(R.array.tv_director);
        tvActor = getResources().getStringArray(R.array.tv_actor);
        tvPlot = getResources().getStringArray(R.array.tv_plot);
    }

    private void addItem() {

        ArrayList<TvShow> tvShows = new ArrayList<>();

        for (int i = 0; i < tvTitle.length; i++) {
            TvShow tvShow = new TvShow();
            tvShow.setTvPoster(tvPoster.getResourceId(i, -1));
            tvShow.setTvTitle(tvTitle[i]);
            tvShow.setTvGenre(tvGenre[i]);
            tvShow.setTvDuration(tvDuration[i]);
            tvShow.setTvReleaseDate(tvReleaseDate[i]);
            tvShow.setTvRating(tvRating[i]);
            tvShow.setTvSeason(tvSeason[i]);
            tvShow.setTvEpisode(tvEpisode[i]);
            tvShow.setTvDescription(tvDescription[i]);
            tvShow.setTvDirector(tvCreator[i]);
            tvShow.setTvActor(tvActor[i]);
            tvShow.setTvPlot(tvPlot[i]);
            tvShows.add(tvShow);
        }

        tvAdapter.setTvShows(tvShows);
    }

}

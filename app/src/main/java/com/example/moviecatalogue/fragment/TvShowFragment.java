package com.example.moviecatalogue.fragment;


import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.adapter.TvShowAdapter;
import com.example.moviecatalogue.model.TvShowGenre;
import com.example.moviecatalogue.model.TvShowResults;
import com.example.moviecatalogue.viewmodel.TvShowViewModel;

import java.util.List;

public class TvShowFragment extends Fragment {

    private TvShowAdapter tvAdapter;
    private ProgressBar tvProgress;
    private List<TvShowGenre> tvGenres;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_tv_shows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int orientation = getResources().getConfiguration().orientation;
        RecyclerView recyclerTv = view.findViewById(R.id.item_tv);

        tvProgress = view.findViewById(R.id.progress_tv);
        tvProgress.setVisibility(View.VISIBLE);

        if (orientation != Configuration.ORIENTATION_LANDSCAPE) {
            recyclerTv.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        } else {
            recyclerTv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayout.HORIZONTAL, false));
        }

        tvAdapter = new TvShowAdapter(view.getContext());
        recyclerTv.setAdapter(tvAdapter);

        TvShowViewModel tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        tvShowViewModel.getTvFromRetrofit().observe(this, getTvShowData);
        tvShowViewModel.getTvGenreFromRetrofit().observe(this, getTvShowGenreData);
    }

    private final Observer<List<TvShowResults>> getTvShowData = new Observer<List<TvShowResults>>() {
        @Override
        public void onChanged(List<TvShowResults> tvShowResults) {
            tvAdapter.setTvShows(tvShowResults, tvGenres);
            tvAdapter.notifyDataSetChanged();
            tvProgress.setVisibility(View.GONE);
        }
    };

    private final Observer<List<TvShowGenre>> getTvShowGenreData = new Observer<List<TvShowGenre>>() {
        @Override
        public void onChanged(List<TvShowGenre> tvShowGenres) {
            tvGenres = tvShowGenres;
        }
    };
}

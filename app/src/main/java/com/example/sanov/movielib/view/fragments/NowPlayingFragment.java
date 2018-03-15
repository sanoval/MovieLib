package com.example.sanov.movielib.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sanov.movielib.BuildConfig;
import com.example.sanov.movielib.R;
import com.example.sanov.movielib.adapter.MovieNowPlayingAdapter;
import com.example.sanov.movielib.api.MovieNowPlayingService;
import com.example.sanov.movielib.model.Movie;
import com.example.sanov.movielib.model.MovieResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment {

    RecyclerView recyclerView;
    MovieNowPlayingAdapter movieNowPlayingAdapter;


    public NowPlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        recyclerView = view.findViewById(R.id.rv_movie_now_playing_list);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MovieNowPlayingService service = retrofit.create(MovieNowPlayingService.class);
        Call<MovieResponse> call = service.listMovie(BuildConfig.API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null) {
                    List<Movie> movies = response.body().getResults();
                    movieNowPlayingAdapter = new MovieNowPlayingAdapter(movies, R.layout.item_now_playing, getActivity());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(movieNowPlayingAdapter);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
            }
        });
        return view;
    }

}

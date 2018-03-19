package com.example.sanov.movielib.view.activities;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.sanov.movielib.BuildConfig;
import com.example.sanov.movielib.R;
import com.example.sanov.movielib.adapter.MovieAdapter;
import com.example.sanov.movielib.adapter.MovieUpcomingAdapter;
import com.example.sanov.movielib.api.MovieService;
import com.example.sanov.movielib.model.Movie;
import com.example.sanov.movielib.model.MovieResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieSearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    SearchView searchView;
    private String keywords;
    private static final String KEYWORDS = "keywords";
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);

        recyclerView = findViewById(R.id.rv_movie_search);
        setTitle(getResources().getString(R.string.cari));

        if(savedInstanceState != null) {
            keywords = savedInstanceState.getString(KEYWORDS);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        searchView =
                (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        if (keywords != null && !keywords.isEmpty()) {
            searchView.onActionViewExpanded();
            searchView.setQuery(keywords, true);
            searchMovie(keywords);
            searchView.clearFocus();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchMovie(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchMovie(s);
                return false;
            }
        });
        return true;
    }

    private void searchMovie(String s) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MovieService service = retrofit.create(MovieService.class);
        Call<MovieResponse> call = service.listMovie(s, BuildConfig.API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null) {
                    movies = response.body().getResults();
                    movieAdapter = new MovieAdapter(movies, R.layout.item_movie, getApplicationContext());
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                    recyclerView.setAdapter(movieAdapter);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEYWORDS, searchView.getQuery().toString());
    }
}

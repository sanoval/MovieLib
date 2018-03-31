package com.example.sanov.movielib.api;

import com.example.sanov.movielib.model.Movie;
import com.example.sanov.movielib.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sanov on 2/23/2018.
 */

public interface MovieService {
    @GET("search/movie")
    Call<MovieResponse> listMovie(@Query("query") String query, @Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<MovieResponse> listNowPlaying(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MovieResponse> listUpcoming(@Query("api_key") String apiKey);
}

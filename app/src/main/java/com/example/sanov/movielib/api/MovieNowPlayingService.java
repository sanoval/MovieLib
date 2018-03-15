package com.example.sanov.movielib.api;

import com.example.sanov.movielib.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sanov on 3/5/2018.
 */

public interface MovieNowPlayingService {
    @GET("movie/now_playing")
    Call<MovieResponse> listMovie(@Query("api_key") String apiKey);
}

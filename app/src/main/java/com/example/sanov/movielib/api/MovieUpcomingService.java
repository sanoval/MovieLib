package com.example.sanov.movielib.api;

import com.example.sanov.movielib.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sanov on 3/2/2018.
 */

public interface MovieUpcomingService {
    @GET("movie/upcoming")
    Call<MovieResponse> listMovie(@Query("api_key") String apiKey);
}

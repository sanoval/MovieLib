package com.example.sanov.movielib.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanov.movielib.BuildConfig;
import com.example.sanov.movielib.view.activities.MovieDetailActivity;
import com.example.sanov.movielib.R;
import com.example.sanov.movielib.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sanov on 2/23/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private List<Movie> movies;
    private int rowLayout;
    private Context context;

    public MovieAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        String movieTitle = ResizeOverview(movies.get(position).getTitle(), "title");
        holder.tvMovieTitle.setText(movieTitle);
        Picasso.with(context).load(BuildConfig.BASE_URL_POSTER_PATH_SMALL + movies.get(position).getPosterPath()).into(holder.posterPath);

        holder.moviesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailMovieIntent = new Intent (context, MovieDetailActivity.class);
                detailMovieIntent.putExtra(MovieDetailActivity.MOVIE_DATA, movies.get(position));
                context.startActivity(detailMovieIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView tvMovieTitle;
        ImageView posterPath;
        ConstraintLayout moviesLayout;

        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = v.findViewById(R.id.movies_layout);
            tvMovieTitle = v.findViewById(R.id.tv_movie_title);
            posterPath = v.findViewById(R.id.img_poster);

        }
    }

    private String ResizeOverview(String chars, String field) {
        if (chars.length() > 80 && field == "overview") {
            chars = chars.substring(0, 80) + "...";
        } else if (chars.length() > 30 && field == "title") {
            chars = chars.substring(0, 30) + "...";
        }
        return chars;
    }


}

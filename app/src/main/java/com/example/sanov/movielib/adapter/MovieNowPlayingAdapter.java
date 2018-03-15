package com.example.sanov.movielib.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanov.movielib.BuildConfig;
import com.example.sanov.movielib.R;
import com.example.sanov.movielib.helper.MyHelper;
import com.example.sanov.movielib.model.Movie;
import com.example.sanov.movielib.view.activities.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sanov on 3/2/2018.
 */

public class MovieNowPlayingAdapter extends RecyclerView.Adapter<MovieNowPlayingAdapter.MovieNowPlaying> {

    private List<Movie> movieNowPlaying;
    private Context context;
    private int rowLayout;
    private MyHelper myHelper = new MyHelper();

    public MovieNowPlayingAdapter(List<Movie> movieNowPlaying, int rowLayout, Context context) {
        this.movieNowPlaying = movieNowPlaying;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MovieNowPlaying onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieNowPlaying(view);
    }

    @Override
    public void onBindViewHolder(MovieNowPlaying holder, final int position) {
        String movieTitle = myHelper.ResizeOverview(movieNowPlaying.get(position).getTitle(), "title");
        String movieOverview = myHelper.ResizeOverview(movieNowPlaying.get(position).getOverview(), "overview");
        String movieDateRelease = movieNowPlaying.get(position).getReleaseDate();
        Picasso.with(context).load(BuildConfig.BASE_URL_POSTER_PATH_SMALL + movieNowPlaying.get(position).getPosterPath()).into(holder.imgPoster);

        holder.tvMovieTitle.setText(movieTitle);
        holder.tvMovieOverview.setText(movieOverview);
        holder.tvDateRelease.setText(movieDateRelease);
        holder.btnShowDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailMovieIntent = new Intent(context, MovieDetailActivity.class);
                detailMovieIntent.putExtra(MovieDetailActivity.MOVIE_DATA, movieNowPlaying.get(position));
                context.startActivity(detailMovieIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieNowPlaying.size();
    }
    public static class MovieNowPlaying extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvMovieTitle, tvMovieOverview, tvDateRelease;
        ConstraintLayout moviesNowPlayingLayout;
        Button btnShowDetail;

        public MovieNowPlaying(View view) {
            super(view);
            moviesNowPlayingLayout = view.findViewById(R.id.movie_now_playing_layout);
            imgPoster = view.findViewById(R.id.img_poster);
            tvDateRelease = view.findViewById(R.id.tv_date_release);
            tvMovieOverview = view.findViewById(R.id.tv_overview);
            tvMovieTitle = view.findViewById(R.id.tv_movie_title);
            btnShowDetail = view.findViewById(R.id.btn_show_detail);

        }
    }
}

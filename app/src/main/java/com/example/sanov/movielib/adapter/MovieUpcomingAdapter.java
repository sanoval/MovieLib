package com.example.sanov.movielib.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by sanov on 3/5/2018.
 */

public class MovieUpcomingAdapter extends RecyclerView.Adapter<MovieUpcomingAdapter.MovieUpcoming> {
    private List<Movie> movieUpcoming;
    private Context context;
    private int rowLayout;
    private MyHelper myHelper = new MyHelper();

    public MovieUpcomingAdapter(List<Movie> movieUpcoming, int rowLayout, Context context) {
        this.movieUpcoming = movieUpcoming;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MovieUpcoming onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieUpcoming(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(MovieUpcoming holder, final int position) {
        Movie movie = movieUpcoming.get(position);
        String movieTitle = myHelper.ResizeOverview(movie.getTitle(), "title");
        String movieOverview = myHelper.ResizeOverview(movie.getOverview(), "overview");
        String movieDateRelease = movie.getReleaseDate();
        String newDate = myHelper.ConvertDateFormat(movieDateRelease);

        Picasso.with(context).load(BuildConfig.BASE_URL_POSTER_PATH_SMALL + movie.getPosterPath()).into(holder.imgPoster);

        holder.tvMovieTitle.setText(movieTitle);
        holder.tvMovieOverview.setText(movieOverview);
        holder.tvDateRelease.setText(newDate);
        holder.cvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailMovieIntent = new Intent(context, MovieDetailActivity.class);
                detailMovieIntent.putExtra(MovieDetailActivity.MOVIE_DATA, movieUpcoming.get(position));
                context.startActivity(detailMovieIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieUpcoming.size();
    }

    public static class MovieUpcoming extends RecyclerView.ViewHolder {
        ConstraintLayout movieUpcomingLayout;
        ImageView imgPoster;
        TextView tvMovieTitle, tvMovieOverview, tvDateRelease;
        CardView cvContainer;

        public MovieUpcoming(View view) {
            super(view);
            movieUpcomingLayout = view.findViewById(R.id.movie_now_playing_layout);
            imgPoster = view.findViewById(R.id.img_poster);
            tvDateRelease = view.findViewById(R.id.tv_date_release);
            tvMovieOverview = view.findViewById(R.id.tv_overview);
            tvMovieTitle = view.findViewById(R.id.tv_movie_title);
            cvContainer = view.findViewById(R.id.cv_container);
        }
    }
}

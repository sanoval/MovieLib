package com.example.sanov.movielib.view.activities;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.sanov.movielib.BuildConfig;
import com.example.sanov.movielib.R;
import com.example.sanov.movielib.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String MOVIE_DATA = "move_data";
    private TextView tvRating, tvPopularity, tvReleaseDate, tvOverview;
    private ImageView ImgPoster;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        tvRating = findViewById(R.id.tv_rating);
        tvPopularity = findViewById(R.id.tv_popularity);
        tvReleaseDate = findViewById(R.id.tv_release_date);
        tvOverview = findViewById(R.id.tv_overview);
        ImgPoster = findViewById(R.id.img_poster);

        Movie mMovie = getIntent().getParcelableExtra(MOVIE_DATA);

        setTitle(mMovie.getTitle());
        tvRating.setText(mMovie.getVoteAverage().toString());
        tvPopularity.setText(mMovie.getPopularity().toString());
        tvReleaseDate.setText(mMovie.getReleaseDate().toString());
        tvOverview.setText(mMovie.getOverview().toString());
        Picasso.with(this).load(BuildConfig.BASE_URL_POSTER_PATH_BIG + mMovie.getPosterPath()).into(ImgPoster);
    }
}

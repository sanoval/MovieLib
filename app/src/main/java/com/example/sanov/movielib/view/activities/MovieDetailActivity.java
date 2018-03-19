package com.example.sanov.movielib.view.activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanov.movielib.BuildConfig;
import com.example.sanov.movielib.R;
import com.example.sanov.movielib.db.helper.FavoriteHelper;
import com.example.sanov.movielib.helper.MyHelper;
import com.example.sanov.movielib.model.Movie;
import com.squareup.picasso.Picasso;

import static android.provider.BaseColumns._ID;
import static com.example.sanov.movielib.db.DatabaseContract.CONTENT_URI;
import static com.example.sanov.movielib.db.DatabaseContract.FavColumns.DATE;
import static com.example.sanov.movielib.db.DatabaseContract.FavColumns.IMAGE;
import static com.example.sanov.movielib.db.DatabaseContract.FavColumns.OVERVIEW;
import static com.example.sanov.movielib.db.DatabaseContract.FavColumns.POPULARITY;
import static com.example.sanov.movielib.db.DatabaseContract.FavColumns.RATING;
import static com.example.sanov.movielib.db.DatabaseContract.FavColumns.TITLE;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String MOVIE_DATA = "move_data";
    private static final String TAG = "";
    private TextView tvRating, tvPopularity, tvReleaseDate, tvOverview;
    private ImageView imgPoster, imgFavBtn;
    private boolean flags;
    private MyHelper myHelper = new MyHelper();
    private Movie movie;
    private FavoriteHelper favoriteHelper;

    View view;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        tvRating = findViewById(R.id.tv_rating);
        tvPopularity = findViewById(R.id.tv_popularity);
        tvReleaseDate = findViewById(R.id.tv_release_date);
        tvOverview = findViewById(R.id.tv_overview);
        imgPoster = findViewById(R.id.img_poster);
        imgFavBtn = findViewById(R.id.img_fav_btn);
        imgFavBtn.setOnClickListener(this);
        view = findViewById(R.id.movie_detail_container);
        favoriteHelper = new FavoriteHelper(this);
        favoriteHelper.open();

        Uri uri = getIntent().getData();

        if (uri != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) movie = new Movie(cursor);
                cursor.close();
            }
        }

        flags = false;

        if (movie != null) {
            flags = true;
            imgFavBtn.setBackground(getResources().getDrawable(R.drawable.ic_favorite_red_24dp));
        }

        Movie mMovie = getIntent().getParcelableExtra(MOVIE_DATA);
        setTitle(mMovie.getTitle());

        String newDate = myHelper.ConvertDateFormat(mMovie.getReleaseDate());

        tvRating.setText(mMovie.getVoteAverage().toString());
        tvPopularity.setText(mMovie.getPopularity().toString());
        tvOverview.setText(mMovie.getOverview());
        tvReleaseDate.setText(newDate);
        Picasso.with(this).load(BuildConfig.BASE_URL_POSTER_PATH_BIG + mMovie.getPosterPath()).into(imgPoster);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_fav_btn:
                if (!flags) {
                    imgFavBtn.setBackground(getResources().getDrawable(R.drawable.ic_favorite_red_24dp));
                    Movie mMovie = getIntent().getParcelableExtra(MOVIE_DATA);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(_ID, mMovie.getId());
                    contentValues.put(TITLE, mMovie.getTitle());
                    contentValues.put(OVERVIEW, mMovie.getOverview());
                    contentValues.put(DATE, mMovie.getReleaseDate());
                    contentValues.put(IMAGE, mMovie.getPosterPath());
                    contentValues.put(POPULARITY, mMovie.getPopularity());
                    contentValues.put(RATING, mMovie.getVoteAverage());
                    getContentResolver().insert(CONTENT_URI, contentValues);

                    showSnackbarMessage(getResources().getString(R.string.added_to_favorite));
                    flags = true;
                } else {
                    imgFavBtn.setBackground(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));

                    getContentResolver().delete(getIntent().getData(), null, null);

                    showSnackbarMessage(getResources().getString(R.string.removed_from_favorite));
                    flags = false;
                }
                break;
            default:
                break;
        }
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
}

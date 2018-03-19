package com.example.sanov.movielib.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanov.movielib.BuildConfig;
import com.example.sanov.movielib.R;
import com.example.sanov.movielib.helper.MyHelper;
import com.example.sanov.movielib.model.Movie;
import com.example.sanov.movielib.view.activities.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import static android.content.ContentValues.TAG;
import static com.example.sanov.movielib.db.DatabaseContract.CONTENT_URI;

/**
 * Created by sanov on 3/18/2018.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private Cursor listFavorites;
    private Activity activity;
    private MyHelper myHelper = new MyHelper();

    public FavoriteAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListFavorites(Cursor listFavorites) {
        this.listFavorites = listFavorites;
    }

    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_now_playing, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteAdapter.FavoriteViewHolder holder, int position) {
        final Movie movie = getItem(position);
        String newDate = myHelper.ConvertDateFormat(movie.getReleaseDate());
        holder.tvTitle.setText(myHelper.ResizeOverview(movie.getTitle(), "title"));
        holder.tvOverview.setText(myHelper.ResizeOverview(movie.getOverview(), "overview"));
        holder.tvDate.setText(newDate);
        Picasso.with(activity).load(BuildConfig.BASE_URL_POSTER_PATH_SMALL + movie.getPosterPath()).into(holder.imgPoster);
        holder.cvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailMovieIntent = new Intent(activity, MovieDetailActivity.class);
                Uri uri = Uri.parse(CONTENT_URI + "/" + movie.getId());
                detailMovieIntent.setData(uri);
                detailMovieIntent.putExtra(MovieDetailActivity.MOVIE_DATA, movie);
                activity.startActivity(detailMovieIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listFavorites == null) return 0;
        return listFavorites.getCount();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvOverview, tvDate;
        ImageView imgPoster;
        CardView cvContainer;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_movie_title);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            tvDate = itemView.findViewById(R.id.tv_date_release);
            imgPoster = itemView.findViewById(R.id.img_poster);
            cvContainer = itemView.findViewById(R.id.cv_container);
        }
    }

    private Movie getItem(int positon) {
        if (!listFavorites.moveToPosition(positon)) {
            throw new IllegalStateException("Invalid Position");
        }

        return new Movie(listFavorites);
    }
}

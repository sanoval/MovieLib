package com.example.sanov.favoritemovie.adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanov.favoritemovie.BuildConfig;
import com.example.sanov.favoritemovie.R;
import com.example.sanov.favoritemovie.helper.MyHelper;
import com.squareup.picasso.Picasso;

import static com.example.sanov.favoritemovie.db.DatabaseContract.FavColumns.DATE;
import static com.example.sanov.favoritemovie.db.DatabaseContract.FavColumns.IMAGE;
import static com.example.sanov.favoritemovie.db.DatabaseContract.FavColumns.OVERVIEW;
import static com.example.sanov.favoritemovie.db.DatabaseContract.FavColumns.TITLE;
import static com.example.sanov.favoritemovie.db.DatabaseContract.getColumnString;

/**
 * Created by sanov on 3/19/2018.
 */

public class MyFavoriteAdapter extends CursorAdapter {

    MyHelper myHelper = new MyHelper();

    public MyFavoriteAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite, viewGroup, false);
        return view;
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor != null) {
            TextView tvTitle = view.findViewById(R.id.tv_movie_title);
            TextView tvOverview = view.findViewById(R.id.tv_overview);
            TextView tvDate = view.findViewById(R.id.tv_date_release);
            ImageView imgPoster = view.findViewById(R.id.img_poster);

            String newDate = myHelper.ConvertDateFormat(getColumnString(cursor, DATE));
            Picasso.with(context).load(BuildConfig.BASE_URL_POSTER_PATH_SMALL + getColumnString(cursor, IMAGE)).into(imgPoster);
            tvTitle.setText(myHelper.ResizeOverview(getColumnString(cursor, TITLE), "title"));
            tvOverview.setText(myHelper.ResizeOverview(getColumnString(cursor, OVERVIEW), "overview"));
            tvDate.setText(newDate);
        }

    }
}

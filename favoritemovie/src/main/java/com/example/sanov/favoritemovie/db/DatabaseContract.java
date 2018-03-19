package com.example.sanov.favoritemovie.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by sanov on 3/19/2018.
 */

public class DatabaseContract {
    public static String TABLE_FAVORITE = "favorites";

    public static final class FavColumns implements BaseColumns {
        public static String TITLE = "title";
        public static String OVERVIEW = "overview";
        public static String DATE = "date_release";
        public static String IMAGE = "image";
        public static String POPULARITY = "popularity";
        public static String RATING = "rating";
    }

    public static final String AUTHORITY = "com.example.sanov.movielib";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_FAVORITE)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static double getColumnDouble(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndex(columnName));
    }
}

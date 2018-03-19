package com.example.sanov.movielib.db.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.sanov.movielib.db.DatabaseContract;
import com.example.sanov.movielib.model.Movie;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static android.provider.ContactsContract.CommonDataKinds.Organization.TITLE;
import static com.example.sanov.movielib.db.DatabaseContract.FavColumns.DATE;
import static com.example.sanov.movielib.db.DatabaseContract.FavColumns.IMAGE;
import static com.example.sanov.movielib.db.DatabaseContract.FavColumns.OVERVIEW;
import static com.example.sanov.movielib.db.DatabaseContract.FavColumns.POPULARITY;
import static com.example.sanov.movielib.db.DatabaseContract.FavColumns.RATING;
import static com.example.sanov.movielib.db.DatabaseContract.TABLE_FAVORITE;

/**
 * Created by sanov on 3/16/2018.
 */

public class FavoriteHelper {
    private static String DATABASE_TABLE = TABLE_FAVORITE;
    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase db;

    public FavoriteHelper(Context context) {
        this.context = context;
    }

    public FavoriteHelper open() throws SQLiteException {
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public ArrayList<Movie> query() {
        ArrayList<Movie> arrayList = new ArrayList<Movie>();
        Cursor cursor = db.query(DATABASE_TABLE, null, null, null, null, null, _ID + " DESC", null);
        cursor.moveToFirst();
        Movie movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new Movie();
                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                movie.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                movie.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                movie.setPopularity(cursor.getDouble(cursor.getColumnIndexOrThrow(POPULARITY)));
                movie.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(RATING)));

                arrayList.add(movie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        cursor.close();
        return arrayList;
    }

    public long insert(Movie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, movie.getTitle());
        contentValues.put(OVERVIEW, movie.getOverview());
        contentValues.put(DATE, movie.getReleaseDate());
        contentValues.put(IMAGE, movie.getPosterPath());
        contentValues.put(POPULARITY, movie.getPopularity());
        contentValues.put(RATING, movie.getVoteAverage());
        return db.insert(DATABASE_TABLE, null, contentValues);
    }

    public int update(Movie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, movie.getTitle());
        contentValues.put(OVERVIEW, movie.getOverview());
        contentValues.put(DATE, movie.getReleaseDate());
        contentValues.put(IMAGE, movie.getPosterPath());
        contentValues.put(POPULARITY, movie.getPopularity());
        contentValues.put(RATING, movie.getVoteAverage());
        return db.update(DATABASE_TABLE, contentValues, _ID + " = '" + movie.getId() + "'", null);
    }

    public int delete(int d) {
        return db.delete(TABLE_FAVORITE, _ID + " = '" + d + "'", null);
    }

    public Cursor queryByIdProvider(String id) {
        return db.query(DATABASE_TABLE, null, _ID + " = ?", new String[]{id}, null, null, null, null);
    }

    public Cursor queryProvider() {
        return db.query(DATABASE_TABLE, null, null, null, null, null, _ID + " DESC");
    }

    public long insertProvider(ContentValues values) {
        return db.insert(DATABASE_TABLE, null, values);
    }

    public int updateProvider(String id, ContentValues values) {
        return db.update(DATABASE_TABLE, values, _ID + " = ?", new String[]{id});
    }

    public int deleteProvider(String id) {
        return db.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }
}

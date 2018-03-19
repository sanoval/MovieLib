package com.example.sanov.movielib.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sanov.movielib.db.DatabaseContract;

/**
 * Created by sanov on 3/18/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbmovie";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_FAVORITE = String.format("CREATE TABLE %s"
        + " (%s INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " %s TEXT NOT NULL,"
            + " %s TEXT NOT NULL,"
            + " %s TEXT NOT NULL,"
            + " %s TEXT NOT NULL,"
            + " %s DOUBLE NOT NULL,"
            + " %s DOUBLE NOT NULL)",
            DatabaseContract.TABLE_FAVORITE,
            DatabaseContract.FavColumns._ID,
            DatabaseContract.FavColumns.TITLE,
            DatabaseContract.FavColumns.OVERVIEW,
            DatabaseContract.FavColumns.DATE,
            DatabaseContract.FavColumns.IMAGE,
            DatabaseContract.FavColumns.POPULARITY,
            DatabaseContract.FavColumns.RATING
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAVORITE);
    }
}

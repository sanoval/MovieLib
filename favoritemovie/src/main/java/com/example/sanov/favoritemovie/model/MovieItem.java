package com.example.sanov.favoritemovie.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.sanov.favoritemovie.db.DatabaseContract;

import static com.example.sanov.favoritemovie.db.DatabaseContract.getColumnDouble;
import static com.example.sanov.favoritemovie.db.DatabaseContract.getColumnInt;
import static com.example.sanov.favoritemovie.db.DatabaseContract.getColumnString;

/**
 * Created by sanov on 3/19/2018.
 */

public class MovieItem implements Parcelable {

    private int id;
    private String title;
    private String overview;
    private String date;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;
    private double rating, popularity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public static Creator<MovieItem> getCREATOR() {
        return CREATOR;
    }

    protected MovieItem(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.date = in.readString();
        this.popularity = in.readDouble();
        this.rating = in.readDouble();
        this.image = in.readString();
    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel in) {
            return new MovieItem(in);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.overview);
        parcel.writeString(this.date);
        parcel.writeDouble(this.popularity);
        parcel.writeDouble(this.rating);
        parcel.writeString(this.image);
    }

    public MovieItem() {

    }

    public MovieItem(Cursor cursor) {
        this.id = getColumnInt(cursor, DatabaseContract.FavColumns._ID);
        this.title = getColumnString(cursor, DatabaseContract.FavColumns.TITLE);
        this.overview = getColumnString(cursor, DatabaseContract.FavColumns.OVERVIEW);
        this.date = getColumnString(cursor, DatabaseContract.FavColumns.DATE);
        this.popularity = getColumnDouble(cursor, DatabaseContract.FavColumns.POPULARITY);
        this.rating = getColumnDouble(cursor, DatabaseContract.FavColumns.RATING);
        this.image = getColumnString(cursor, DatabaseContract.FavColumns.IMAGE);
    }
}

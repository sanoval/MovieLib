package com.example.sanov.movielib.helper;

/**
 * Created by sanov on 3/5/2018.
 */

public class MyHelper {

    public String ResizeOverview(String chars, String field) {
        if (chars.length() > 80 && field == "overview") {
            chars = chars.substring(0, 80) + "...";
        } else if (chars.length() > 30 && field == "title") {
            chars = chars.substring(0, 30) + "...";
        }
        return chars;
    }
}

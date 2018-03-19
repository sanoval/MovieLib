package com.example.sanov.favoritemovie.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sanov on 3/19/2018.
 */

public class MyHelper {

    public String ResizeOverview(String chars, String field) {
        if (chars.length() > 60 && field == "overview") {
            chars = chars.substring(0, 60) + "...";
        } else if (chars.length() > 25 && field == "title") {
            chars = chars.substring(0, 25) + "...";
        }
        return chars;
    }

    public String ConvertDateFormat(String oldDate) {
        try {
            Date date = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(oldDate);
            DateFormat formatter = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            String today = formatter.format(date);
            return today;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return oldDate;
    }
}

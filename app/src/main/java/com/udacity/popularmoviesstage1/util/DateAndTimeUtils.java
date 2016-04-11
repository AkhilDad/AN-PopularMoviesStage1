package com.udacity.popularmoviesstage1.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by akhil on 21/03/16.
 */
public class DateAndTimeUtils {

    private DateAndTimeUtils(){

    }

    public static String getDateString(Date date) {
        if (date == null || date.toString().trim().length() == 0) {
            return "";
        }
        String format = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return  simpleDateFormat.format(date);
    }
}

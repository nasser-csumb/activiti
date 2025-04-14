package com.webcraftsolutions.project02;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateWithoutTimeConverter {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getDateWithoutTime(Date dateWithTime) {
        try {
            return dateFormat.parse(getDateAsString(dateWithTime));
        } catch (Exception e) {
            return dateWithTime;
        }
    }

    public static String getDateAsString(Date date) {
        return dateFormat.format(date);
    }
}

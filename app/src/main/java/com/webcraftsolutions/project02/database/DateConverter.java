package com.webcraftsolutions.project02.database;

import androidx.room.TypeConverter;

import java.util.Date;

// From GymLog videos where Dr. C uses ZonedDateTime
public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        if (timestamp == null) {
            return null;
        } else {
            return new Date(timestamp);
        }
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }
}

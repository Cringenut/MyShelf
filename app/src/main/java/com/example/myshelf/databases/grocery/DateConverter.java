package com.example.myshelf.databases.grocery;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @TypeConverter
    public static Date fromString(String value) {
        try {
            return value == null ? null : formatter.parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    @TypeConverter
    public static String dateToString(Date date) {
        return date == null ? null : formatter.format(date);
    }

}

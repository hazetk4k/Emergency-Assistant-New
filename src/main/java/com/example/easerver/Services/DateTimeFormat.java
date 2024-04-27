package com.example.easerver.Services;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormat {

    public static String formatTimestamp(Timestamp timestamp) {
        try {
            DateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Date date = new Date(timestamp.getTime());
            return outputFormat.format(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

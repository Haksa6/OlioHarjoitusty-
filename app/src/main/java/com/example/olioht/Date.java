package com.example.olioht;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Date {
    String now;

    public String getNow(){
        java.util.Date currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd.M.yyyy HH:mm");
        TimeZone gmtTime = TimeZone.getTimeZone("GMT+3");
        dateFormat.setTimeZone(gmtTime);
        now = dateFormat.format(currentTime);

        return now;
    }
}

package com.app.epolice.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {

    public static Date getDateTime() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String date = formatter.format(cal.getTime());
        Date currentTime = formatter.parse(date);
        return currentTime;
    }

    public static Date getExpireTime() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 1);
        String date = formatter.format(cal.getTime());
        Date expireTime = formatter.parse(date);
        return expireTime;
    }
}

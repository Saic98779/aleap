package com.metaverse.workflow.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static String dateToString(Date date, String format) {
        if(date == null) return null;
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static Date stringToDate(String dateStr, String format) {
        if(dateStr == null) return null;
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static Date stringToDates(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        String[] dateFormats = {"yyyy-MM-dd", "dd-MM-yyyy", "dd-MMM-yyyy", "d-MMM-yyyy", "d-MM-yyyy"};

        for (String format : dateFormats) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                sdf.setLenient(false);
                Date parsedDate = sdf.parse(input);
                return java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(parsedDate));
            } catch (ParseException e) {
                // Try next format
            }
        }

        return null;
    }

    public static LocalDate stringToDate(String date)
    {
        if (date == null) return null;
        try{
            return LocalDate.parse(date);
        }
        catch(Exception e)
        {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
    }

}

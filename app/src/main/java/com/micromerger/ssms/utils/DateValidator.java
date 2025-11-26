package com.micromerger.ssms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang.time.DateUtils;

/* loaded from: classes2.dex */
public class DateValidator {
    private static DateValidator instance;

    public String formatDate(Date date) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String formatDate(String oldFormat, String newFormat, String date) throws ParseException {
        if (oldFormat == null) {
            oldFormat = "dd-MM-yyyy";
        }
        try {
            return new SimpleDateFormat(newFormat, Locale.getDefault()).format(new SimpleDateFormat(oldFormat, Locale.ENGLISH).parse(date));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getDayFromDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(date));
            return calendar.get(5);
        } catch (Exception unused) {
            return 0;
        }
    }

    public long getTransactionTime(String dateTime) {
        try {
            return new SimpleDateFormat("`yyyy-MM-dd'T'HH:mm:ss`", new Locale("en")).parse(dateTime).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public long getStatDate(String dateTime) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(dateTime).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static DateValidator getInstance() {
        if (instance == null) {
            instance = new DateValidator();
        }
        return instance;
    }

    public static String getFormattedDate(String stringDate) {
        Date time = Calendar.getInstance().getTime();
        try {
            time = new SimpleDateFormat(Constant.SERVER_DATE_FORMAT).parse(stringDate);
            System.out.println(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int i = calendar.get(5);
        if (i <= 10 || i >= 19) {
            int i2 = i % 10;
            if (i2 == 1) {
                return new SimpleDateFormat("d'st' 'of' MMMM yyyy", Locale.getDefault()).format(time);
            }
            if (i2 == 2) {
                return new SimpleDateFormat("d'nd' 'of' MMMM yyyy", Locale.getDefault()).format(time);
            }
            if (i2 == 3) {
                return new SimpleDateFormat("d'rd' 'of' MMMM yyyy", Locale.getDefault()).format(time);
            }
            return new SimpleDateFormat("d'th' 'of' MMMM yyyy", Locale.getDefault()).format(time);
        }
        return new SimpleDateFormat("d'th' 'of' MMMM yyyy", Locale.getDefault()).format(time);
    }

    public static int getNumberOfDays(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.SERVER_DATE_FORMAT, Locale.getDefault());
        if (dateString != null && dateString.length() != 0) {
            try {
                Date date = simpleDateFormat.parse(dateString);
                if (date == null) {
                    return 0;
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                long timeInMillis = calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
                if (timeInMillis <= 0) {
                    return 0;
                }
                return (int) (timeInMillis / DateUtils.MILLIS_PER_DAY);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public String formatRewardsPointsDate(String date) {
        Date date2;
        try {
            date2 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            date2 = null;
        }
        return new SimpleDateFormat("dd MMM yyyy").format(date2);
    }
}

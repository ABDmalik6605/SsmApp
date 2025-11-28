package org.apache.commons.lang.time;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;

/* loaded from: classes3.dex */
public class DateUtils {
    public static final int MILLIS_IN_DAY = 86400000;
    public static final int MILLIS_IN_HOUR = 3600000;
    public static final int MILLIS_IN_MINUTE = 60000;
    public static final int MILLIS_IN_SECOND = 1000;
    public static final long MILLIS_PER_DAY = 86400000;
    public static final long MILLIS_PER_HOUR = 3600000;
    public static final long MILLIS_PER_MINUTE = 60000;
    public static final long MILLIS_PER_SECOND = 1000;
    private static final int MODIFY_CEILING = 2;
    private static final int MODIFY_ROUND = 1;
    private static final int MODIFY_TRUNCATE = 0;
    public static final int RANGE_MONTH_MONDAY = 6;
    public static final int RANGE_MONTH_SUNDAY = 5;
    public static final int RANGE_WEEK_CENTER = 4;
    public static final int RANGE_WEEK_MONDAY = 2;
    public static final int RANGE_WEEK_RELATIVE = 3;
    public static final int RANGE_WEEK_SUNDAY = 1;
    public static final int SEMI_MONTH = 1001;
    public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("GMT");
    private static final int[][] fields = {new int[]{14}, new int[]{13}, new int[]{12}, new int[]{11, 10}, new int[]{5, 5, 9}, new int[]{2, 1001}, new int[]{1}, new int[]{0}};

    public static boolean isSameDay(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return isSameDay(calendar, calendar2);
    }

    public static boolean isSameDay(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6);
    }

    public static boolean isSameInstant(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return date.getTime() == date2.getTime();
    }

    public static boolean isSameInstant(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.getTime().getTime() == calendar2.getTime().getTime();
    }

    public static boolean isSameLocalTime(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.get(14) == calendar2.get(14) && calendar.get(13) == calendar2.get(13) && calendar.get(12) == calendar2.get(12) && calendar.get(10) == calendar2.get(10) && calendar.get(6) == calendar2.get(6) && calendar.get(1) == calendar2.get(1) && calendar.get(0) == calendar2.get(0) && calendar.getClass() == calendar2.getClass();
    }

    public static Date parseDate(String str, String[] strArr) throws ParseException {
        return parseDateWithLeniency(str, strArr, true);
    }

    public static Date parseDateStrictly(String str, String[] strArr) throws ParseException {
        return parseDateWithLeniency(str, strArr, false);
    }

    private static Date parseDateWithLeniency(String str, String[] strArr, boolean z) throws ParseException {
        String strReformatTimezone;
        if (str == null || strArr == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.setLenient(z);
        ParsePosition parsePosition = new ParsePosition(0);
        for (int i = 0; i < strArr.length; i++) {
            String strSubstring = strArr[i];
            if (strArr[i].endsWith("ZZ")) {
                strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
            }
            simpleDateFormat.applyPattern(strSubstring);
            parsePosition.setIndex(0);
            if (strArr[i].endsWith("ZZ")) {
                int iIndexOfSignChars = indexOfSignChars(str, 0);
                strReformatTimezone = str;
                while (iIndexOfSignChars >= 0) {
                    strReformatTimezone = reformatTimezone(strReformatTimezone, iIndexOfSignChars);
                    iIndexOfSignChars = indexOfSignChars(strReformatTimezone, iIndexOfSignChars + 1);
                }
            } else {
                strReformatTimezone = str;
            }
            Date date = simpleDateFormat.parse(strReformatTimezone, parsePosition);
            if (date != null && parsePosition.getIndex() == strReformatTimezone.length()) {
                return date;
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unable to parse the date: ");
        stringBuffer.append(str);
        throw new ParseException(stringBuffer.toString(), -1);
    }

    private static int indexOfSignChars(String str, int i) {
        int iIndexOf = StringUtils.indexOf(str, '+', i);
        return iIndexOf < 0 ? StringUtils.indexOf(str, '-', i) : iIndexOf;
    }

    private static String reformatTimezone(String str, int i) {
        int i2;
        if (i < 0 || (i2 = i + 5) >= str.length() || !Character.isDigit(str.charAt(i + 1)) || !Character.isDigit(str.charAt(i + 2))) {
            return str;
        }
        int i3 = i + 3;
        if (str.charAt(i3) != ':') {
            return str;
        }
        int i4 = i + 4;
        if (!Character.isDigit(str.charAt(i4)) || !Character.isDigit(str.charAt(i2))) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str.substring(0, i3));
        stringBuffer.append(str.substring(i4));
        return stringBuffer.toString();
    }

    public static Date addYears(Date date, int i) {
        return add(date, 1, i);
    }

    public static Date addMonths(Date date, int i) {
        return add(date, 2, i);
    }

    public static Date addWeeks(Date date, int i) {
        return add(date, 3, i);
    }

    public static Date addDays(Date date, int i) {
        return add(date, 5, i);
    }

    public static Date addHours(Date date, int i) {
        return add(date, 11, i);
    }

    public static Date addMinutes(Date date, int i) {
        return add(date, 12, i);
    }

    public static Date addSeconds(Date date, int i) {
        return add(date, 13, i);
    }

    public static Date addMilliseconds(Date date, int i) {
        return add(date, 14, i);
    }

    public static Date add(Date date, int i, int i2) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(i, i2);
        return calendar.getTime();
    }

    public static Date setYears(Date date, int i) {
        return set(date, 1, i);
    }

    public static Date setMonths(Date date, int i) {
        return set(date, 2, i);
    }

    public static Date setDays(Date date, int i) {
        return set(date, 5, i);
    }

    public static Date setHours(Date date, int i) {
        return set(date, 11, i);
    }

    public static Date setMinutes(Date date, int i) {
        return set(date, 12, i);
    }

    public static Date setSeconds(Date date, int i) {
        return set(date, 13, i);
    }

    public static Date setMilliseconds(Date date, int i) {
        return set(date, 14, i);
    }

    private static Date set(Date date, int i, int i2) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.setTime(date);
        calendar.set(i, i2);
        return calendar.getTime();
    }

    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date round(Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, i, 1);
        return calendar.getTime();
    }

    public static Calendar round(Calendar calendar, int i) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        modify(calendar2, i, 1);
        return calendar2;
    }

    public static Date round(Object obj, int i) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (obj instanceof Date) {
            return round((Date) obj, i);
        }
        if (obj instanceof Calendar) {
            return round((Calendar) obj, i).getTime();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Could not round ");
        stringBuffer.append(obj);
        throw new ClassCastException(stringBuffer.toString());
    }

    public static Date truncate(Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, i, 0);
        return calendar.getTime();
    }

    public static Calendar truncate(Calendar calendar, int i) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        modify(calendar2, i, 0);
        return calendar2;
    }

    public static Date truncate(Object obj, int i) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (obj instanceof Date) {
            return truncate((Date) obj, i);
        }
        if (obj instanceof Calendar) {
            return truncate((Calendar) obj, i).getTime();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Could not truncate ");
        stringBuffer.append(obj);
        throw new ClassCastException(stringBuffer.toString());
    }

    public static Date ceiling(Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, i, 2);
        return calendar.getTime();
    }

    public static Calendar ceiling(Calendar calendar, int i) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        modify(calendar2, i, 2);
        return calendar2;
    }

    public static Date ceiling(Object obj, int i) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (obj instanceof Date) {
            return ceiling((Date) obj, i);
        }
        if (obj instanceof Calendar) {
            return ceiling((Calendar) obj, i).getTime();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Could not find ceiling of for type: ");
        stringBuffer.append(obj.getClass());
        throw new ClassCastException(stringBuffer.toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x00c1, code lost:
    
        if (r18 == 9) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00c3, code lost:
    
        if (r18 == 1001) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00cb, code lost:
    
        if (r9[r5][0] != 5) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00cd, code lost:
    
        r6 = r17.get(5) - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00d2, code lost:
    
        if (r6 < 15) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00d4, code lost:
    
        r6 = r6 - 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00d7, code lost:
    
        if (r6 <= 7) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00df, code lost:
    
        if (r9[r5][0] != 11) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00e1, code lost:
    
        r6 = r17.get(11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00e5, code lost:
    
        if (r6 < 12) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00e7, code lost:
    
        r6 = r6 - 12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00ea, code lost:
    
        if (r6 < 6) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00ec, code lost:
    
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00ee, code lost:
    
        r7 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00ef, code lost:
    
        r8 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00f1, code lost:
    
        r7 = r6;
        r6 = 0;
        r8 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00f4, code lost:
    
        if (r8 != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00f6, code lost:
    
        r6 = r17.getActualMinimum(r9[r5][0]);
        r8 = r17.getActualMaximum(r9[r5][0]);
        r7 = r17.get(r9[r5][0]) - r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0112, code lost:
    
        if (r7 <= ((r8 - r6) / 2)) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0114, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0116, code lost:
    
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0117, code lost:
    
        r7 = r6;
        r6 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x011c, code lost:
    
        if (r6 == 0) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x011e, code lost:
    
        r17.set(r9[r5][0], r17.get(r9[r5][0]) - r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0131, code lost:
    
        r5 = r5 + 1;
        r6 = r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void modify(java.util.Calendar r17, int r18, int r19) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.time.DateUtils.modify(java.util.Calendar, int, int):void");
    }

    public static Iterator iterator(Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return iterator(calendar, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007d A[LOOP:0: B:29:0x0077->B:31:0x007d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0087 A[LOOP:1: B:32:0x0081->B:34:0x0087, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Iterator iterator(java.util.Calendar r8, int r9) {
        /*
            if (r8 == 0) goto L91
            r0 = -1
            r1 = 2
            r2 = 5
            r3 = 1
            r4 = 7
            switch(r9) {
                case 1: goto L42;
                case 2: goto L42;
                case 3: goto L42;
                case 4: goto L42;
                case 5: goto L26;
                case 6: goto L26;
                default: goto La;
            }
        La:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "The range style "
            r0.append(r1)
            r0.append(r9)
            java.lang.String r9 = " is not valid."
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            r8.<init>(r9)
            throw r8
        L26:
            java.util.Calendar r8 = truncate(r8, r1)
            java.lang.Object r5 = r8.clone()
            java.util.Calendar r5 = (java.util.Calendar) r5
            r5.add(r1, r3)
            r5.add(r2, r0)
            r6 = 6
            if (r9 != r6) goto L3d
            r6 = r5
            r5 = r8
        L3b:
            r8 = 1
            goto L67
        L3d:
            r6 = r5
            r1 = 1
            r5 = r8
            r8 = 7
            goto L67
        L42:
            java.util.Calendar r5 = truncate(r8, r2)
            java.util.Calendar r6 = truncate(r8, r2)
            if (r9 == r1) goto L3b
            r1 = 3
            if (r9 == r1) goto L61
            r7 = 4
            if (r9 == r7) goto L55
            r8 = 7
            r1 = 1
            goto L67
        L55:
            int r9 = r8.get(r4)
            int r9 = r9 - r1
            int r8 = r8.get(r4)
            int r8 = r8 + r1
            r1 = r9
            goto L67
        L61:
            int r1 = r8.get(r4)
            int r8 = r1 + (-1)
        L67:
            if (r1 >= r3) goto L6b
            int r1 = r1 + 7
        L6b:
            if (r1 <= r4) goto L6f
            int r1 = r1 + (-7)
        L6f:
            if (r8 >= r3) goto L73
            int r8 = r8 + 7
        L73:
            if (r8 <= r4) goto L77
            int r8 = r8 + (-7)
        L77:
            int r9 = r5.get(r4)
            if (r9 == r1) goto L81
            r5.add(r2, r0)
            goto L77
        L81:
            int r9 = r6.get(r4)
            if (r9 == r8) goto L8b
            r6.add(r2, r3)
            goto L81
        L8b:
            org.apache.commons.lang.time.DateUtils$DateIterator r8 = new org.apache.commons.lang.time.DateUtils$DateIterator
            r8.<init>(r5, r6)
            return r8
        L91:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "The date must not be null"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.time.DateUtils.iterator(java.util.Calendar, int):java.util.Iterator");
    }

    public static Iterator iterator(Object obj, int i) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (obj instanceof Date) {
            return iterator((Date) obj, i);
        }
        if (obj instanceof Calendar) {
            return iterator((Calendar) obj, i);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Could not iterate based on ");
        stringBuffer.append(obj);
        throw new ClassCastException(stringBuffer.toString());
    }

    public static long getFragmentInMilliseconds(Date date, int i) {
        return getFragment(date, i, 14);
    }

    public static long getFragmentInSeconds(Date date, int i) {
        return getFragment(date, i, 13);
    }

    public static long getFragmentInMinutes(Date date, int i) {
        return getFragment(date, i, 12);
    }

    public static long getFragmentInHours(Date date, int i) {
        return getFragment(date, i, 11);
    }

    public static long getFragmentInDays(Date date, int i) {
        return getFragment(date, i, 6);
    }

    public static long getFragmentInMilliseconds(Calendar calendar, int i) {
        return getFragment(calendar, i, 14);
    }

    public static long getFragmentInSeconds(Calendar calendar, int i) {
        return getFragment(calendar, i, 13);
    }

    public static long getFragmentInMinutes(Calendar calendar, int i) {
        return getFragment(calendar, i, 12);
    }

    public static long getFragmentInHours(Calendar calendar, int i) {
        return getFragment(calendar, i, 11);
    }

    public static long getFragmentInDays(Calendar calendar, int i) {
        return getFragment(calendar, i, 6);
    }

    private static long getFragment(Date date, int i, int i2) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFragment(calendar, i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static long getFragment(java.util.Calendar r11, int r12, int r13) {
        /*
            if (r11 == 0) goto L82
            long r0 = getMillisPerUnit(r13)
            r2 = 0
            r4 = 86400000(0x5265c00, double:4.2687272E-316)
            r13 = 6
            r6 = 5
            r7 = 2
            r8 = 1
            if (r12 == r8) goto L1d
            if (r12 == r7) goto L14
            goto L26
        L14:
            int r9 = r11.get(r6)
            long r9 = (long) r9
            long r9 = r9 * r4
            long r9 = r9 / r0
            goto L25
        L1d:
            int r9 = r11.get(r13)
            long r9 = (long) r9
            long r9 = r9 * r4
            long r9 = r9 / r0
        L25:
            long r2 = r2 + r9
        L26:
            if (r12 == r8) goto L4d
            if (r12 == r7) goto L4d
            if (r12 == r6) goto L4d
            if (r12 == r13) goto L4d
            switch(r12) {
                case 11: goto L5b;
                case 12: goto L69;
                case 13: goto L76;
                case 14: goto L81;
                default: goto L31;
            }
        L31:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.StringBuffer r13 = new java.lang.StringBuffer
            r13.<init>()
            java.lang.String r0 = "The fragment "
            r13.append(r0)
            r13.append(r12)
            java.lang.String r12 = " is not supported"
            r13.append(r12)
            java.lang.String r12 = r13.toString()
            r11.<init>(r12)
            throw r11
        L4d:
            r12 = 11
            int r12 = r11.get(r12)
            long r12 = (long) r12
            r4 = 3600000(0x36ee80, double:1.7786363E-317)
            long r12 = r12 * r4
            long r12 = r12 / r0
            long r2 = r2 + r12
        L5b:
            r12 = 12
            int r12 = r11.get(r12)
            long r12 = (long) r12
            r4 = 60000(0xea60, double:2.9644E-319)
            long r12 = r12 * r4
            long r12 = r12 / r0
            long r2 = r2 + r12
        L69:
            r12 = 13
            int r12 = r11.get(r12)
            long r12 = (long) r12
            r4 = 1000(0x3e8, double:4.94E-321)
            long r12 = r12 * r4
            long r12 = r12 / r0
            long r2 = r2 + r12
        L76:
            r12 = 14
            int r11 = r11.get(r12)
            int r11 = r11 * 1
            long r11 = (long) r11
            long r11 = r11 / r0
            long r2 = r2 + r11
        L81:
            return r2
        L82:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.String r12 = "The date must not be null"
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.time.DateUtils.getFragment(java.util.Calendar, int, int):long");
    }

    public static boolean truncatedEquals(Calendar calendar, Calendar calendar2, int i) {
        return truncatedCompareTo(calendar, calendar2, i) == 0;
    }

    public static boolean truncatedEquals(Date date, Date date2, int i) {
        return truncatedCompareTo(date, date2, i) == 0;
    }

    public static int truncatedCompareTo(Calendar calendar, Calendar calendar2, int i) {
        return truncate(calendar, i).getTime().compareTo(truncate(calendar2, i).getTime());
    }

    public static int truncatedCompareTo(Date date, Date date2, int i) {
        return truncate(date, i).compareTo(truncate(date2, i));
    }

    private static long getMillisPerUnit(int i) {
        if (i == 5 || i == 6) {
            return MILLIS_PER_DAY;
        }
        switch (i) {
            case 11:
                return MILLIS_PER_HOUR;
            case 12:
                return MILLIS_PER_MINUTE;
            case 13:
                return 1000L;
            case 14:
                return 1L;
            default:
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("The unit ");
                stringBuffer.append(i);
                stringBuffer.append(" cannot be represented is milleseconds");
                throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    static class DateIterator implements Iterator {
        private final Calendar endFinal;
        private final Calendar spot;

        DateIterator(Calendar calendar, Calendar calendar2) {
            this.endFinal = calendar2;
            this.spot = calendar;
            calendar.add(5, -1);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.spot.before(this.endFinal);
        }

        @Override // java.util.Iterator
        public Object next() {
            if (this.spot.equals(this.endFinal)) {
                throw new NoSuchElementException();
            }
            this.spot.add(5, 1);
            return this.spot.clone();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
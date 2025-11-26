package com.micromerger.ssms.utils;

import java.util.Collection;

/* loaded from: classes2.dex */
public class Strings {
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static <T> String join(Collection<T> coll, String separator) {
        return join(coll, separator, (String) null);
    }

    public static String join(Object[] arr, String separator) {
        return join(arr, separator, (String) null);
    }

    public static <T> String join(Collection<T> coll, String separator, String terminator) {
        return join(coll.toArray(new Object[coll.size()]), separator, terminator);
    }

    public static String join(Object[] arr, String separator, String terminator) {
        StringBuilder sb = new StringBuilder(arr.length * 2);
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(separator);
            } else if (terminator != null && arr.length > 0) {
                sb.append(terminator);
            }
        }
        return sb.toString();
    }
}

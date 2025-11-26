package org.apache.commons.lang;

import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes3.dex */
public final class NumberUtils {
    public static int maximum(int i, int i2, int i3) {
        if (i2 > i) {
            i = i2;
        }
        return i3 > i ? i3 : i;
    }

    public static long maximum(long j, long j2, long j3) {
        if (j2 > j) {
            j = j2;
        }
        return j3 > j ? j3 : j;
    }

    public static int minimum(int i, int i2, int i3) {
        if (i2 < i) {
            i = i2;
        }
        return i3 < i ? i3 : i;
    }

    public static long minimum(long j, long j2, long j3) {
        if (j2 < j) {
            j = j2;
        }
        return j3 < j ? j3 : j;
    }

    public static int stringToInt(String str) {
        return stringToInt(str, 0);
    }

    public static int stringToInt(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x00ed, code lost:
    
        if (r1 == 'l') goto L59;
     */
    /* JADX WARN: Removed duplicated region for block: B:133:0x013f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0134 A[Catch: NumberFormatException -> 0x013f, TRY_LEAVE, TryCatch #3 {NumberFormatException -> 0x013f, blocks: (B:73:0x012a, B:75:0x0134), top: B:137:0x012a }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0149 A[Catch: NumberFormatException -> 0x0155, TRY_LEAVE, TryCatch #0 {NumberFormatException -> 0x0155, blocks: (B:80:0x013f, B:82:0x0149), top: B:133:0x013f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Number createNumber(java.lang.String r13) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 477
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.NumberUtils.createNumber(java.lang.String):java.lang.Number");
    }

    private static boolean isAllZeros(String str) {
        if (str == null) {
            return true;
        }
        for (int length = str.length() - 1; length >= 0; length--) {
            if (str.charAt(length) != '0') {
                return false;
            }
        }
        return str.length() > 0;
    }

    public static Float createFloat(String str) {
        return Float.valueOf(str);
    }

    public static Double createDouble(String str) {
        return Double.valueOf(str);
    }

    public static Integer createInteger(String str) {
        return Integer.decode(str);
    }

    public static Long createLong(String str) {
        return Long.valueOf(str);
    }

    public static BigInteger createBigInteger(String str) {
        return new BigInteger(str);
    }

    public static BigDecimal createBigDecimal(String str) {
        return new BigDecimal(str);
    }

    public static int compare(double d, double d2) {
        if (d < d2) {
            return -1;
        }
        if (d > d2) {
            return 1;
        }
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        long jDoubleToLongBits2 = Double.doubleToLongBits(d2);
        if (jDoubleToLongBits == jDoubleToLongBits2) {
            return 0;
        }
        return jDoubleToLongBits < jDoubleToLongBits2 ? -1 : 1;
    }

    public static int compare(float f, float f2) {
        if (f < f2) {
            return -1;
        }
        if (f > f2) {
            return 1;
        }
        int iFloatToIntBits = Float.floatToIntBits(f);
        int iFloatToIntBits2 = Float.floatToIntBits(f2);
        if (iFloatToIntBits == iFloatToIntBits2) {
            return 0;
        }
        return iFloatToIntBits < iFloatToIntBits2 ? -1 : 1;
    }

    public static boolean isDigits(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:133:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x006d, code lost:
    
        if (r3 >= r0.length) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0071, code lost:
    
        if (r0[r3] < '0') goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0075, code lost:
    
        if (r0[r3] > '9') goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0077, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x007a, code lost:
    
        if (r0[r3] == 'e') goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x007e, code lost:
    
        if (r0[r3] != 'E') goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0081, code lost:
    
        if (r6 != false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0087, code lost:
    
        if (r0[r3] == 'd') goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x008d, code lost:
    
        if (r0[r3] == 'D') goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0091, code lost:
    
        if (r0[r3] == 'f') goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0095, code lost:
    
        if (r0[r3] != 'F') goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0097, code lost:
    
        return r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x009c, code lost:
    
        if (r0[r3] == 'l') goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00a2, code lost:
    
        if (r0[r3] != 'L') goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00a5, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00a6, code lost:
    
        if (r11 == false) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00a8, code lost:
    
        if (r12 != false) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00aa, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00ac, code lost:
    
        if (r6 != false) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00ae, code lost:
    
        if (r11 == false) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00b0, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x00ce, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isNumber(java.lang.String r16) {
        /*
            Method dump skipped, instructions count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.NumberUtils.isNumber(java.lang.String):boolean");
    }
}

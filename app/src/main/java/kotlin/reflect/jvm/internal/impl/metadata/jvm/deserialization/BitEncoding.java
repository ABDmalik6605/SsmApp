package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlinx.coroutines.scheduling.WorkQueueKt;

/* loaded from: classes2.dex */
public class BitEncoding {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean FORCE_8TO7_ENCODING;

    static {
        String property;
        try {
            property = System.getProperty("kotlin.jvm.serialization.use8to7");
        } catch (SecurityException unused) {
            property = null;
        }
        FORCE_8TO7_ENCODING = "true".equals(property);
    }

    private BitEncoding() {
    }

    private static void addModuloByte(byte[] bArr, int i) {
        int length = bArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i2] = (byte) ((bArr[i2] + i) & WorkQueueKt.MASK);
        }
    }

    public static byte[] decodeBytes(String[] strArr) {
        if (strArr.length > 0 && !strArr[0].isEmpty()) {
            char cCharAt = strArr[0].charAt(0);
            if (cCharAt == 0) {
                return UtfEncodingKt.stringsToBytes(dropMarker(strArr));
            }
            if (cCharAt == 65535) {
                strArr = dropMarker(strArr);
            }
        }
        byte[] bArrCombineStringArrayIntoBytes = combineStringArrayIntoBytes(strArr);
        addModuloByte(bArrCombineStringArrayIntoBytes, WorkQueueKt.MASK);
        return decode7to8(bArrCombineStringArrayIntoBytes);
    }

    private static String[] dropMarker(String[] strArr) {
        String[] strArr2 = (String[]) strArr.clone();
        strArr2[0] = strArr2[0].substring(1);
        return strArr2;
    }

    private static byte[] combineStringArrayIntoBytes(String[] strArr) {
        int length = 0;
        for (String str : strArr) {
            length += str.length();
        }
        byte[] bArr = new byte[length];
        int i = 0;
        for (String str2 : strArr) {
            int length2 = str2.length();
            int i2 = 0;
            while (i2 < length2) {
                bArr[i] = (byte) str2.charAt(i2);
                i2++;
                i++;
            }
        }
        return bArr;
    }

    private static byte[] decode7to8(byte[] bArr) {
        int length = (bArr.length * 7) / 8;
        byte[] bArr2 = new byte[length];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = (bArr[i] & 255) >>> i2;
            i++;
            int i5 = i2 + 1;
            bArr2[i3] = (byte) (i4 + ((bArr[i] & ((1 << i5) - 1)) << (7 - i2)));
            if (i2 == 6) {
                i++;
                i2 = 0;
            } else {
                i2 = i5;
            }
        }
        return bArr2;
    }
}

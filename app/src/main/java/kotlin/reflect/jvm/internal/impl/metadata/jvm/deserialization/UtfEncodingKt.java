package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: utfEncoding.kt */
/* loaded from: classes2.dex */
public final class UtfEncodingKt {
    public static final byte[] stringsToBytes(String[] strings) {
        int i;
        Intrinsics.checkParameterIsNotNull(strings, "strings");
        int length = 0;
        for (String str : strings) {
            length += str.length();
        }
        byte[] bArr = new byte[length];
        int i2 = 0;
        for (String str2 : strings) {
            int length2 = str2.length() - 1;
            if (length2 >= 0) {
                int i3 = 0;
                while (true) {
                    i = i2 + 1;
                    bArr[i2] = (byte) str2.charAt(i3);
                    if (i3 == length2) {
                        break;
                    }
                    i3++;
                    i2 = i;
                }
                i2 = i;
            }
        }
        return bArr;
    }
}

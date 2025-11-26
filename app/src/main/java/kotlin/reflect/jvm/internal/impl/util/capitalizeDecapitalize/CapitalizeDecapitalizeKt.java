package kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: capitalizeDecapitalize.kt */
/* loaded from: classes2.dex */
public final class CapitalizeDecapitalizeKt {
    /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt$decapitalizeSmart$1] */
    public static final String decapitalizeSmart(final String receiver, final boolean z) {
        Integer next;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        ?? r0 = new Function1<Integer, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt.decapitalizeSmart.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Integer num) {
                return Boolean.valueOf(invoke(num.intValue()));
            }

            public final boolean invoke(int i) {
                char cCharAt = receiver.charAt(i);
                return z ? 'A' <= cCharAt && 'Z' >= cCharAt : Character.isUpperCase(cCharAt);
            }
        };
        String str = receiver;
        if ((str.length() == 0) || !r0.invoke(0)) {
            return receiver;
        }
        if (receiver.length() == 1 || !r0.invoke(1)) {
            return z ? decapitalizeAsciiOnly(receiver) : StringsKt.decapitalize(receiver);
        }
        Function1<String, String> function1 = new Function1<String, String>() { // from class: kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt.decapitalizeSmart.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final String invoke(String string) {
                Intrinsics.checkParameterIsNotNull(string, "string");
                if (z) {
                    return CapitalizeDecapitalizeKt.toLowerCaseAsciiOnly(string);
                }
                String lowerCase = string.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                return lowerCase;
            }
        };
        Iterator<Integer> it = StringsKt.getIndices(str).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (!r0.invoke(next.intValue())) {
                break;
            }
        }
        Integer num = next;
        if (num == null) {
            return function1.invoke(receiver);
        }
        int iIntValue = num.intValue() - 1;
        StringBuilder sb = new StringBuilder();
        String strSubstring = receiver.substring(0, iIntValue);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        sb.append(function1.invoke(strSubstring));
        String strSubstring2 = receiver.substring(iIntValue);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring2, "(this as java.lang.String).substring(startIndex)");
        sb.append(strSubstring2);
        return sb.toString();
    }

    public static final String capitalizeAsciiOnly(String receiver) {
        char cCharAt;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if ((receiver.length() == 0) || 'a' > (cCharAt = receiver.charAt(0)) || 'z' < cCharAt) {
            return receiver;
        }
        char upperCase = Character.toUpperCase(cCharAt);
        String strSubstring = receiver.substring(1);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return String.valueOf(upperCase) + strSubstring;
    }

    public static final String decapitalizeAsciiOnly(String receiver) {
        char cCharAt;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if ((receiver.length() == 0) || 'A' > (cCharAt = receiver.charAt(0)) || 'Z' < cCharAt) {
            return receiver;
        }
        char lowerCase = Character.toLowerCase(cCharAt);
        String strSubstring = receiver.substring(1);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return String.valueOf(lowerCase) + strSubstring;
    }

    public static final String toLowerCaseAsciiOnly(String receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        StringBuilder sb = new StringBuilder(receiver.length());
        int length = receiver.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = receiver.charAt(i);
            if ('A' <= cCharAt && 'Z' >= cCharAt) {
                cCharAt = Character.toLowerCase(cCharAt);
            }
            sb.append(cCharAt);
        }
        String string = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "builder.toString()");
        return string;
    }
}

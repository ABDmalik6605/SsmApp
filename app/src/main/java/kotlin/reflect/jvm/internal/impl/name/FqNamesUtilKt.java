package kotlin.reflect.jvm.internal.impl.name;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: FqNamesUtil.kt */
/* loaded from: classes2.dex */
public final class FqNamesUtilKt {

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[State.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[State.BEGINNING.ordinal()] = 1;
            iArr[State.AFTER_DOT.ordinal()] = 2;
            iArr[State.MIDDLE.ordinal()] = 3;
        }
    }

    public static final boolean isSubpackageOf(FqName receiver, FqName packageName) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        if (Intrinsics.areEqual(receiver, packageName) || packageName.isRoot()) {
            return true;
        }
        String strAsString = receiver.asString();
        Intrinsics.checkExpressionValueIsNotNull(strAsString, "this.asString()");
        String strAsString2 = packageName.asString();
        Intrinsics.checkExpressionValueIsNotNull(strAsString2, "packageName.asString()");
        return isSubpackageOf(strAsString, strAsString2);
    }

    private static final boolean isSubpackageOf(String str, String str2) {
        return StringsKt.startsWith$default(str, str2, false, 2, (Object) null) && str.charAt(str2.length()) == '.';
    }

    public static final FqName tail(FqName receiver, FqName prefix) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        if (!isSubpackageOf(receiver, prefix) || prefix.isRoot()) {
            return receiver;
        }
        if (Intrinsics.areEqual(receiver, prefix)) {
            FqName fqName = FqName.ROOT;
            Intrinsics.checkExpressionValueIsNotNull(fqName, "FqName.ROOT");
            return fqName;
        }
        String strAsString = receiver.asString();
        Intrinsics.checkExpressionValueIsNotNull(strAsString, "asString()");
        int length = prefix.asString().length() + 1;
        if (strAsString == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = strAsString.substring(length);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return new FqName(strSubstring);
    }

    public static final boolean isValidJavaFqName(String str) {
        if (str == null) {
            return false;
        }
        State state = State.BEGINNING;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            int i2 = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
            if (i2 == 1 || i2 == 2) {
                if (!Character.isJavaIdentifierPart(cCharAt)) {
                    return false;
                }
                state = State.MIDDLE;
            } else if (i2 != 3) {
                continue;
            } else if (cCharAt == '.') {
                state = State.AFTER_DOT;
            } else if (!Character.isJavaIdentifierPart(cCharAt)) {
                return false;
            }
        }
        return state != State.AFTER_DOT;
    }
}

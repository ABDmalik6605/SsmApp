package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* compiled from: RenderingUtils.kt */
/* loaded from: classes2.dex */
public final class RenderingUtilsKt {
    public static final String render(Name receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!shouldBeEscaped(receiver)) {
            String strAsString = receiver.asString();
            Intrinsics.checkExpressionValueIsNotNull(strAsString, "asString()");
            return strAsString;
        }
        StringBuilder sb = new StringBuilder();
        String strAsString2 = receiver.asString();
        Intrinsics.checkExpressionValueIsNotNull(strAsString2, "asString()");
        sb.append(String.valueOf('`') + strAsString2);
        sb.append('`');
        return sb.toString();
    }

    private static final boolean shouldBeEscaped(Name name) {
        boolean z;
        if (name.isSpecial()) {
            return false;
        }
        String string = name.asString();
        if (!KeywordStringsGenerated.KEYWORDS.contains(string)) {
            Intrinsics.checkExpressionValueIsNotNull(string, "string");
            String str = string;
            int i = 0;
            while (true) {
                if (i >= str.length()) {
                    z = false;
                    break;
                }
                char cCharAt = str.charAt(i);
                if ((Character.isLetterOrDigit(cCharAt) || cCharAt == '_') ? false : true) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public static final String render(FqNameUnsafe receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        List<Name> listPathSegments = receiver.pathSegments();
        Intrinsics.checkExpressionValueIsNotNull(listPathSegments, "pathSegments()");
        return renderFqName(listPathSegments);
    }

    public static final String renderFqName(List<Name> pathSegments) {
        Intrinsics.checkParameterIsNotNull(pathSegments, "pathSegments");
        StringBuilder sb = new StringBuilder();
        for (Name name : pathSegments) {
            if (sb.length() > 0) {
                sb.append(".");
            }
            sb.append(render(name));
        }
        String string = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}

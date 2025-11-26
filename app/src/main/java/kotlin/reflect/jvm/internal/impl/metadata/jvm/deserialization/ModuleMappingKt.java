package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin.text.StringsKt;
import org.apache.commons.lang.ClassUtils;

/* compiled from: ModuleMapping.kt */
/* loaded from: classes2.dex */
public final class ModuleMappingKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String internalNameOf(String str, String str2) {
        if (str.length() == 0) {
            return str2;
        }
        return StringsKt.replace$default(str, ClassUtils.PACKAGE_SEPARATOR_CHAR, '/', false, 4, (Object) null) + "/" + str2;
    }
}

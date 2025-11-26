package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.TypeCastException;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: ModuleMapping.kt */
/* loaded from: classes2.dex */
public final class PackageParts {
    private final Set<String> metadataParts;
    private final String packageFqName;
    private final LinkedHashMap<String, String> packageParts;

    public PackageParts(String packageFqName) {
        Intrinsics.checkParameterIsNotNull(packageFqName, "packageFqName");
        this.packageFqName = packageFqName;
        this.packageParts = new LinkedHashMap<>();
        this.metadataParts = new LinkedHashSet();
    }

    public final Set<String> getParts() {
        Set<String> setKeySet = this.packageParts.keySet();
        Intrinsics.checkExpressionValueIsNotNull(setKeySet, "packageParts.keys");
        return setKeySet;
    }

    public final void addPart(String partInternalName, String str) {
        Intrinsics.checkParameterIsNotNull(partInternalName, "partInternalName");
        this.packageParts.put(partInternalName, str);
    }

    public final void addMetadataPart(String shortName) {
        Intrinsics.checkParameterIsNotNull(shortName, "shortName");
        Set<String> set = this.metadataParts;
        if (set == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableSet<kotlin.String>");
        }
        TypeIntrinsics.asMutableSet(set).add(shortName);
    }

    public boolean equals(Object obj) {
        if (obj instanceof PackageParts) {
            PackageParts packageParts = (PackageParts) obj;
            if (Intrinsics.areEqual(packageParts.packageFqName, this.packageFqName) && Intrinsics.areEqual(packageParts.packageParts, this.packageParts) && Intrinsics.areEqual(packageParts.metadataParts, this.metadataParts)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((this.packageFqName.hashCode() * 31) + this.packageParts.hashCode()) * 31) + this.metadataParts.hashCode();
    }

    public String toString() {
        return SetsKt.plus((Set) getParts(), (Iterable) this.metadataParts).toString();
    }
}

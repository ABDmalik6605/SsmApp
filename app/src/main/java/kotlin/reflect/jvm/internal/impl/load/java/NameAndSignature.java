package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* compiled from: specialBuiltinMembers.kt */
/* loaded from: classes2.dex */
final class NameAndSignature {
    private final Name name;
    private final String signature;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NameAndSignature)) {
            return false;
        }
        NameAndSignature nameAndSignature = (NameAndSignature) obj;
        return Intrinsics.areEqual(this.name, nameAndSignature.name) && Intrinsics.areEqual(this.signature, nameAndSignature.signature);
    }

    public int hashCode() {
        Name name = this.name;
        int iHashCode = (name != null ? name.hashCode() : 0) * 31;
        String str = this.signature;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "NameAndSignature(name=" + this.name + ", signature=" + this.signature + ")";
    }

    public NameAndSignature(Name name, String signature) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(signature, "signature");
        this.name = name;
        this.signature = signature;
    }

    public final Name getName() {
        return this.name;
    }

    public final String getSignature() {
        return this.signature;
    }
}

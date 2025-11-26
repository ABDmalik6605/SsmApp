package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* compiled from: NameResolverUtil.kt */
/* loaded from: classes2.dex */
public final class NameResolverUtilKt {
    public static final ClassId getClassId(NameResolver receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        ClassId classIdFromString = ClassId.fromString(receiver.getQualifiedClassName(i), receiver.isLocalClassName(i));
        Intrinsics.checkExpressionValueIsNotNull(classIdFromString, "ClassId.fromString(getQu… isLocalClassName(index))");
        return classIdFromString;
    }

    public static final Name getName(NameResolver receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Name nameGuessByFirstCharacter = Name.guessByFirstCharacter(receiver.getString(i));
        Intrinsics.checkExpressionValueIsNotNull(nameGuessByFirstCharacter, "Name.guessByFirstCharacter(getString(index))");
        return nameGuessByFirstCharacter;
    }
}

package kotlin.reflect.jvm.internal.impl.incremental;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LocationInfo;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.incremental.components.Position;
import kotlin.reflect.jvm.internal.impl.incremental.components.ScopeKind;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;

/* compiled from: utils.kt */
/* loaded from: classes2.dex */
public final class UtilsKt {
    public static final void record(LookupTracker receiver, LookupLocation from, ClassDescriptor scopeOwner, Name name) {
        LocationInfo location;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(from, "from");
        Intrinsics.checkParameterIsNotNull(scopeOwner, "scopeOwner");
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (receiver == LookupTracker.DO_NOTHING.INSTANCE || (location = from.getLocation()) == null) {
            return;
        }
        Position position = receiver.getRequiresPosition() ? location.getPosition() : Position.Companion.getNO_POSITION();
        String filePath = location.getFilePath();
        String strAsString = DescriptorUtils.getFqName(scopeOwner).asString();
        Intrinsics.checkExpressionValueIsNotNull(strAsString, "DescriptorUtils.getFqName(scopeOwner).asString()");
        ScopeKind scopeKind = ScopeKind.CLASSIFIER;
        String strAsString2 = name.asString();
        Intrinsics.checkExpressionValueIsNotNull(strAsString2, "name.asString()");
        receiver.record(filePath, position, strAsString, scopeKind, strAsString2);
    }

    public static final void record(LookupTracker receiver, LookupLocation from, PackageFragmentDescriptor scopeOwner, Name name) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(from, "from");
        Intrinsics.checkParameterIsNotNull(scopeOwner, "scopeOwner");
        Intrinsics.checkParameterIsNotNull(name, "name");
        String strAsString = scopeOwner.getFqName().asString();
        Intrinsics.checkExpressionValueIsNotNull(strAsString, "scopeOwner.fqName.asString()");
        String strAsString2 = name.asString();
        Intrinsics.checkExpressionValueIsNotNull(strAsString2, "name.asString()");
        recordPackageLookup(receiver, from, strAsString, strAsString2);
    }

    public static final void recordPackageLookup(LookupTracker receiver, LookupLocation from, String packageFqName, String name) {
        LocationInfo location;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(from, "from");
        Intrinsics.checkParameterIsNotNull(packageFqName, "packageFqName");
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (receiver == LookupTracker.DO_NOTHING.INSTANCE || (location = from.getLocation()) == null) {
            return;
        }
        receiver.record(location.getFilePath(), receiver.getRequiresPosition() ? location.getPosition() : Position.Companion.getNO_POSITION(), packageFqName, ScopeKind.PACKAGE, name);
    }
}

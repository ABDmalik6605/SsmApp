package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;

/* compiled from: VisibilityUtil.kt */
/* loaded from: classes2.dex */
public final class VisibilityUtilKt {
    public static final CallableMemberDescriptor findMemberWithMaxVisibility(Collection<? extends CallableMemberDescriptor> descriptors) {
        Integer numCompare;
        Intrinsics.checkParameterIsNotNull(descriptors, "descriptors");
        descriptors.isEmpty();
        CallableMemberDescriptor callableMemberDescriptor = (CallableMemberDescriptor) null;
        for (CallableMemberDescriptor callableMemberDescriptor2 : descriptors) {
            if (callableMemberDescriptor == null || ((numCompare = Visibilities.compare(callableMemberDescriptor.getVisibility(), callableMemberDescriptor2.getVisibility())) != null && Intrinsics.compare(numCompare.intValue(), 0) < 0)) {
                callableMemberDescriptor = callableMemberDescriptor2;
            }
        }
        if (callableMemberDescriptor == null) {
            Intrinsics.throwNpe();
        }
        return callableMemberDescriptor;
    }
}

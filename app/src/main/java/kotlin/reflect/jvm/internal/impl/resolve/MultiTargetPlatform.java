package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;

/* compiled from: MultiTargetPlatform.kt */
/* loaded from: classes2.dex */
public abstract class MultiTargetPlatform implements Comparable<MultiTargetPlatform> {
    public static final Companion Companion = new Companion(null);
    public static final ModuleDescriptor.Capability<MultiTargetPlatform> CAPABILITY = new ModuleDescriptor.Capability<>("MULTI_TARGET_PLATFORM");

    private MultiTargetPlatform() {
    }

    /* compiled from: MultiTargetPlatform.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

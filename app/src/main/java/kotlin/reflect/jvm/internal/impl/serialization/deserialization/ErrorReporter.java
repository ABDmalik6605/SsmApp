package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;

/* loaded from: classes2.dex */
public interface ErrorReporter {
    public static final ErrorReporter DO_NOTHING = new ErrorReporter() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter.1
        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter
        public void reportCannotInferVisibility(CallableMemberDescriptor callableMemberDescriptor) {
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter
        public void reportIncompleteHierarchy(ClassDescriptor classDescriptor, List<String> list) {
        }
    };

    void reportCannotInferVisibility(CallableMemberDescriptor callableMemberDescriptor);

    void reportIncompleteHierarchy(ClassDescriptor classDescriptor, List<String> list);
}

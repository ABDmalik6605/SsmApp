package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;

/* compiled from: AnnotationUseSiteTarget.kt */
/* loaded from: classes2.dex */
public enum AnnotationUseSiteTarget {
    FIELD(null, 1, null),
    FILE(null, 1, null),
    PROPERTY(0 == true ? 1 : 0, 1, null),
    PROPERTY_GETTER("get"),
    PROPERTY_SETTER("set"),
    RECEIVER(0 == true ? 1 : 0, 1, null),
    CONSTRUCTOR_PARAMETER("param"),
    SETTER_PARAMETER("setparam"),
    PROPERTY_DELEGATE_FIELD("delegate");

    public static final Companion Companion = new Companion(null);
    private final String renderName;

    AnnotationUseSiteTarget(String str) {
        if (str == null) {
            String strName = name();
            if (strName == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            str = strName.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).toLowerCase()");
        }
        this.renderName = str;
    }

    /* synthetic */ AnnotationUseSiteTarget(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (String) null : str);
    }

    public final String getRenderName() {
        return this.renderName;
    }

    /* compiled from: AnnotationUseSiteTarget.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AnnotationUseSiteTarget getAssociatedUseSiteTarget(DeclarationDescriptor descriptor) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            if (descriptor instanceof PropertyDescriptor) {
                return AnnotationUseSiteTarget.PROPERTY;
            }
            if (descriptor instanceof ValueParameterDescriptor) {
                return AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER;
            }
            if (descriptor instanceof PropertyGetterDescriptor) {
                return AnnotationUseSiteTarget.PROPERTY_GETTER;
            }
            if (descriptor instanceof PropertySetterDescriptor) {
                return AnnotationUseSiteTarget.PROPERTY_SETTER;
            }
            return null;
        }
    }
}

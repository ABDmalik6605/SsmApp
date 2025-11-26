package kotlin.reflect.jvm.internal.impl.types;

/* loaded from: classes2.dex */
public interface TypeProjection {
    Variance getProjectionKind();

    KotlinType getType();

    boolean isStarProjection();
}

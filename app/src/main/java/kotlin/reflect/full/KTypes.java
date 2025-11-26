package kotlin.reflect.full;

import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* compiled from: KTypes.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u001a\u0014\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0001H\u0007¨\u0006\u0007"}, d2 = {"isSubtypeOf", "", "Lkotlin/reflect/KType;", "other", "isSupertypeOf", "withNullability", "nullable", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class KTypes {
    public static final KType withNullability(final KType receiver, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.isMarkedNullable()) {
            if (z) {
                return receiver;
            }
            KotlinType kotlinTypeMakeNotNullable = TypeUtils.makeNotNullable(((KTypeImpl) receiver).getType());
            Intrinsics.checkExpressionValueIsNotNull(kotlinTypeMakeNotNullable, "TypeUtils.makeNotNullabl…(this as KTypeImpl).type)");
            return new KTypeImpl(kotlinTypeMakeNotNullable, new Function0<Type>() { // from class: kotlin.reflect.full.KTypes.withNullability.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Type invoke() {
                    return ((KTypeImpl) receiver).getJavaType$kotlin_reflect_api();
                }
            });
        }
        KotlinType type = ((KTypeImpl) receiver).getType();
        if (FlexibleTypesKt.isFlexible(type)) {
            KotlinType kotlinTypeMakeNullableAsSpecified = TypeUtils.makeNullableAsSpecified(type, z);
            Intrinsics.checkExpressionValueIsNotNull(kotlinTypeMakeNullableAsSpecified, "TypeUtils.makeNullableAs…ied(kotlinType, nullable)");
            return new KTypeImpl(kotlinTypeMakeNullableAsSpecified, new Function0<Type>() { // from class: kotlin.reflect.full.KTypes.withNullability.2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Type invoke() {
                    return ((KTypeImpl) receiver).getJavaType$kotlin_reflect_api();
                }
            });
        }
        if (!z) {
            return receiver;
        }
        KotlinType kotlinTypeMakeNullable = TypeUtils.makeNullable(type);
        Intrinsics.checkExpressionValueIsNotNull(kotlinTypeMakeNullable, "TypeUtils.makeNullable(kotlinType)");
        return new KTypeImpl(kotlinTypeMakeNullable, new Function0<Type>() { // from class: kotlin.reflect.full.KTypes.withNullability.3
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Type invoke() {
                return ((KTypeImpl) receiver).getJavaType$kotlin_reflect_api();
            }
        });
    }

    public static final boolean isSubtypeOf(KType receiver, KType other) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return TypeUtilsKt.isSubtypeOf(((KTypeImpl) receiver).getType(), ((KTypeImpl) other).getType());
    }

    public static final boolean isSupertypeOf(KType receiver, KType other) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return isSubtypeOf(other, receiver);
    }
}

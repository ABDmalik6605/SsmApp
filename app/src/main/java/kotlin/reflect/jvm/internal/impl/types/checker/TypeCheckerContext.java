package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayDeque;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

/* compiled from: TypeCheckerContext.kt */
/* loaded from: classes2.dex */
public class TypeCheckerContext {
    private final boolean allowedTypeVariable;
    private int argumentsDepth;
    private final boolean errorTypeEqualsToAnything;
    private ArrayDeque<SimpleType> supertypesDeque;
    private boolean supertypesLocked;
    private Set<SimpleType> supertypesSet;

    /* compiled from: TypeCheckerContext.kt */
    public enum LowerCapturedTypePolicy {
        CHECK_ONLY_LOWER,
        CHECK_SUBTYPE_AND_LOWER,
        SKIP_LOWER
    }

    /* compiled from: TypeCheckerContext.kt */
    public enum SeveralSupertypesWithSameConstructorPolicy {
        TAKE_FIRST_FOR_SUBTYPING,
        FORCE_NOT_SUBTYPE,
        CHECK_ANY_OF_THEM,
        INTERSECT_ARGUMENTS_AND_CHECK_AGAIN
    }

    public Boolean addSubtypeConstraint(UnwrappedType subType, UnwrappedType superType) {
        Intrinsics.checkParameterIsNotNull(subType, "subType");
        Intrinsics.checkParameterIsNotNull(superType, "superType");
        return null;
    }

    public TypeCheckerContext(boolean z, boolean z2) {
        this.errorTypeEqualsToAnything = z;
        this.allowedTypeVariable = z2;
    }

    public /* synthetic */ TypeCheckerContext(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? true : z2);
    }

    public final boolean getErrorTypeEqualsToAnything() {
        return this.errorTypeEqualsToAnything;
    }

    public boolean areEqualTypeConstructors(TypeConstructor a, TypeConstructor b) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        return Intrinsics.areEqual(a, b);
    }

    public LowerCapturedTypePolicy getLowerCapturedTypePolicy(SimpleType subType, NewCapturedType superType) {
        Intrinsics.checkParameterIsNotNull(subType, "subType");
        Intrinsics.checkParameterIsNotNull(superType, "superType");
        return LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER;
    }

    public SeveralSupertypesWithSameConstructorPolicy getSameConstructorPolicy() {
        return SeveralSupertypesWithSameConstructorPolicy.INTERSECT_ARGUMENTS_AND_CHECK_AGAIN;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initialize() {
        this.supertypesLocked = true;
        if (this.supertypesDeque == null) {
            this.supertypesDeque = new ArrayDeque<>(4);
        }
        if (this.supertypesSet == null) {
            this.supertypesSet = SmartSet.Companion.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clear() {
        ArrayDeque<SimpleType> arrayDeque = this.supertypesDeque;
        if (arrayDeque == null) {
            Intrinsics.throwNpe();
        }
        arrayDeque.clear();
        Set<SimpleType> set = this.supertypesSet;
        if (set == null) {
            Intrinsics.throwNpe();
        }
        set.clear();
        this.supertypesLocked = false;
    }

    /* compiled from: TypeCheckerContext.kt */
    public static abstract class SupertypesPolicy {
        /* renamed from: transformType */
        public abstract SimpleType mo1342transformType(KotlinType kotlinType);

        private SupertypesPolicy() {
        }

        public /* synthetic */ SupertypesPolicy(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: TypeCheckerContext.kt */
        public static final class None extends SupertypesPolicy {
            public static final None INSTANCE = new None();

            private None() {
                super(null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy
            /* renamed from: transformType, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ SimpleType mo1342transformType(KotlinType kotlinType) {
                return (SimpleType) transformType(kotlinType);
            }

            public Void transformType(KotlinType type) {
                Intrinsics.checkParameterIsNotNull(type, "type");
                throw new UnsupportedOperationException("Should not be called");
            }
        }

        /* compiled from: TypeCheckerContext.kt */
        public static final class UpperIfFlexible extends SupertypesPolicy {
            public static final UpperIfFlexible INSTANCE = new UpperIfFlexible();

            private UpperIfFlexible() {
                super(null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy
            /* renamed from: transformType */
            public SimpleType mo1342transformType(KotlinType type) {
                Intrinsics.checkParameterIsNotNull(type, "type");
                return FlexibleTypesKt.upperIfFlexible(type);
            }
        }

        /* compiled from: TypeCheckerContext.kt */
        public static final class LowerIfFlexible extends SupertypesPolicy {
            public static final LowerIfFlexible INSTANCE = new LowerIfFlexible();

            private LowerIfFlexible() {
                super(null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy
            /* renamed from: transformType */
            public SimpleType mo1342transformType(KotlinType type) {
                Intrinsics.checkParameterIsNotNull(type, "type");
                return FlexibleTypesKt.lowerIfFlexible(type);
            }
        }

        /* compiled from: TypeCheckerContext.kt */
        public static final class LowerIfFlexibleWithCustomSubstitutor extends SupertypesPolicy {
            private final TypeSubstitutor substitutor;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public LowerIfFlexibleWithCustomSubstitutor(TypeSubstitutor substitutor) {
                super(null);
                Intrinsics.checkParameterIsNotNull(substitutor, "substitutor");
                this.substitutor = substitutor;
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy
            /* renamed from: transformType */
            public SimpleType mo1342transformType(KotlinType type) {
                Intrinsics.checkParameterIsNotNull(type, "type");
                KotlinType kotlinTypeSafeSubstitute = this.substitutor.safeSubstitute(FlexibleTypesKt.lowerIfFlexible(type), Variance.INVARIANT);
                Intrinsics.checkExpressionValueIsNotNull(kotlinTypeSafeSubstitute, "substitutor.safeSubstitu…le(), Variance.INVARIANT)");
                return TypeSubstitutionKt.asSimpleType(kotlinTypeSafeSubstitute);
            }
        }
    }

    public final boolean isAllowedTypeVariable(UnwrappedType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return this.allowedTypeVariable && (receiver.getConstructor() instanceof NewTypeVariableConstructor);
    }
}

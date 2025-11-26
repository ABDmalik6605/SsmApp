package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;

/* loaded from: classes2.dex */
public class KotlinTypeCheckerImpl implements KotlinTypeChecker {
    private final TypeCheckingProcedure procedure;

    public static KotlinTypeChecker withAxioms(final KotlinTypeChecker.TypeConstructorEquality typeConstructorEquality) {
        return new KotlinTypeCheckerImpl(new TypeCheckingProcedure(new TypeCheckerProcedureCallbacksImpl() { // from class: kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeCheckerImpl.1
            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerProcedureCallbacksImpl, kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckingProcedureCallbacks
            public boolean assertEqualTypeConstructors(TypeConstructor typeConstructor, TypeConstructor typeConstructor2) {
                return typeConstructor.equals(typeConstructor2) || typeConstructorEquality.equals(typeConstructor, typeConstructor2);
            }
        }));
    }

    protected KotlinTypeCheckerImpl(TypeCheckingProcedure typeCheckingProcedure) {
        this.procedure = typeCheckingProcedure;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker
    public boolean isSubtypeOf(KotlinType kotlinType, KotlinType kotlinType2) {
        return this.procedure.isSubtypeOf(kotlinType, kotlinType2);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker
    public boolean equalTypes(KotlinType kotlinType, KotlinType kotlinType2) {
        return this.procedure.equalTypes(kotlinType, kotlinType2);
    }
}

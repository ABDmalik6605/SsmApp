package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;

/* compiled from: NewCapturedType.kt */
/* loaded from: classes2.dex */
public final class NewCapturedTypeKt {
    public static /* bridge */ /* synthetic */ SimpleType captureFromArguments$default(SimpleType simpleType, CaptureStatus captureStatus, Function2 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            function2 = FunctionsKt.getDO_NOTHING_2();
        }
        return captureFromArguments(simpleType, captureStatus, function2);
    }

    public static final SimpleType captureFromArguments(SimpleType type, CaptureStatus status, Function2<? super Integer, ? super NewCapturedType, Unit> acceptNewCapturedType) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Intrinsics.checkParameterIsNotNull(acceptNewCapturedType, "acceptNewCapturedType");
        if (type.getArguments().size() != type.getConstructor().getParameters().size()) {
            return null;
        }
        List<TypeProjection> arguments = type.getArguments();
        List<TypeProjection> list = arguments;
        boolean z = true;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (!(((TypeProjection) it.next()).getProjectionKind() == Variance.INVARIANT)) {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            return null;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (TypeProjection typeProjectionAsTypeProjection : list) {
            if (typeProjectionAsTypeProjection.getProjectionKind() != Variance.INVARIANT) {
                typeProjectionAsTypeProjection = TypeUtilsKt.asTypeProjection(new NewCapturedType(status, (typeProjectionAsTypeProjection.isStarProjection() || typeProjectionAsTypeProjection.getProjectionKind() != Variance.IN_VARIANCE) ? null : typeProjectionAsTypeProjection.getType().unwrap(), typeProjectionAsTypeProjection));
            }
            arrayList.add(typeProjectionAsTypeProjection);
        }
        ArrayList arrayList2 = arrayList;
        TypeSubstitutor typeSubstitutorBuildSubstitutor = TypeConstructorSubstitution.Companion.create(type.getConstructor(), arrayList2).buildSubstitutor();
        int size = arguments.size();
        for (int i = 0; i < size; i++) {
            TypeProjection typeProjection = arguments.get(i);
            TypeProjection typeProjection2 = (TypeProjection) arrayList2.get(i);
            if (typeProjection.getProjectionKind() != Variance.INVARIANT) {
                TypeParameterDescriptor typeParameterDescriptor = type.getConstructor().getParameters().get(i);
                Intrinsics.checkExpressionValueIsNotNull(typeParameterDescriptor, "type.constructor.parameters[index]");
                List<KotlinType> upperBounds = typeParameterDescriptor.getUpperBounds();
                Intrinsics.checkExpressionValueIsNotNull(upperBounds, "type.constructor.parameters[index].upperBounds");
                List<KotlinType> list2 = upperBounds;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it2 = list2.iterator();
                while (it2.hasNext()) {
                    arrayList3.add(NewKotlinTypeChecker.INSTANCE.transformToNewType(typeSubstitutorBuildSubstitutor.safeSubstitute((KotlinType) it2.next(), Variance.INVARIANT).unwrap()));
                }
                ArrayList arrayListPlus = arrayList3;
                if (!typeProjection.isStarProjection() && typeProjection.getProjectionKind() == Variance.OUT_VARIANCE) {
                    arrayListPlus = CollectionsKt.plus((Collection<? extends UnwrappedType>) arrayListPlus, NewKotlinTypeChecker.INSTANCE.transformToNewType(typeProjection.getType().unwrap()));
                }
                KotlinType type2 = typeProjection2.getType();
                if (type2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.checker.NewCapturedType");
                }
                NewCapturedType newCapturedType = (NewCapturedType) type2;
                newCapturedType.getConstructor().initializeSupertypes(arrayListPlus);
                acceptNewCapturedType.invoke(Integer.valueOf(i), newCapturedType);
            }
        }
        return KotlinTypeFactory.simpleType(type.getAnnotations(), type.getConstructor(), arrayList2, type.isMarkedNullable());
    }
}

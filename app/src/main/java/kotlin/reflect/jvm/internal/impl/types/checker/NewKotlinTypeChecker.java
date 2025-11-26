package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModalityKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerValueTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.DynamicTypesKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.StubType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;

/* compiled from: NewKotlinTypeChecker.kt */
/* loaded from: classes2.dex */
public final class NewKotlinTypeChecker implements KotlinTypeChecker {
    public static final NewKotlinTypeChecker INSTANCE = new NewKotlinTypeChecker();

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[TypeCheckerContext.LowerCapturedTypePolicy.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[TypeCheckerContext.LowerCapturedTypePolicy.CHECK_ONLY_LOWER.ordinal()] = 1;
            iArr[TypeCheckerContext.LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER.ordinal()] = 2;
            iArr[TypeCheckerContext.LowerCapturedTypePolicy.SKIP_LOWER.ordinal()] = 3;
            int[] iArr2 = new int[TypeCheckerContext.SeveralSupertypesWithSameConstructorPolicy.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[TypeCheckerContext.SeveralSupertypesWithSameConstructorPolicy.FORCE_NOT_SUBTYPE.ordinal()] = 1;
            iArr2[TypeCheckerContext.SeveralSupertypesWithSameConstructorPolicy.TAKE_FIRST_FOR_SUBTYPING.ordinal()] = 2;
            iArr2[TypeCheckerContext.SeveralSupertypesWithSameConstructorPolicy.CHECK_ANY_OF_THEM.ordinal()] = 3;
            iArr2[TypeCheckerContext.SeveralSupertypesWithSameConstructorPolicy.INTERSECT_ARGUMENTS_AND_CHECK_AGAIN.ordinal()] = 4;
            int[] iArr3 = new int[Variance.values().length];
            $EnumSwitchMapping$2 = iArr3;
            iArr3[Variance.INVARIANT.ordinal()] = 1;
            iArr3[Variance.OUT_VARIANCE.ordinal()] = 2;
            iArr3[Variance.IN_VARIANCE.ordinal()] = 3;
        }
    }

    private NewKotlinTypeChecker() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker
    public boolean isSubtypeOf(KotlinType subtype, KotlinType supertype) {
        Intrinsics.checkParameterIsNotNull(subtype, "subtype");
        Intrinsics.checkParameterIsNotNull(supertype, "supertype");
        return isSubtypeOf(new TypeCheckerContext(true, false, 2, null), subtype.unwrap(), supertype.unwrap());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker
    public boolean equalTypes(KotlinType a, KotlinType b) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        boolean z = false;
        return equalTypes(new TypeCheckerContext(z, z, 2, null), a.unwrap(), b.unwrap());
    }

    public final boolean equalTypes(TypeCheckerContext receiver, UnwrappedType a, UnwrappedType b) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        if (a == b) {
            return true;
        }
        UnwrappedType unwrappedType = a;
        if (isCommonDenotableType(unwrappedType)) {
            UnwrappedType unwrappedType2 = b;
            if (isCommonDenotableType(unwrappedType2)) {
                if (!receiver.areEqualTypeConstructors(a.getConstructor(), b.getConstructor())) {
                    return false;
                }
                if (a.getArguments().isEmpty()) {
                    return hasFlexibleNullability(unwrappedType) || hasFlexibleNullability(unwrappedType2) || a.isMarkedNullable() == b.isMarkedNullable();
                }
            }
        }
        return isSubtypeOf(receiver, a, b) && isSubtypeOf(receiver, b, a);
    }

    private final boolean hasFlexibleNullability(KotlinType kotlinType) {
        return FlexibleTypesKt.lowerIfFlexible(kotlinType).isMarkedNullable() != FlexibleTypesKt.upperIfFlexible(kotlinType).isMarkedNullable();
    }

    private final boolean isCommonDenotableType(KotlinType kotlinType) {
        return kotlinType.getConstructor().isDenotable() && !DynamicTypesKt.isDynamic(kotlinType) && !SpecialTypesKt.isDefinitelyNotNullType(kotlinType) && Intrinsics.areEqual(FlexibleTypesKt.lowerIfFlexible(kotlinType).getConstructor(), FlexibleTypesKt.upperIfFlexible(kotlinType).getConstructor());
    }

    public final boolean isSubtypeOf(TypeCheckerContext receiver, UnwrappedType subType, UnwrappedType superType) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(subType, "subType");
        Intrinsics.checkParameterIsNotNull(superType, "superType");
        if (subType == superType) {
            return true;
        }
        UnwrappedType unwrappedTypeTransformToNewType = transformToNewType(subType);
        UnwrappedType unwrappedTypeTransformToNewType2 = transformToNewType(superType);
        UnwrappedType unwrappedType = unwrappedTypeTransformToNewType;
        UnwrappedType unwrappedType2 = unwrappedTypeTransformToNewType2;
        Boolean boolCheckSubtypeForSpecialCases = checkSubtypeForSpecialCases(receiver, FlexibleTypesKt.lowerIfFlexible(unwrappedType), FlexibleTypesKt.upperIfFlexible(unwrappedType2));
        if (boolCheckSubtypeForSpecialCases != null) {
            boolean zBooleanValue = boolCheckSubtypeForSpecialCases.booleanValue();
            receiver.addSubtypeConstraint(unwrappedTypeTransformToNewType, unwrappedTypeTransformToNewType2);
            return zBooleanValue;
        }
        Boolean boolAddSubtypeConstraint = receiver.addSubtypeConstraint(unwrappedTypeTransformToNewType, unwrappedTypeTransformToNewType2);
        return boolAddSubtypeConstraint != null ? boolAddSubtypeConstraint.booleanValue() : isSubtypeOfForSingleClassifierType(receiver, FlexibleTypesKt.lowerIfFlexible(unwrappedType), FlexibleTypesKt.upperIfFlexible(unwrappedType2));
    }

    public final SimpleType transformToNewType(SimpleType type) {
        KotlinType type2;
        Intrinsics.checkParameterIsNotNull(type, "type");
        TypeConstructor constructor = type.getConstructor();
        if (constructor instanceof CapturedTypeConstructor) {
            CapturedTypeConstructor capturedTypeConstructor = (CapturedTypeConstructor) constructor;
            TypeProjection typeProjection = capturedTypeConstructor.getTypeProjection();
            UnwrappedType unwrappedTypeUnwrap = null;
            if (!(typeProjection.getProjectionKind() == Variance.IN_VARIANCE)) {
                typeProjection = null;
            }
            if (typeProjection != null && (type2 = typeProjection.getType()) != null) {
                unwrappedTypeUnwrap = type2.unwrap();
            }
            UnwrappedType unwrappedType = unwrappedTypeUnwrap;
            if (capturedTypeConstructor.getNewTypeConstructor() == null) {
                TypeProjection typeProjection2 = capturedTypeConstructor.getTypeProjection();
                Collection<KotlinType> supertypes = capturedTypeConstructor.getSupertypes();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(supertypes, 10));
                Iterator<T> it = supertypes.iterator();
                while (it.hasNext()) {
                    arrayList.add(((KotlinType) it.next()).unwrap());
                }
                capturedTypeConstructor.setNewTypeConstructor(new NewCapturedTypeConstructor(typeProjection2, arrayList));
            }
            CaptureStatus captureStatus = CaptureStatus.FOR_SUBTYPING;
            NewCapturedTypeConstructor newTypeConstructor = capturedTypeConstructor.getNewTypeConstructor();
            if (newTypeConstructor == null) {
                Intrinsics.throwNpe();
            }
            return new NewCapturedType(captureStatus, newTypeConstructor, unwrappedType, type.getAnnotations(), type.isMarkedNullable());
        }
        if (constructor instanceof IntegerValueTypeConstructor) {
            Collection<KotlinType> supertypes2 = ((IntegerValueTypeConstructor) constructor).getSupertypes();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(supertypes2, 10));
            Iterator<T> it2 = supertypes2.iterator();
            while (it2.hasNext()) {
                arrayList2.add(TypeUtils.makeNullableAsSpecified((KotlinType) it2.next(), type.isMarkedNullable()));
            }
            return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(type.getAnnotations(), new IntersectionTypeConstructor(arrayList2), CollectionsKt.emptyList(), false, type.getMemberScope());
        }
        if (!(constructor instanceof IntersectionTypeConstructor) || !type.isMarkedNullable()) {
            return type;
        }
        Collection<KotlinType> supertypes3 = ((IntersectionTypeConstructor) constructor).getSupertypes();
        Intrinsics.checkExpressionValueIsNotNull(supertypes3, "constructor.supertypes");
        Collection<KotlinType> collection = supertypes3;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        for (KotlinType it3 : collection) {
            Intrinsics.checkExpressionValueIsNotNull(it3, "it");
            arrayList3.add(TypeUtilsKt.makeNullable(it3));
        }
        IntersectionTypeConstructor intersectionTypeConstructor = new IntersectionTypeConstructor(arrayList3);
        Annotations annotations = type.getAnnotations();
        IntersectionTypeConstructor intersectionTypeConstructor2 = intersectionTypeConstructor;
        List listEmptyList = CollectionsKt.emptyList();
        MemberScope memberScopeCreateScopeForKotlinType = intersectionTypeConstructor.createScopeForKotlinType();
        Intrinsics.checkExpressionValueIsNotNull(memberScopeCreateScopeForKotlinType, "newConstructor.createScopeForKotlinType()");
        return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(annotations, intersectionTypeConstructor2, listEmptyList, false, memberScopeCreateScopeForKotlinType);
    }

    public final UnwrappedType transformToNewType(UnwrappedType type) {
        SimpleType simpleTypeFlexibleType;
        Intrinsics.checkParameterIsNotNull(type, "type");
        if (type instanceof SimpleType) {
            simpleTypeFlexibleType = transformToNewType((SimpleType) type);
        } else if (type instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) type;
            SimpleType simpleTypeTransformToNewType = transformToNewType(flexibleType.getLowerBound());
            SimpleType simpleTypeTransformToNewType2 = transformToNewType(flexibleType.getUpperBound());
            simpleTypeFlexibleType = (simpleTypeTransformToNewType == flexibleType.getLowerBound() && simpleTypeTransformToNewType2 == flexibleType.getUpperBound()) ? type : KotlinTypeFactory.flexibleType(simpleTypeTransformToNewType, simpleTypeTransformToNewType2);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return TypeWithEnhancementKt.inheritEnhancement(simpleTypeFlexibleType, type);
    }

    private final Boolean checkSubtypeForSpecialCases(TypeCheckerContext typeCheckerContext, SimpleType simpleType, SimpleType simpleType2) {
        boolean z = false;
        if (KotlinTypeKt.isError(simpleType) || KotlinTypeKt.isError(simpleType2)) {
            if (typeCheckerContext.getErrorTypeEqualsToAnything()) {
                return true;
            }
            if (!simpleType.isMarkedNullable() || simpleType2.isMarkedNullable()) {
                return Boolean.valueOf(StrictEqualityTypeChecker.INSTANCE.strictEqualTypes(simpleType.makeNullableAsSpecified(false), simpleType2.makeNullableAsSpecified(false)));
            }
            return false;
        }
        if ((simpleType instanceof StubType) || (simpleType2 instanceof StubType)) {
            return true;
        }
        if (simpleType2 instanceof NewCapturedType) {
            NewCapturedType newCapturedType = (NewCapturedType) simpleType2;
            if (newCapturedType.getLowerType() != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[typeCheckerContext.getLowerCapturedTypePolicy(simpleType, newCapturedType).ordinal()];
                if (i == 1) {
                    return Boolean.valueOf(isSubtypeOf(typeCheckerContext, simpleType, newCapturedType.getLowerType()));
                }
                if (i == 2 && isSubtypeOf(typeCheckerContext, simpleType, newCapturedType.getLowerType())) {
                    return true;
                }
            }
        }
        TypeConstructor constructor = simpleType2.getConstructor();
        if (!(constructor instanceof IntersectionTypeConstructor)) {
            constructor = null;
        }
        IntersectionTypeConstructor intersectionTypeConstructor = (IntersectionTypeConstructor) constructor;
        if (intersectionTypeConstructor == null) {
            return null;
        }
        simpleType2.isMarkedNullable();
        Collection<KotlinType> supertypes = intersectionTypeConstructor.getSupertypes();
        Intrinsics.checkExpressionValueIsNotNull(supertypes, "it.supertypes");
        Collection<KotlinType> collection = supertypes;
        if ((collection instanceof Collection) && collection.isEmpty()) {
            z = true;
        } else {
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                if (!INSTANCE.isSubtypeOf(typeCheckerContext, simpleType, ((KotlinType) it.next()).unwrap())) {
                    break;
                }
            }
            z = true;
        }
        return Boolean.valueOf(z);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00ab A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean isSubtypeOfForSingleClassifierType(kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext r17, kotlin.reflect.jvm.internal.impl.types.SimpleType r18, kotlin.reflect.jvm.internal.impl.types.SimpleType r19) {
        /*
            Method dump skipped, instructions count: 411
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker.isSubtypeOfForSingleClassifierType(kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext, kotlin.reflect.jvm.internal.impl.types.SimpleType, kotlin.reflect.jvm.internal.impl.types.SimpleType):boolean");
    }

    private final List<SimpleType> collectAndFilter(TypeCheckerContext typeCheckerContext, SimpleType simpleType, TypeConstructor typeConstructor) {
        return selectOnlyPureKotlinSupertypes(collectAllSupertypesWithGivenTypeConstructor(typeCheckerContext, simpleType, typeConstructor));
    }

    public final List<SimpleType> findCorrespondingSupertypes(TypeCheckerContext receiver, SimpleType baseType, TypeConstructor constructor) {
        TypeCheckerContext.SupertypesPolicy.LowerIfFlexible lowerIfFlexible;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(baseType, "baseType");
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        if (NewKotlinTypeCheckerKt.isClassType(baseType)) {
            return collectAndFilter(receiver, baseType, constructor);
        }
        if (!(constructor.mo1333getDeclarationDescriptor() instanceof ClassDescriptor)) {
            return collectAllSupertypesWithGivenTypeConstructor(receiver, baseType, constructor);
        }
        SmartList<SimpleType> smartList = new SmartList();
        receiver.initialize();
        ArrayDeque arrayDeque = receiver.supertypesDeque;
        if (arrayDeque == null) {
            Intrinsics.throwNpe();
        }
        Set set = receiver.supertypesSet;
        if (set == null) {
            Intrinsics.throwNpe();
        }
        arrayDeque.push(baseType);
        while (!arrayDeque.isEmpty()) {
            if (set.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + baseType + ". Supertypes = " + CollectionsKt.joinToString$default(set, null, null, null, 0, null, null, 63, null)).toString());
            }
            SimpleType current = (SimpleType) arrayDeque.pop();
            Intrinsics.checkExpressionValueIsNotNull(current, "current");
            if (set.add(current)) {
                if (NewKotlinTypeCheckerKt.isClassType(current)) {
                    smartList.add(current);
                    lowerIfFlexible = TypeCheckerContext.SupertypesPolicy.None.INSTANCE;
                } else {
                    lowerIfFlexible = TypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                }
                if (!(!Intrinsics.areEqual(lowerIfFlexible, TypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                    lowerIfFlexible = null;
                }
                if (lowerIfFlexible != null) {
                    for (KotlinType supertype : current.getConstructor().getSupertypes()) {
                        Intrinsics.checkExpressionValueIsNotNull(supertype, "supertype");
                        arrayDeque.add(lowerIfFlexible.mo1342transformType(supertype));
                    }
                }
            }
        }
        receiver.clear();
        ArrayList arrayList = new ArrayList();
        for (SimpleType it : smartList) {
            NewKotlinTypeChecker newKotlinTypeChecker = INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            CollectionsKt.addAll(arrayList, newKotlinTypeChecker.collectAndFilter(receiver, it, constructor));
        }
        return arrayList;
    }

    private final List<SimpleType> collectAllSupertypesWithGivenTypeConstructor(TypeCheckerContext typeCheckerContext, SimpleType simpleType, TypeConstructor typeConstructor) {
        TypeCheckerContext.SupertypesPolicy.LowerIfFlexibleWithCustomSubstitutor lowerIfFlexibleWithCustomSubstitutor;
        ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = typeConstructor.mo1333getDeclarationDescriptor();
        if (!(classifierDescriptorMo1333getDeclarationDescriptor instanceof ClassDescriptor)) {
            classifierDescriptorMo1333getDeclarationDescriptor = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptorMo1333getDeclarationDescriptor;
        if (classDescriptor != null && isCommonFinalClass(classDescriptor)) {
            if (typeCheckerContext.areEqualTypeConstructors(simpleType.getConstructor(), typeConstructor)) {
                SimpleType simpleTypeCaptureFromArguments$default = NewCapturedTypeKt.captureFromArguments$default(simpleType, CaptureStatus.FOR_SUBTYPING, null, 4, null);
                if (simpleTypeCaptureFromArguments$default == null) {
                    simpleTypeCaptureFromArguments$default = simpleType;
                }
                return CollectionsKt.listOf(simpleTypeCaptureFromArguments$default);
            }
            return CollectionsKt.emptyList();
        }
        SmartList smartList = new SmartList();
        typeCheckerContext.initialize();
        ArrayDeque arrayDeque = typeCheckerContext.supertypesDeque;
        if (arrayDeque == null) {
            Intrinsics.throwNpe();
        }
        Set set = typeCheckerContext.supertypesSet;
        if (set == null) {
            Intrinsics.throwNpe();
        }
        arrayDeque.push(simpleType);
        while (!arrayDeque.isEmpty()) {
            if (set.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + simpleType + ". Supertypes = " + CollectionsKt.joinToString$default(set, null, null, null, 0, null, null, 63, null)).toString());
            }
            SimpleType current = (SimpleType) arrayDeque.pop();
            Intrinsics.checkExpressionValueIsNotNull(current, "current");
            if (set.add(current)) {
                SimpleType simpleTypeCaptureFromArguments$default2 = NewCapturedTypeKt.captureFromArguments$default(current, CaptureStatus.FOR_SUBTYPING, null, 4, null);
                if (simpleTypeCaptureFromArguments$default2 == null) {
                    simpleTypeCaptureFromArguments$default2 = current;
                }
                if (typeCheckerContext.areEqualTypeConstructors(simpleTypeCaptureFromArguments$default2.getConstructor(), typeConstructor)) {
                    smartList.add(simpleTypeCaptureFromArguments$default2);
                    lowerIfFlexibleWithCustomSubstitutor = TypeCheckerContext.SupertypesPolicy.None.INSTANCE;
                } else if (simpleTypeCaptureFromArguments$default2.getArguments().isEmpty()) {
                    lowerIfFlexibleWithCustomSubstitutor = TypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                } else {
                    lowerIfFlexibleWithCustomSubstitutor = new TypeCheckerContext.SupertypesPolicy.LowerIfFlexibleWithCustomSubstitutor(TypeConstructorSubstitution.Companion.create(simpleTypeCaptureFromArguments$default2).buildSubstitutor());
                }
                if (!(!Intrinsics.areEqual(lowerIfFlexibleWithCustomSubstitutor, TypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                    lowerIfFlexibleWithCustomSubstitutor = null;
                }
                if (lowerIfFlexibleWithCustomSubstitutor != null) {
                    for (KotlinType supertype : current.getConstructor().getSupertypes()) {
                        Intrinsics.checkExpressionValueIsNotNull(supertype, "supertype");
                        arrayDeque.add(lowerIfFlexibleWithCustomSubstitutor.mo1342transformType(supertype));
                    }
                }
            }
        }
        typeCheckerContext.clear();
        return smartList;
    }

    private final boolean isCommonFinalClass(ClassDescriptor classDescriptor) {
        return (!ModalityKt.isFinalClass(classDescriptor) || classDescriptor.getKind() == ClassKind.ENUM_ENTRY || classDescriptor.getKind() == ClassKind.ANNOTATION_CLASS) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<SimpleType> selectOnlyPureKotlinSupertypes(List<? extends SimpleType> list) {
        if (list.size() < 2) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            List<TypeProjection> arguments = ((SimpleType) next).getArguments();
            if (!(arguments instanceof Collection) || !arguments.isEmpty()) {
                Iterator<T> it2 = arguments.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Intrinsics.checkExpressionValueIsNotNull(((TypeProjection) it2.next()).getType(), "it.type");
                    if (!(!FlexibleTypesKt.isFlexible(r5))) {
                        z = false;
                        break;
                    }
                }
            }
            if (z) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = arrayList;
        return arrayList2.isEmpty() ^ true ? arrayList2 : list;
    }

    public final Variance effectiveVariance(Variance declared, Variance useSite) {
        Intrinsics.checkParameterIsNotNull(declared, "declared");
        Intrinsics.checkParameterIsNotNull(useSite, "useSite");
        if (declared == Variance.INVARIANT) {
            return useSite;
        }
        if (useSite == Variance.INVARIANT || declared == useSite) {
            return declared;
        }
        return null;
    }

    private final boolean isSubtypeForSameConstructor(TypeCheckerContext typeCheckerContext, List<? extends TypeProjection> list, SimpleType simpleType) {
        boolean zEqualTypes;
        if (list == simpleType.getArguments()) {
            return true;
        }
        List<TypeParameterDescriptor> parameters = simpleType.getConstructor().getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "parameters");
        int size = parameters.size();
        for (int i = 0; i < size; i++) {
            TypeProjection typeProjection = simpleType.getArguments().get(i);
            if (!typeProjection.isStarProjection()) {
                UnwrappedType unwrappedTypeUnwrap = typeProjection.getType().unwrap();
                TypeProjection typeProjection2 = list.get(i);
                typeProjection2.getProjectionKind();
                Variance variance = Variance.INVARIANT;
                UnwrappedType unwrappedTypeUnwrap2 = typeProjection2.getType().unwrap();
                TypeParameterDescriptor typeParameterDescriptor = parameters.get(i);
                Intrinsics.checkExpressionValueIsNotNull(typeParameterDescriptor, "parameters[index]");
                Variance variance2 = typeParameterDescriptor.getVariance();
                Intrinsics.checkExpressionValueIsNotNull(variance2, "parameters[index].variance");
                Variance projectionKind = typeProjection.getProjectionKind();
                Intrinsics.checkExpressionValueIsNotNull(projectionKind, "superProjection.projectionKind");
                Variance varianceEffectiveVariance = effectiveVariance(variance2, projectionKind);
                if (varianceEffectiveVariance != null) {
                    if (typeCheckerContext.argumentsDepth > 100) {
                        throw new IllegalStateException(("Arguments depth is too high. Some related argument: " + unwrappedTypeUnwrap2).toString());
                    }
                    typeCheckerContext.argumentsDepth++;
                    int i2 = WhenMappings.$EnumSwitchMapping$2[varianceEffectiveVariance.ordinal()];
                    if (i2 == 1) {
                        zEqualTypes = INSTANCE.equalTypes(typeCheckerContext, unwrappedTypeUnwrap2, unwrappedTypeUnwrap);
                    } else if (i2 == 2) {
                        zEqualTypes = INSTANCE.isSubtypeOf(typeCheckerContext, unwrappedTypeUnwrap2, unwrappedTypeUnwrap);
                    } else {
                        if (i2 != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        zEqualTypes = INSTANCE.isSubtypeOf(typeCheckerContext, unwrappedTypeUnwrap, unwrappedTypeUnwrap2);
                    }
                    typeCheckerContext.argumentsDepth--;
                    if (!zEqualTypes) {
                        return false;
                    }
                } else {
                    return typeCheckerContext.getErrorTypeEqualsToAnything();
                }
            }
        }
        return true;
    }

    private final boolean hasNothingSupertype(TypeCheckerContext typeCheckerContext, SimpleType simpleType) {
        TypeCheckerContext.SupertypesPolicy.LowerIfFlexible lowerIfFlexible;
        if (KotlinBuiltIns.isNothingOrNullableNothing(simpleType)) {
            return true;
        }
        typeCheckerContext.initialize();
        ArrayDeque arrayDeque = typeCheckerContext.supertypesDeque;
        if (arrayDeque == null) {
            Intrinsics.throwNpe();
        }
        Set set = typeCheckerContext.supertypesSet;
        if (set == null) {
            Intrinsics.throwNpe();
        }
        arrayDeque.push(simpleType);
        while (!arrayDeque.isEmpty()) {
            if (set.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + simpleType + ". Supertypes = " + CollectionsKt.joinToString$default(set, null, null, null, 0, null, null, 63, null)).toString());
            }
            SimpleType current = (SimpleType) arrayDeque.pop();
            Intrinsics.checkExpressionValueIsNotNull(current, "current");
            if (set.add(current)) {
                if (NewKotlinTypeCheckerKt.isClassType(current)) {
                    lowerIfFlexible = TypeCheckerContext.SupertypesPolicy.None.INSTANCE;
                } else {
                    lowerIfFlexible = TypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                }
                if (!(!Intrinsics.areEqual(lowerIfFlexible, TypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                    lowerIfFlexible = null;
                }
                if (lowerIfFlexible != null) {
                    for (KotlinType supertype : current.getConstructor().getSupertypes()) {
                        Intrinsics.checkExpressionValueIsNotNull(supertype, "supertype");
                        SimpleType simpleTypeMo1342transformType = lowerIfFlexible.mo1342transformType(supertype);
                        if (KotlinBuiltIns.isNothingOrNullableNothing(simpleTypeMo1342transformType)) {
                            typeCheckerContext.clear();
                            return true;
                        }
                        arrayDeque.add(simpleTypeMo1342transformType);
                    }
                } else {
                    continue;
                }
            }
        }
        typeCheckerContext.clear();
        return false;
    }
}

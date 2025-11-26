package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.SuspendFunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationWithTarget;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotationsWithPossibleTargets;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeBasedStarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.sequences.SequencesKt;

/* compiled from: TypeDeserializer.kt */
/* loaded from: classes2.dex */
public final class TypeDeserializer {
    private final DeserializationContext c;
    private final Function1<Integer, ClassDescriptor> classDescriptors;
    private final String debugName;
    private boolean experimentalSuspendFunctionTypeEncountered;
    private final TypeDeserializer parent;
    private final Function1<Integer, ClassifierDescriptor> typeAliasDescriptors;
    private final Map<Integer, TypeParameterDescriptor> typeParameterDescriptors;

    /* compiled from: TypeDeserializer.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$typeConstructor$1, reason: invalid class name and case insensitive filesystem */
    static final class C01891 extends Lambda implements Function1<Integer, ClassDescriptor> {
        final /* synthetic */ ProtoBuf.Type $proto;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01891(ProtoBuf.Type type) {
            super(1);
            this.$proto = type;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ ClassDescriptor invoke(Integer num) {
            return invoke(num.intValue());
        }

        public final ClassDescriptor invoke(int i) {
            ClassId classId = NameResolverUtilKt.getClassId(TypeDeserializer.this.c.getNameResolver(), i);
            List<Integer> mutableList = SequencesKt.toMutableList(SequencesKt.map(SequencesKt.generateSequence(this.$proto, new Function1<ProtoBuf.Type, ProtoBuf.Type>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$typeConstructor$1$typeParametersCount$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final ProtoBuf.Type invoke(ProtoBuf.Type it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return ProtoTypeTableUtilKt.outerType(it, TypeDeserializer.this.c.getTypeTable());
                }
            }), new Function1<ProtoBuf.Type, Integer>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$typeConstructor$1$typeParametersCount$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(ProtoBuf.Type type) {
                    return Integer.valueOf(invoke2(type));
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final int invoke2(ProtoBuf.Type it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.getArgumentCount();
                }
            }));
            int iCount = SequencesKt.count(SequencesKt.generateSequence(classId, TypeDeserializer$typeConstructor$1$classNestingLevel$1.INSTANCE));
            while (mutableList.size() < iCount) {
                mutableList.add(0);
            }
            return TypeDeserializer.this.c.getComponents().getNotFoundClasses().getClass(classId, mutableList);
        }
    }

    public TypeDeserializer(DeserializationContext c, TypeDeserializer typeDeserializer, List<ProtoBuf.TypeParameter> typeParameterProtos, String debugName, boolean z) {
        LinkedHashMap linkedHashMapEmptyMap;
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(typeParameterProtos, "typeParameterProtos");
        Intrinsics.checkParameterIsNotNull(debugName, "debugName");
        this.c = c;
        this.parent = typeDeserializer;
        this.debugName = debugName;
        this.experimentalSuspendFunctionTypeEncountered = z;
        this.classDescriptors = c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<Integer, ClassDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$classDescriptors$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ ClassDescriptor invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final ClassDescriptor invoke(int i) {
                return this.this$0.computeClassDescriptor(i);
            }
        });
        this.typeAliasDescriptors = c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<Integer, ClassifierDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$typeAliasDescriptors$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ ClassifierDescriptor invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final ClassifierDescriptor invoke(int i) {
                return this.this$0.computeTypeAliasDescriptor(i);
            }
        });
        if (typeParameterProtos.isEmpty()) {
            linkedHashMapEmptyMap = MapsKt.emptyMap();
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int i = 0;
            for (ProtoBuf.TypeParameter typeParameter : typeParameterProtos) {
                linkedHashMap.put(Integer.valueOf(typeParameter.getId()), new DeserializedTypeParameterDescriptor(this.c, typeParameter, i));
                i++;
            }
            linkedHashMapEmptyMap = linkedHashMap;
        }
        this.typeParameterDescriptors = linkedHashMapEmptyMap;
    }

    public /* synthetic */ TypeDeserializer(DeserializationContext deserializationContext, TypeDeserializer typeDeserializer, List list, String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(deserializationContext, typeDeserializer, list, str, (i & 16) != 0 ? false : z);
    }

    public final boolean getExperimentalSuspendFunctionTypeEncountered() {
        return this.experimentalSuspendFunctionTypeEncountered;
    }

    public final List<TypeParameterDescriptor> getOwnTypeParameters() {
        return CollectionsKt.toList(this.typeParameterDescriptors.values());
    }

    public static /* bridge */ /* synthetic */ KotlinType type$default(TypeDeserializer typeDeserializer, ProtoBuf.Type type, Annotations annotations, int i, Object obj) {
        if ((i & 2) != 0) {
            annotations = Annotations.Companion.getEMPTY();
        }
        return typeDeserializer.type(type, annotations);
    }

    public final KotlinType type(ProtoBuf.Type proto, Annotations additionalAnnotations) {
        Intrinsics.checkParameterIsNotNull(proto, "proto");
        Intrinsics.checkParameterIsNotNull(additionalAnnotations, "additionalAnnotations");
        if (proto.hasFlexibleTypeCapabilitiesId()) {
            String string = this.c.getNameResolver().getString(proto.getFlexibleTypeCapabilitiesId());
            SimpleType simpleType = simpleType(proto, additionalAnnotations);
            ProtoBuf.Type typeFlexibleUpperBound = ProtoTypeTableUtilKt.flexibleUpperBound(proto, this.c.getTypeTable());
            if (typeFlexibleUpperBound == null) {
                Intrinsics.throwNpe();
            }
            return this.c.getComponents().getFlexibleTypeDeserializer().create(proto, string, simpleType, simpleType(typeFlexibleUpperBound, additionalAnnotations));
        }
        return simpleType(proto, additionalAnnotations);
    }

    public static /* bridge */ /* synthetic */ SimpleType simpleType$default(TypeDeserializer typeDeserializer, ProtoBuf.Type type, Annotations annotations, int i, Object obj) {
        if ((i & 2) != 0) {
            annotations = Annotations.Companion.getEMPTY();
        }
        return typeDeserializer.simpleType(type, annotations);
    }

    public final SimpleType simpleType(final ProtoBuf.Type proto, final Annotations additionalAnnotations) {
        SimpleType simpleTypeComputeLocalClassifierReplacementType;
        SimpleType simpleType;
        Intrinsics.checkParameterIsNotNull(proto, "proto");
        Intrinsics.checkParameterIsNotNull(additionalAnnotations, "additionalAnnotations");
        if (proto.hasClassName()) {
            simpleTypeComputeLocalClassifierReplacementType = computeLocalClassifierReplacementType(proto.getClassName());
        } else {
            simpleTypeComputeLocalClassifierReplacementType = proto.hasTypeAliasName() ? computeLocalClassifierReplacementType(proto.getTypeAliasName()) : null;
        }
        if (simpleTypeComputeLocalClassifierReplacementType != null) {
            return simpleTypeComputeLocalClassifierReplacementType;
        }
        TypeConstructor typeConstructor = typeConstructor(proto);
        if (ErrorUtils.isError(typeConstructor.mo1333getDeclarationDescriptor())) {
            SimpleType simpleTypeCreateErrorTypeWithCustomConstructor = ErrorUtils.createErrorTypeWithCustomConstructor(typeConstructor.toString(), typeConstructor);
            Intrinsics.checkExpressionValueIsNotNull(simpleTypeCreateErrorTypeWithCustomConstructor, "ErrorUtils.createErrorTy….toString(), constructor)");
            return simpleTypeCreateErrorTypeWithCustomConstructor;
        }
        DeserializedAnnotationsWithPossibleTargets deserializedAnnotationsWithPossibleTargets = new DeserializedAnnotationsWithPossibleTargets(this.c.getStorageManager(), new Function0<List<? extends AnnotationWithTarget>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$simpleType$annotations$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends AnnotationWithTarget> invoke() {
                List<AnnotationDescriptor> listLoadTypeAnnotations = this.this$0.c.getComponents().getAnnotationAndConstantLoader().loadTypeAnnotations(proto, this.this$0.c.getNameResolver());
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listLoadTypeAnnotations, 10));
                Iterator<T> it = listLoadTypeAnnotations.iterator();
                while (it.hasNext()) {
                    arrayList.add(new AnnotationWithTarget((AnnotationDescriptor) it.next(), null));
                }
                return CollectionsKt.toList(CollectionsKt.plus((Collection) arrayList, (Iterable) additionalAnnotations.getAllAnnotations()));
            }
        });
        List<ProtoBuf.Type.Argument> listInvoke = new Function1<ProtoBuf.Type, List<? extends ProtoBuf.Type.Argument>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.simpleType.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<ProtoBuf.Type.Argument> invoke(ProtoBuf.Type receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                List<ProtoBuf.Type.Argument> argumentList = receiver.getArgumentList();
                Intrinsics.checkExpressionValueIsNotNull(argumentList, "argumentList");
                List<ProtoBuf.Type.Argument> list = argumentList;
                ProtoBuf.Type typeOuterType = ProtoTypeTableUtilKt.outerType(receiver, TypeDeserializer.this.c.getTypeTable());
                List<ProtoBuf.Type.Argument> listInvoke2 = typeOuterType != null ? invoke(typeOuterType) : null;
                if (listInvoke2 == null) {
                    listInvoke2 = CollectionsKt.emptyList();
                }
                return CollectionsKt.plus((Collection) list, (Iterable) listInvoke2);
            }
        }.invoke(proto);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listInvoke, 10));
        int i = 0;
        for (ProtoBuf.Type.Argument argument : listInvoke) {
            List<TypeParameterDescriptor> parameters = typeConstructor.getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "constructor.parameters");
            arrayList.add(typeArgument((TypeParameterDescriptor) CollectionsKt.getOrNull(parameters, i), argument));
            i++;
        }
        List<? extends TypeProjection> list = CollectionsKt.toList(arrayList);
        Boolean bool = Flags.SUSPEND_TYPE.get(proto.getFlags());
        Intrinsics.checkExpressionValueIsNotNull(bool, "Flags.SUSPEND_TYPE.get(proto.flags)");
        if (bool.booleanValue()) {
            simpleType = createSuspendFunctionType(deserializedAnnotationsWithPossibleTargets, typeConstructor, list, proto.getNullable());
        } else {
            simpleType = KotlinTypeFactory.simpleType(deserializedAnnotationsWithPossibleTargets, typeConstructor, list, proto.getNullable());
        }
        ProtoBuf.Type typeAbbreviatedType = ProtoTypeTableUtilKt.abbreviatedType(proto, this.c.getTypeTable());
        return typeAbbreviatedType != null ? SpecialTypesKt.withAbbreviation(simpleType, simpleType(typeAbbreviatedType, additionalAnnotations)) : simpleType;
    }

    private final TypeConstructor typeConstructor(ProtoBuf.Type type) {
        Object next;
        TypeConstructor typeConstructor;
        C01891 c01891 = new C01891(type);
        if (type.hasClassName()) {
            ClassDescriptor classDescriptorInvoke = this.classDescriptors.invoke(Integer.valueOf(type.getClassName()));
            if (classDescriptorInvoke == null) {
                classDescriptorInvoke = c01891.invoke(type.getClassName());
            }
            TypeConstructor typeConstructor2 = classDescriptorInvoke.getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(typeConstructor2, "(classDescriptors(proto.…assName)).typeConstructor");
            return typeConstructor2;
        }
        if (type.hasTypeParameter()) {
            TypeConstructor typeConstructorTypeParameterTypeConstructor = typeParameterTypeConstructor(type.getTypeParameter());
            if (typeConstructorTypeParameterTypeConstructor != null) {
                return typeConstructorTypeParameterTypeConstructor;
            }
            TypeConstructor typeConstructorCreateErrorTypeConstructor = ErrorUtils.createErrorTypeConstructor("Unknown type parameter " + type.getTypeParameter());
            Intrinsics.checkExpressionValueIsNotNull(typeConstructorCreateErrorTypeConstructor, "ErrorUtils.createErrorTy… ${proto.typeParameter}\")");
            return typeConstructorCreateErrorTypeConstructor;
        }
        if (type.hasTypeParameterName()) {
            DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
            String string = this.c.getNameResolver().getString(type.getTypeParameterName());
            Iterator<T> it = getOwnTypeParameters().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (Intrinsics.areEqual(((TypeParameterDescriptor) next).getName().asString(), string)) {
                    break;
                }
            }
            TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) next;
            if (typeParameterDescriptor != null && (typeConstructor = typeParameterDescriptor.getTypeConstructor()) != null) {
                return typeConstructor;
            }
            TypeConstructor typeConstructorCreateErrorTypeConstructor2 = ErrorUtils.createErrorTypeConstructor("Deserialized type parameter " + string + " in " + containingDeclaration);
            Intrinsics.checkExpressionValueIsNotNull(typeConstructorCreateErrorTypeConstructor2, "ErrorUtils.createErrorTy…ter $name in $container\")");
            return typeConstructorCreateErrorTypeConstructor2;
        }
        if (!type.hasTypeAliasName()) {
            TypeConstructor typeConstructorCreateErrorTypeConstructor3 = ErrorUtils.createErrorTypeConstructor("Unknown type");
            Intrinsics.checkExpressionValueIsNotNull(typeConstructorCreateErrorTypeConstructor3, "ErrorUtils.createErrorTy…nstructor(\"Unknown type\")");
            return typeConstructorCreateErrorTypeConstructor3;
        }
        ClassDescriptor classDescriptorInvoke2 = this.typeAliasDescriptors.invoke(Integer.valueOf(type.getTypeAliasName()));
        if (classDescriptorInvoke2 == null) {
            classDescriptorInvoke2 = c01891.invoke(type.getTypeAliasName());
        }
        TypeConstructor typeConstructor3 = classDescriptorInvoke2.getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor3, "(typeAliasDescriptors(pr…iasName)).typeConstructor");
        return typeConstructor3;
    }

    private final SimpleType createSuspendFunctionType(Annotations annotations, TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z) {
        int size;
        int size2 = typeConstructor.getParameters().size() - list.size();
        SimpleType simpleTypeCreateSuspendFunctionTypeForBasicCase = null;
        if (size2 == 0) {
            simpleTypeCreateSuspendFunctionTypeForBasicCase = createSuspendFunctionTypeForBasicCase(annotations, typeConstructor, list, z);
        } else if (size2 == 1 && (size = list.size() - 1) >= 0) {
            ClassDescriptor suspendFunction = typeConstructor.getBuiltIns().getSuspendFunction(size);
            Intrinsics.checkExpressionValueIsNotNull(suspendFunction, "functionTypeConstructor.…getSuspendFunction(arity)");
            TypeConstructor typeConstructor2 = suspendFunction.getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(typeConstructor2, "functionTypeConstructor.…on(arity).typeConstructor");
            simpleTypeCreateSuspendFunctionTypeForBasicCase = KotlinTypeFactory.simpleType(annotations, typeConstructor2, list, z);
        }
        if (simpleTypeCreateSuspendFunctionTypeForBasicCase != null) {
            return simpleTypeCreateSuspendFunctionTypeForBasicCase;
        }
        SimpleType simpleTypeCreateErrorTypeWithArguments = ErrorUtils.createErrorTypeWithArguments("Bad suspend function in metadata with constructor: " + typeConstructor, list);
        Intrinsics.checkExpressionValueIsNotNull(simpleTypeCreateErrorTypeWithArguments, "ErrorUtils.createErrorTy…      arguments\n        )");
        return simpleTypeCreateErrorTypeWithArguments;
    }

    private final SimpleType createSuspendFunctionTypeForBasicCase(Annotations annotations, TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z) {
        SimpleType simpleType = KotlinTypeFactory.simpleType(annotations, typeConstructor, list, z);
        if (FunctionTypesKt.isFunctionType(simpleType)) {
            return transformRuntimeFunctionTypeToSuspendFunction(simpleType);
        }
        return null;
    }

    private final SimpleType transformRuntimeFunctionTypeToSuspendFunction(KotlinType kotlinType) {
        KotlinType type;
        boolean releaseCoroutines = this.c.getComponents().getConfiguration().getReleaseCoroutines();
        TypeProjection typeProjection = (TypeProjection) CollectionsKt.lastOrNull((List) FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType));
        if (typeProjection == null || (type = typeProjection.getType()) == null) {
            return null;
        }
        ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = type.getConstructor().mo1333getDeclarationDescriptor();
        FqName fqNameSafe = classifierDescriptorMo1333getDeclarationDescriptor != null ? DescriptorUtilsKt.getFqNameSafe(classifierDescriptorMo1333getDeclarationDescriptor) : null;
        boolean z = true;
        if (type.getArguments().size() != 1 || (!SuspendFunctionTypesKt.isContinuation(fqNameSafe, true) && !SuspendFunctionTypesKt.isContinuation(fqNameSafe, false))) {
            return (SimpleType) kotlinType;
        }
        KotlinType suspendReturnType = ((TypeProjection) CollectionsKt.single((List) type.getArguments())).getType();
        DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
        if (!(containingDeclaration instanceof CallableDescriptor)) {
            containingDeclaration = null;
        }
        CallableDescriptor callableDescriptor = (CallableDescriptor) containingDeclaration;
        if (Intrinsics.areEqual(callableDescriptor != null ? DescriptorUtilsKt.fqNameOrNull(callableDescriptor) : null, SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME)) {
            Intrinsics.checkExpressionValueIsNotNull(suspendReturnType, "suspendReturnType");
            return createSimpleSuspendFunctionType(kotlinType, suspendReturnType);
        }
        if (!this.experimentalSuspendFunctionTypeEncountered && (!releaseCoroutines || !SuspendFunctionTypesKt.isContinuation(fqNameSafe, !releaseCoroutines))) {
            z = false;
        }
        this.experimentalSuspendFunctionTypeEncountered = z;
        Intrinsics.checkExpressionValueIsNotNull(suspendReturnType, "suspendReturnType");
        return createSimpleSuspendFunctionType(kotlinType, suspendReturnType);
    }

    private final SimpleType createSimpleSuspendFunctionType(KotlinType kotlinType, KotlinType kotlinType2) {
        KotlinBuiltIns builtIns = TypeUtilsKt.getBuiltIns(kotlinType);
        Annotations annotations = kotlinType.getAnnotations();
        KotlinType receiverTypeFromFunctionType = FunctionTypesKt.getReceiverTypeFromFunctionType(kotlinType);
        List listDropLast = CollectionsKt.dropLast(FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType), 1);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDropLast, 10));
        Iterator it = listDropLast.iterator();
        while (it.hasNext()) {
            arrayList.add(((TypeProjection) it.next()).getType());
        }
        return FunctionTypesKt.createFunctionType(builtIns, annotations, receiverTypeFromFunctionType, arrayList, null, kotlinType2, true).makeNullableAsSpecified(kotlinType.isMarkedNullable());
    }

    private final TypeConstructor typeParameterTypeConstructor(int i) {
        TypeConstructor typeConstructor;
        TypeParameterDescriptor typeParameterDescriptor = this.typeParameterDescriptors.get(Integer.valueOf(i));
        if (typeParameterDescriptor != null && (typeConstructor = typeParameterDescriptor.getTypeConstructor()) != null) {
            return typeConstructor;
        }
        TypeDeserializer typeDeserializer = this.parent;
        if (typeDeserializer != null) {
            return typeDeserializer.typeParameterTypeConstructor(i);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassDescriptor computeClassDescriptor(int i) {
        ClassId classId = NameResolverUtilKt.getClassId(this.c.getNameResolver(), i);
        if (classId.isLocal()) {
            return this.c.getComponents().deserializeClass(classId);
        }
        return FindClassInModuleKt.findClassAcrossModuleDependencies(this.c.getComponents().getModuleDescriptor(), classId);
    }

    private final SimpleType computeLocalClassifierReplacementType(int i) {
        if (NameResolverUtilKt.getClassId(this.c.getNameResolver(), i).isLocal()) {
            return this.c.getComponents().getLocalClassifierTypeSettings().getReplacementTypeForLocalClassifiers();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassifierDescriptor computeTypeAliasDescriptor(int i) {
        ClassId classId = NameResolverUtilKt.getClassId(this.c.getNameResolver(), i);
        if (classId.isLocal()) {
            return null;
        }
        return FindClassInModuleKt.findTypeAliasAcrossModuleDependencies(this.c.getComponents().getModuleDescriptor(), classId);
    }

    private final TypeProjection typeArgument(TypeParameterDescriptor typeParameterDescriptor, ProtoBuf.Type.Argument argument) {
        if (argument.getProjection() == ProtoBuf.Type.Argument.Projection.STAR) {
            if (typeParameterDescriptor == null) {
                SimpleType nullableAnyType = this.c.getComponents().getModuleDescriptor().getBuiltIns().getNullableAnyType();
                Intrinsics.checkExpressionValueIsNotNull(nullableAnyType, "c.components.moduleDescr….builtIns.nullableAnyType");
                return new TypeBasedStarProjectionImpl(nullableAnyType);
            }
            return new StarProjectionImpl(typeParameterDescriptor);
        }
        ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
        ProtoBuf.Type.Argument.Projection projection = argument.getProjection();
        Intrinsics.checkExpressionValueIsNotNull(projection, "typeArgumentProto.projection");
        Variance variance = protoEnumFlags.variance(projection);
        ProtoBuf.Type type = ProtoTypeTableUtilKt.type(argument, this.c.getTypeTable());
        if (type == null) {
            return new TypeProjectionImpl(ErrorUtils.createErrorType("No type recorded"));
        }
        return new TypeProjectionImpl(variance, type$default(this, type, null, 2, null));
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.debugName);
        if (this.parent == null) {
            str = "";
        } else {
            str = ". Child of " + this.parent.debugName;
        }
        sb.append(str);
        return sb.toString();
    }
}

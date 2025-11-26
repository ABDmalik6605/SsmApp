package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.BooleanValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ByteValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.CharValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.DoubleValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.FloatValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.LongValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ShortValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UByteValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UIntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ULongValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UShortValue;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* compiled from: AnnotationDeserializer.kt */
/* loaded from: classes2.dex */
public final class AnnotationDeserializer {
    private final ModuleDescriptor module;
    private final NotFoundClasses notFoundClasses;

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ProtoBuf.Annotation.Argument.Value.Type.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.BYTE.ordinal()] = 1;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.CHAR.ordinal()] = 2;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.SHORT.ordinal()] = 3;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.INT.ordinal()] = 4;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.LONG.ordinal()] = 5;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.FLOAT.ordinal()] = 6;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.DOUBLE.ordinal()] = 7;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.BOOLEAN.ordinal()] = 8;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.STRING.ordinal()] = 9;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.CLASS.ordinal()] = 10;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.ENUM.ordinal()] = 11;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.ANNOTATION.ordinal()] = 12;
            iArr[ProtoBuf.Annotation.Argument.Value.Type.ARRAY.ordinal()] = 13;
            int[] iArr2 = new int[ProtoBuf.Annotation.Argument.Value.Type.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[ProtoBuf.Annotation.Argument.Value.Type.BYTE.ordinal()] = 1;
            iArr2[ProtoBuf.Annotation.Argument.Value.Type.CHAR.ordinal()] = 2;
            iArr2[ProtoBuf.Annotation.Argument.Value.Type.SHORT.ordinal()] = 3;
            iArr2[ProtoBuf.Annotation.Argument.Value.Type.INT.ordinal()] = 4;
            iArr2[ProtoBuf.Annotation.Argument.Value.Type.LONG.ordinal()] = 5;
            iArr2[ProtoBuf.Annotation.Argument.Value.Type.FLOAT.ordinal()] = 6;
            iArr2[ProtoBuf.Annotation.Argument.Value.Type.DOUBLE.ordinal()] = 7;
            iArr2[ProtoBuf.Annotation.Argument.Value.Type.BOOLEAN.ordinal()] = 8;
            iArr2[ProtoBuf.Annotation.Argument.Value.Type.STRING.ordinal()] = 9;
            iArr2[ProtoBuf.Annotation.Argument.Value.Type.CLASS.ordinal()] = 10;
            iArr2[ProtoBuf.Annotation.Argument.Value.Type.ENUM.ordinal()] = 11;
            iArr2[ProtoBuf.Annotation.Argument.Value.Type.ANNOTATION.ordinal()] = 12;
            iArr2[ProtoBuf.Annotation.Argument.Value.Type.ARRAY.ordinal()] = 13;
        }
    }

    public AnnotationDeserializer(ModuleDescriptor module, NotFoundClasses notFoundClasses) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        Intrinsics.checkParameterIsNotNull(notFoundClasses, "notFoundClasses");
        this.module = module;
        this.notFoundClasses = notFoundClasses;
    }

    private final KotlinBuiltIns getBuiltIns() {
        return this.module.getBuiltIns();
    }

    public final AnnotationDescriptor deserializeAnnotation(ProtoBuf.Annotation proto, NameResolver nameResolver) {
        Intrinsics.checkParameterIsNotNull(proto, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        ClassDescriptor classDescriptorResolveClass = resolveClass(NameResolverUtilKt.getClassId(nameResolver, proto.getId()));
        Map mapEmptyMap = MapsKt.emptyMap();
        if (proto.getArgumentCount() != 0) {
            ClassDescriptor classDescriptor = classDescriptorResolveClass;
            if (!ErrorUtils.isError(classDescriptor) && DescriptorUtils.isAnnotationClass(classDescriptor)) {
                Collection<ClassConstructorDescriptor> constructors = classDescriptorResolveClass.getConstructors();
                Intrinsics.checkExpressionValueIsNotNull(constructors, "annotationClass.constructors");
                ClassConstructorDescriptor classConstructorDescriptor = (ClassConstructorDescriptor) CollectionsKt.singleOrNull(constructors);
                if (classConstructorDescriptor != null) {
                    List<ValueParameterDescriptor> valueParameters = classConstructorDescriptor.getValueParameters();
                    Intrinsics.checkExpressionValueIsNotNull(valueParameters, "constructor.valueParameters");
                    List<ValueParameterDescriptor> list = valueParameters;
                    LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
                    for (Object obj : list) {
                        ValueParameterDescriptor it = (ValueParameterDescriptor) obj;
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        linkedHashMap.put(it.getName(), obj);
                    }
                    List<ProtoBuf.Annotation.Argument> argumentList = proto.getArgumentList();
                    Intrinsics.checkExpressionValueIsNotNull(argumentList, "proto.argumentList");
                    ArrayList arrayList = new ArrayList();
                    for (ProtoBuf.Annotation.Argument it2 : argumentList) {
                        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                        Pair<Name, ConstantValue<?>> pairResolveArgument = resolveArgument(it2, linkedHashMap, nameResolver);
                        if (pairResolveArgument != null) {
                            arrayList.add(pairResolveArgument);
                        }
                    }
                    mapEmptyMap = MapsKt.toMap(arrayList);
                }
            }
        }
        return new AnnotationDescriptorImpl(classDescriptorResolveClass.getDefaultType(), mapEmptyMap, SourceElement.NO_SOURCE);
    }

    private final Pair<Name, ConstantValue<?>> resolveArgument(ProtoBuf.Annotation.Argument argument, Map<Name, ? extends ValueParameterDescriptor> map, NameResolver nameResolver) {
        ValueParameterDescriptor valueParameterDescriptor = map.get(NameResolverUtilKt.getName(nameResolver, argument.getNameId()));
        if (valueParameterDescriptor == null) {
            return null;
        }
        Name name = NameResolverUtilKt.getName(nameResolver, argument.getNameId());
        KotlinType type = valueParameterDescriptor.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "parameter.type");
        ProtoBuf.Annotation.Argument.Value value = argument.getValue();
        Intrinsics.checkExpressionValueIsNotNull(value, "proto.value");
        return new Pair<>(name, resolveValue(type, value, nameResolver));
    }

    public final ConstantValue<?> resolveValue(KotlinType expectedType, ProtoBuf.Annotation.Argument.Value value, NameResolver nameResolver) {
        ConstantValue<?> uByteValue;
        SimpleType simpleType;
        Intrinsics.checkParameterIsNotNull(expectedType, "expectedType");
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Boolean isUnsigned = Flags.IS_UNSIGNED.get(value.getFlags());
        ProtoBuf.Annotation.Argument.Value.Type type = value.getType();
        if (type != null) {
            switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                case 1:
                    byte intValue = (byte) value.getIntValue();
                    Intrinsics.checkExpressionValueIsNotNull(isUnsigned, "isUnsigned");
                    uByteValue = isUnsigned.booleanValue() ? new UByteValue(intValue) : new ByteValue(intValue);
                    break;
                case 2:
                    uByteValue = new CharValue((char) value.getIntValue());
                    break;
                case 3:
                    short intValue2 = (short) value.getIntValue();
                    Intrinsics.checkExpressionValueIsNotNull(isUnsigned, "isUnsigned");
                    uByteValue = isUnsigned.booleanValue() ? new UShortValue(intValue2) : new ShortValue(intValue2);
                    break;
                case 4:
                    int intValue3 = (int) value.getIntValue();
                    Intrinsics.checkExpressionValueIsNotNull(isUnsigned, "isUnsigned");
                    uByteValue = isUnsigned.booleanValue() ? new UIntValue(intValue3) : new IntValue(intValue3);
                    break;
                case 5:
                    long intValue4 = value.getIntValue();
                    Intrinsics.checkExpressionValueIsNotNull(isUnsigned, "isUnsigned");
                    uByteValue = isUnsigned.booleanValue() ? new ULongValue(intValue4) : new LongValue(intValue4);
                    break;
                case 6:
                    uByteValue = new FloatValue(value.getFloatValue());
                    break;
                case 7:
                    uByteValue = new DoubleValue(value.getDoubleValue());
                    break;
                case 8:
                    uByteValue = new BooleanValue(value.getIntValue() != 0);
                    break;
                case 9:
                    uByteValue = new StringValue(nameResolver.getString(value.getStringValue()));
                    break;
                case 10:
                    uByteValue = resolveClassLiteralValue(NameResolverUtilKt.getClassId(nameResolver, value.getClassId()));
                    break;
                case 11:
                    uByteValue = new EnumValue(NameResolverUtilKt.getClassId(nameResolver, value.getClassId()), NameResolverUtilKt.getName(nameResolver, value.getEnumValueId()));
                    break;
                case 12:
                    ProtoBuf.Annotation annotation = value.getAnnotation();
                    Intrinsics.checkExpressionValueIsNotNull(annotation, "value.annotation");
                    uByteValue = new AnnotationValue(deserializeAnnotation(annotation, nameResolver));
                    break;
                case 13:
                    boolean z = KotlinBuiltIns.isArray(expectedType) || KotlinBuiltIns.isPrimitiveArray(expectedType);
                    List<ProtoBuf.Annotation.Argument.Value> arrayElements = value.getArrayElementList();
                    Intrinsics.checkExpressionValueIsNotNull(arrayElements, "arrayElements");
                    if (!arrayElements.isEmpty()) {
                        Object objFirst = CollectionsKt.first((List<? extends Object>) arrayElements);
                        Intrinsics.checkExpressionValueIsNotNull(objFirst, "arrayElements.first()");
                        SimpleType simpleTypeResolveArrayElementType = resolveArrayElementType((ProtoBuf.Annotation.Argument.Value) objFirst, nameResolver);
                        SimpleType primitiveArrayKotlinTypeByPrimitiveKotlinType = getBuiltIns().getPrimitiveArrayKotlinTypeByPrimitiveKotlinType(simpleTypeResolveArrayElementType);
                        if (primitiveArrayKotlinTypeByPrimitiveKotlinType != null) {
                            simpleType = primitiveArrayKotlinTypeByPrimitiveKotlinType;
                        } else {
                            SimpleType arrayType = getBuiltIns().getArrayType(Variance.INVARIANT, simpleTypeResolveArrayElementType);
                            Intrinsics.checkExpressionValueIsNotNull(arrayType, "builtIns.getArrayType(Va…RIANT, actualElementType)");
                            simpleType = arrayType;
                        }
                    } else if (z) {
                        simpleType = expectedType;
                    } else {
                        SimpleType arrayType2 = getBuiltIns().getArrayType(Variance.INVARIANT, getBuiltIns().getAnyType());
                        Intrinsics.checkExpressionValueIsNotNull(arrayType2, "builtIns.getArrayType(Va…ARIANT, builtIns.anyType)");
                        simpleType = arrayType2;
                    }
                    KotlinType expectedElementType = getBuiltIns().getArrayElementType(z ? expectedType : simpleType);
                    ConstantValueFactory constantValueFactory = ConstantValueFactory.INSTANCE;
                    List<ProtoBuf.Annotation.Argument.Value> list = arrayElements;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    for (ProtoBuf.Annotation.Argument.Value it : list) {
                        Intrinsics.checkExpressionValueIsNotNull(expectedElementType, "expectedElementType");
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        arrayList.add(resolveValue(expectedElementType, it, nameResolver));
                    }
                    uByteValue = constantValueFactory.createArrayValue(arrayList, simpleType);
                    break;
            }
            return TypeUtilsKt.isSubtypeOf(uByteValue.getType(this.module), expectedType) ? uByteValue : ErrorValue.Companion.create("Unexpected argument value");
        }
        throw new IllegalStateException(("Unsupported annotation argument type: " + value.getType() + " (expected " + expectedType + ')').toString());
    }

    private final ConstantValue<?> resolveClassLiteralValue(ClassId classId) {
        SimpleType defaultType = resolveClass(classId).getDefaultType();
        Intrinsics.checkExpressionValueIsNotNull(defaultType, "resolveClass(classId).defaultType");
        KotlinType kotlinTypeReplaceArgumentsWithStarProjections = TypeUtilsKt.replaceArgumentsWithStarProjections(defaultType);
        ClassId classId2 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.kClass.toSafe());
        Intrinsics.checkExpressionValueIsNotNull(classId2, "ClassId.topLevel(KotlinB…FQ_NAMES.kClass.toSafe())");
        return new KClassValue(KotlinTypeFactory.simpleNotNullType(Annotations.Companion.getEMPTY(), resolveClass(classId2), CollectionsKt.listOf(new TypeProjectionImpl(kotlinTypeReplaceArgumentsWithStarProjections))));
    }

    private final SimpleType resolveArrayElementType(ProtoBuf.Annotation.Argument.Value value, NameResolver nameResolver) {
        KotlinBuiltIns builtIns = getBuiltIns();
        ProtoBuf.Annotation.Argument.Value.Type type = value.getType();
        if (type != null) {
            switch (WhenMappings.$EnumSwitchMapping$1[type.ordinal()]) {
                case 1:
                    SimpleType byteType = builtIns.getByteType();
                    Intrinsics.checkExpressionValueIsNotNull(byteType, "byteType");
                    return byteType;
                case 2:
                    SimpleType charType = builtIns.getCharType();
                    Intrinsics.checkExpressionValueIsNotNull(charType, "charType");
                    return charType;
                case 3:
                    SimpleType shortType = builtIns.getShortType();
                    Intrinsics.checkExpressionValueIsNotNull(shortType, "shortType");
                    return shortType;
                case 4:
                    SimpleType intType = builtIns.getIntType();
                    Intrinsics.checkExpressionValueIsNotNull(intType, "intType");
                    return intType;
                case 5:
                    SimpleType longType = builtIns.getLongType();
                    Intrinsics.checkExpressionValueIsNotNull(longType, "longType");
                    return longType;
                case 6:
                    SimpleType floatType = builtIns.getFloatType();
                    Intrinsics.checkExpressionValueIsNotNull(floatType, "floatType");
                    return floatType;
                case 7:
                    SimpleType doubleType = builtIns.getDoubleType();
                    Intrinsics.checkExpressionValueIsNotNull(doubleType, "doubleType");
                    return doubleType;
                case 8:
                    SimpleType booleanType = builtIns.getBooleanType();
                    Intrinsics.checkExpressionValueIsNotNull(booleanType, "booleanType");
                    return booleanType;
                case 9:
                    SimpleType stringType = builtIns.getStringType();
                    Intrinsics.checkExpressionValueIsNotNull(stringType, "stringType");
                    return stringType;
                case 10:
                    throw new IllegalStateException("Arrays of class literals are not supported yet".toString());
                case 11:
                    SimpleType defaultType = resolveClass(NameResolverUtilKt.getClassId(nameResolver, value.getClassId())).getDefaultType();
                    Intrinsics.checkExpressionValueIsNotNull(defaultType, "resolveClass(nameResolve…lue.classId)).defaultType");
                    return defaultType;
                case 12:
                    ProtoBuf.Annotation annotation = value.getAnnotation();
                    Intrinsics.checkExpressionValueIsNotNull(annotation, "value.annotation");
                    SimpleType defaultType2 = resolveClass(NameResolverUtilKt.getClassId(nameResolver, annotation.getId())).getDefaultType();
                    Intrinsics.checkExpressionValueIsNotNull(defaultType2, "resolveClass(nameResolve…notation.id)).defaultType");
                    return defaultType2;
                case 13:
                    throw new IllegalStateException("Array of arrays is impossible".toString());
            }
        }
        throw new IllegalStateException(("Unknown type: " + value.getType()).toString());
    }

    private final ClassDescriptor resolveClass(ClassId classId) {
        return FindClassInModuleKt.findNonGenericClassAcrossDependencies(this.module, classId, this.notFoundClasses);
    }
}

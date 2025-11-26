package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsProtoBuf;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.serialization.SerializerExtensionProtocol;
import kotlin.text.StringsKt;
import org.apache.commons.lang.ClassUtils;

/* compiled from: BuiltInSerializerProtocol.kt */
/* loaded from: classes2.dex */
public final class BuiltInSerializerProtocol extends SerializerExtensionProtocol {
    public static final BuiltInSerializerProtocol INSTANCE = new BuiltInSerializerProtocol();

    /* JADX WARN: Illegal instructions before constructor call */
    private BuiltInSerializerProtocol() {
        ExtensionRegistryLite extensionRegistryLiteNewInstance = ExtensionRegistryLite.newInstance();
        BuiltInsProtoBuf.registerAllExtensions(extensionRegistryLiteNewInstance);
        Intrinsics.checkExpressionValueIsNotNull(extensionRegistryLiteNewInstance, "ExtensionRegistryLite.ne…sterAllExtensions(this) }");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Package, Integer> generatedExtension = BuiltInsProtoBuf.packageFqName;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension, "BuiltInsProtoBuf.packageFqName");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Constructor, List<ProtoBuf.Annotation>> generatedExtension2 = BuiltInsProtoBuf.constructorAnnotation;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension2, "BuiltInsProtoBuf.constructorAnnotation");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Class, List<ProtoBuf.Annotation>> generatedExtension3 = BuiltInsProtoBuf.classAnnotation;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension3, "BuiltInsProtoBuf.classAnnotation");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Function, List<ProtoBuf.Annotation>> generatedExtension4 = BuiltInsProtoBuf.functionAnnotation;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension4, "BuiltInsProtoBuf.functionAnnotation");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, List<ProtoBuf.Annotation>> generatedExtension5 = BuiltInsProtoBuf.propertyAnnotation;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension5, "BuiltInsProtoBuf.propertyAnnotation");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.EnumEntry, List<ProtoBuf.Annotation>> generatedExtension6 = BuiltInsProtoBuf.enumEntryAnnotation;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension6, "BuiltInsProtoBuf.enumEntryAnnotation");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, ProtoBuf.Annotation.Argument.Value> generatedExtension7 = BuiltInsProtoBuf.compileTimeValue;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension7, "BuiltInsProtoBuf.compileTimeValue");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.ValueParameter, List<ProtoBuf.Annotation>> generatedExtension8 = BuiltInsProtoBuf.parameterAnnotation;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension8, "BuiltInsProtoBuf.parameterAnnotation");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Type, List<ProtoBuf.Annotation>> generatedExtension9 = BuiltInsProtoBuf.typeAnnotation;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension9, "BuiltInsProtoBuf.typeAnnotation");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.TypeParameter, List<ProtoBuf.Annotation>> generatedExtension10 = BuiltInsProtoBuf.typeParameterAnnotation;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension10, "BuiltInsProtoBuf.typeParameterAnnotation");
        super(extensionRegistryLiteNewInstance, generatedExtension, generatedExtension2, generatedExtension3, generatedExtension4, generatedExtension5, generatedExtension6, generatedExtension7, generatedExtension8, generatedExtension9, generatedExtension10);
    }

    public final String getBuiltInsFilePath(FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        StringBuilder sb = new StringBuilder();
        String strAsString = fqName.asString();
        Intrinsics.checkExpressionValueIsNotNull(strAsString, "fqName.asString()");
        sb.append(StringsKt.replace$default(strAsString, ClassUtils.PACKAGE_SEPARATOR_CHAR, '/', false, 4, (Object) null));
        sb.append("/");
        sb.append(getBuiltInsFileName(fqName));
        return sb.toString();
    }

    public final String getBuiltInsFileName(FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return shortName(fqName) + ".kotlin_builtins";
    }

    private final String shortName(FqName fqName) {
        if (fqName.isRoot()) {
            return "default-package";
        }
        String strAsString = fqName.shortName().asString();
        Intrinsics.checkExpressionValueIsNotNull(strAsString, "fqName.shortName().asString()");
        return strAsString;
    }
}

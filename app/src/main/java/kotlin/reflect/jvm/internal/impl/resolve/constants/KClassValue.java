package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;

/* compiled from: constantValues.kt */
/* loaded from: classes2.dex */
public final class KClassValue extends ConstantValue<KotlinType> {
    private final KotlinType type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KClassValue(KotlinType type) {
        super(type);
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.type = type;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public KotlinType getType(ModuleDescriptor module) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        return this.type;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public KotlinType getValue() {
        KotlinType type = ((TypeProjection) CollectionsKt.single((List) this.type.getArguments())).getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "type.arguments.single().type");
        return type;
    }
}

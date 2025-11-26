package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KClassImpl;

/* compiled from: KClassImpl.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lkotlin/reflect/jvm/internal/KTypeImpl;", ExifInterface.GPS_DIRECTION_TRUE, "", "invoke"}, k = 3, mv = {1, 1, 11})
/* loaded from: classes2.dex */
final class KClassImpl$Data$supertypes$2 extends Lambda implements Function0<List<? extends KTypeImpl>> {
    final /* synthetic */ KClassImpl.Data this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    KClassImpl$Data$supertypes$2(KClassImpl.Data data) {
        super(0);
        this.this$0 = data;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x009f  */
    @Override // kotlin.jvm.functions.Function0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<? extends kotlin.reflect.jvm.internal.KTypeImpl> invoke() {
        /*
            r7 = this;
            kotlin.reflect.jvm.internal.KClassImpl$Data r0 = r7.this$0
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r0 = r0.getDescriptor()
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r0 = r0.getTypeConstructor()
            java.lang.String r1 = "descriptor.typeConstructor"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.util.Collection r0 = r0.getSupertypes()
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.size()
            r1.<init>(r2)
            java.lang.String r2 = "kotlinTypes"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L27:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L4b
            java.lang.Object r2 = r0.next()
            r3 = r1
            java.util.Collection r3 = (java.util.Collection) r3
            kotlin.reflect.jvm.internal.impl.types.KotlinType r2 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r2
            kotlin.reflect.jvm.internal.KTypeImpl r4 = new kotlin.reflect.jvm.internal.KTypeImpl
            java.lang.String r5 = "kotlinType"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r5)
            kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$$special$$inlined$mapTo$lambda$1 r5 = new kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$$special$$inlined$mapTo$lambda$1
            r5.<init>()
            kotlin.jvm.functions.Function0 r5 = (kotlin.jvm.functions.Function0) r5
            r4.<init>(r2, r5)
            r3.add(r4)
            goto L27
        L4b:
            r0 = r1
            java.util.Collection r0 = (java.util.Collection) r0
            kotlin.reflect.jvm.internal.KClassImpl$Data r2 = r7.this$0
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r2 = r2.getDescriptor()
            boolean r2 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isSpecialClassWithNoSupertypes(r2)
            if (r2 != 0) goto Lc2
            r2 = r1
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            boolean r3 = r2 instanceof java.util.Collection
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L6e
            r3 = r2
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L6e
        L6c:
            r4 = 1
            goto L9d
        L6e:
            java.util.Iterator r2 = r2.iterator()
        L72:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L6c
            java.lang.Object r3 = r2.next()
            kotlin.reflect.jvm.internal.KTypeImpl r3 = (kotlin.reflect.jvm.internal.KTypeImpl) r3
            kotlin.reflect.jvm.internal.impl.types.KotlinType r3 = r3.getType()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r3 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils.getClassDescriptorForType(r3)
            java.lang.String r6 = "DescriptorUtils.getClassDescriptorForType(it.type)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r6)
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r3 = r3.getKind()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r6 = kotlin.reflect.jvm.internal.impl.descriptors.ClassKind.INTERFACE
            if (r3 == r6) goto L9a
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r6 = kotlin.reflect.jvm.internal.impl.descriptors.ClassKind.ANNOTATION_CLASS
            if (r3 != r6) goto L98
            goto L9a
        L98:
            r3 = 0
            goto L9b
        L9a:
            r3 = 1
        L9b:
            if (r3 != 0) goto L72
        L9d:
            if (r4 == 0) goto Lc2
            kotlin.reflect.jvm.internal.KTypeImpl r2 = new kotlin.reflect.jvm.internal.KTypeImpl
            kotlin.reflect.jvm.internal.KClassImpl$Data r3 = r7.this$0
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r3 = r3.getDescriptor()
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r3 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r3
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r3 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.getBuiltIns(r3)
            kotlin.reflect.jvm.internal.impl.types.SimpleType r3 = r3.getAnyType()
            java.lang.String r4 = "descriptor.builtIns.anyType"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r3 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r3
            kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$3 r4 = new kotlin.jvm.functions.Function0<java.lang.Class<java.lang.Object>>() { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.3
                static {
                    /*
                        kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$3 r0 = new kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$3
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$3) kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.3.INSTANCE kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$3
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.AnonymousClass3.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 0
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.AnonymousClass3.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ java.lang.Class<java.lang.Object> invoke() {
                    /*
                        r1 = this;
                        java.lang.Class r0 = r1.invoke()
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.AnonymousClass3.invoke():java.lang.Object");
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final java.lang.Class<java.lang.Object> invoke() {
                    /*
                        r1 = this;
                        java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.AnonymousClass3.invoke():java.lang.Class");
                }
            }
            kotlin.jvm.functions.Function0 r4 = (kotlin.jvm.functions.Function0) r4
            r2.<init>(r3, r4)
            r0.add(r2)
        Lc2:
            java.util.List r0 = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.invoke():java.util.List");
    }
}

package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Map;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EnumEntrySyntheticClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;

/* compiled from: LazyJavaClassMemberScope.kt */
/* loaded from: classes2.dex */
final class LazyJavaClassMemberScope$nestedClasses$1 extends Lambda implements Function1<Name, ClassDescriptorBase> {
    final /* synthetic */ LazyJavaResolverContext $c;
    final /* synthetic */ LazyJavaClassMemberScope this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LazyJavaClassMemberScope$nestedClasses$1(LazyJavaClassMemberScope lazyJavaClassMemberScope, LazyJavaResolverContext lazyJavaResolverContext) {
        super(1);
        this.this$0 = lazyJavaClassMemberScope;
        this.$c = lazyJavaResolverContext;
    }

    @Override // kotlin.jvm.functions.Function1
    public final ClassDescriptorBase invoke(Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        DeclarationDescriptorWithSource lazyJavaClassDescriptor = null;
        if (!((Set) this.this$0.nestedClassIndex.invoke()).contains(name)) {
            JavaField javaField = (JavaField) ((Map) this.this$0.enumEntryIndex.invoke()).get(name);
            if (javaField != null) {
                lazyJavaClassDescriptor = EnumEntrySyntheticClassDescriptor.create(this.$c.getStorageManager(), this.this$0.getOwnerDescriptor(), name, this.$c.getStorageManager().createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$nestedClasses$1$enumMemberNames$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Set<? extends Name> invoke() {
                        return SetsKt.plus((Set) this.this$0.this$0.getFunctionNames(), (Iterable) this.this$0.this$0.getVariableNames());
                    }
                }), LazyJavaAnnotationsKt.resolveAnnotations(this.$c, javaField), this.$c.getComponents().getSourceElementFactory().source(javaField));
            }
            return (ClassDescriptorBase) lazyJavaClassDescriptor;
        }
        JavaClassFinder finder = this.$c.getComponents().getFinder();
        ClassId classId = DescriptorUtilsKt.getClassId(this.this$0.getOwnerDescriptor());
        if (classId == null) {
            Intrinsics.throwNpe();
        }
        JavaClass it = finder.findClass(classId.createNestedClassId(name));
        if (it != null) {
            LazyJavaResolverContext lazyJavaResolverContext = this.$c;
            ClassDescriptor ownerDescriptor = this.this$0.getOwnerDescriptor();
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            lazyJavaClassDescriptor = new LazyJavaClassDescriptor(lazyJavaResolverContext, ownerDescriptor, it, null, 8, null);
            this.$c.getComponents().getJavaClassesTracker().reportClass((JavaClassDescriptor) lazyJavaClassDescriptor);
        }
        return (ClassDescriptorBase) lazyJavaClassDescriptor;
    }
}

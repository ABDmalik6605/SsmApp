package kotlin.reflect.jvm.internal;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FunctionCaller.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u001f\b \u0018\u0000 /*\f\b\u0000\u0010\u0001 \u0001*\u0004\u0018\u00010\u00022\u00020\u0003:\u001b#$%&'()*+,-./0123456789:;<=B1\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n¢\u0006\u0002\u0010\u000bJ\u001b\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\nH&¢\u0006\u0002\u0010\u001dJ\u0019\u0010\u001e\u001a\u00020\u001f2\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\nH\u0014¢\u0006\u0002\u0010 J\u0012\u0010!\u001a\u00020\u001f2\b\u0010\"\u001a\u0004\u0018\u00010\u0003H\u0004R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0004\u001a\u00028\u0000X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006>"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller;", "M", "Ljava/lang/reflect/Member;", "", "member", "returnType", "Ljava/lang/reflect/Type;", "instanceClass", "Ljava/lang/Class;", "valueParameterTypes", "", "(Ljava/lang/reflect/Member;Ljava/lang/reflect/Type;Ljava/lang/Class;[Ljava/lang/reflect/Type;)V", "arity", "", "getArity", "()I", "getInstanceClass$kotlin_reflect_api", "()Ljava/lang/Class;", "getMember$kotlin_reflect_api", "()Ljava/lang/reflect/Member;", "Ljava/lang/reflect/Member;", "parameterTypes", "", "getParameterTypes", "()Ljava/util/List;", "getReturnType$kotlin_reflect_api", "()Ljava/lang/reflect/Type;", NotificationCompat.CATEGORY_CALL, "args", "([Ljava/lang/Object;)Ljava/lang/Object;", "checkArguments", "", "([Ljava/lang/Object;)V", "checkObjectInstance", "obj", "BoundClassCompanionFieldGetter", "BoundClassCompanionFieldSetter", "BoundConstructor", "BoundInstanceFieldGetter", "BoundInstanceFieldSetter", "BoundInstanceMethod", "BoundJvmStaticInObject", "BoundJvmStaticInObjectFieldGetter", "BoundJvmStaticInObjectFieldSetter", "BoundStaticMethod", "ClassCompanionFieldGetter", "ClassCompanionFieldSetter", "Companion", "Constructor", "FieldGetter", "FieldSetter", "InstanceFieldGetter", "InstanceFieldSetter", "InstanceMethod", "JvmStaticInObject", "JvmStaticInObjectFieldGetter", "JvmStaticInObjectFieldSetter", "Method", "StaticFieldGetter", "StaticFieldSetter", "StaticMethod", "ThrowingCaller", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
/* loaded from: classes2.dex */
public abstract class FunctionCaller<M extends Member> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Class<?> instanceClass;
    private final M member;
    private final List<Type> parameterTypes;
    private final Type returnType;

    public abstract Object call(Object[] args);

    /* JADX WARN: Removed duplicated region for block: B:7:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public FunctionCaller(M r2, java.lang.reflect.Type r3, java.lang.Class<?> r4, java.lang.reflect.Type[] r5) {
        /*
            r1 = this;
            java.lang.String r0 = "returnType"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            java.lang.String r0 = "valueParameterTypes"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            r1.<init>()
            r1.member = r2
            r1.returnType = r3
            r1.instanceClass = r4
            if (r4 == 0) goto L36
            kotlin.jvm.internal.SpreadBuilder r2 = new kotlin.jvm.internal.SpreadBuilder
            r3 = 2
            r2.<init>(r3)
            java.lang.reflect.Type r4 = (java.lang.reflect.Type) r4
            r2.add(r4)
            r2.addSpread(r5)
            int r3 = r2.size()
            java.lang.reflect.Type[] r3 = new java.lang.reflect.Type[r3]
            java.lang.Object[] r2 = r2.toArray(r3)
            java.lang.reflect.Type[] r2 = (java.lang.reflect.Type[]) r2
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            if (r2 == 0) goto L36
            goto L3a
        L36:
            java.util.List r2 = kotlin.collections.ArraysKt.toList(r5)
        L3a:
            r1.parameterTypes = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.FunctionCaller.<init>(java.lang.reflect.Member, java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type[]):void");
    }

    public final M getMember$kotlin_reflect_api() {
        return this.member;
    }

    /* renamed from: getReturnType$kotlin_reflect_api, reason: from getter */
    public final Type getReturnType() {
        return this.returnType;
    }

    public final Class<?> getInstanceClass$kotlin_reflect_api() {
        return this.instanceClass;
    }

    public final List<Type> getParameterTypes() {
        return this.parameterTypes;
    }

    public final int getArity() {
        return this.parameterTypes.size();
    }

    protected void checkArguments(Object[] args) {
        Intrinsics.checkParameterIsNotNull(args, "args");
        if (getArity() == args.length) {
            return;
        }
        throw new IllegalArgumentException("Callable expects " + getArity() + " arguments, but " + args.length + " were provided.");
    }

    protected final void checkObjectInstance(Object obj) {
        if (obj != null) {
            M m = this.member;
            if (m == null) {
                Intrinsics.throwNpe();
            }
            if (m.getDeclaringClass().isInstance(obj)) {
                return;
            }
        }
        throw new IllegalArgumentException("An object member requires the object instance passed as the first argument.");
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0011\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002¢\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$Constructor;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Constructor;", "constructor", "(Ljava/lang/reflect/Constructor;)V", NotificationCompat.CATEGORY_CALL, "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class Constructor extends FunctionCaller<java.lang.reflect.Constructor<?>> {
        /* JADX WARN: Illegal instructions before constructor call */
        public Constructor(java.lang.reflect.Constructor<?> constructor) {
            Intrinsics.checkParameterIsNotNull(constructor, "constructor");
            java.lang.reflect.Constructor<?> constructor2 = constructor;
            Class<?> declaringClass = constructor.getDeclaringClass();
            Intrinsics.checkExpressionValueIsNotNull(declaringClass, "constructor.declaringClass");
            Class<?> cls = declaringClass;
            Class<?> klass = constructor.getDeclaringClass();
            Intrinsics.checkExpressionValueIsNotNull(klass, "klass");
            Class<?> declaringClass2 = klass.getDeclaringClass();
            declaringClass2 = (declaringClass2 == null || Modifier.isStatic(klass.getModifiers())) ? null : declaringClass2;
            Type[] genericParameterTypes = constructor.getGenericParameterTypes();
            Intrinsics.checkExpressionValueIsNotNull(genericParameterTypes, "constructor.genericParameterTypes");
            super(constructor2, cls, declaringClass2, genericParameterTypes);
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            return getMember$kotlin_reflect_api().newInstance(Arrays.copyOf(args, args.length));
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u001b\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0016¢\u0006\u0002\u0010\nR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundConstructor;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Constructor;", "constructor", "boundReceiver", "", "(Ljava/lang/reflect/Constructor;Ljava/lang/Object;)V", NotificationCompat.CATEGORY_CALL, "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class BoundConstructor extends FunctionCaller<java.lang.reflect.Constructor<?>> {
        private final Object boundReceiver;

        /* JADX WARN: Illegal instructions before constructor call */
        public BoundConstructor(java.lang.reflect.Constructor<?> constructor, Object obj) {
            Intrinsics.checkParameterIsNotNull(constructor, "constructor");
            Class<?> declaringClass = constructor.getDeclaringClass();
            Intrinsics.checkExpressionValueIsNotNull(declaringClass, "constructor.declaringClass");
            Type[] genericParameterTypes = constructor.getGenericParameterTypes();
            Intrinsics.checkExpressionValueIsNotNull(genericParameterTypes, "constructor.genericParameterTypes");
            super(constructor, declaringClass, null, genericParameterTypes);
            this.boundReceiver = obj;
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            java.lang.reflect.Constructor<?> member$kotlin_reflect_api = getMember$kotlin_reflect_api();
            Object[] objArrArgsWithReceiver = FunctionCaller.INSTANCE.argsWithReceiver(this.boundReceiver, args);
            return member$kotlin_reflect_api.newInstance(Arrays.copyOf(objArrArgsWithReceiver, objArrArgsWithReceiver.length));
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ%\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\f2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0004¢\u0006\u0002\u0010\u000fR\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$Method;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Method;", FirebaseAnalytics.Param.METHOD, "requiresInstance", "", "parameterTypes", "", "Ljava/lang/reflect/Type;", "(Ljava/lang/reflect/Method;Z[Ljava/lang/reflect/Type;)V", "isVoidMethod", "callMethod", "", "instance", "args", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static abstract class Method extends FunctionCaller<java.lang.reflect.Method> {
        private final boolean isVoidMethod;

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ Method(java.lang.reflect.Method method, boolean z, Type[] typeArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
            z = (i & 2) != 0 ? !Modifier.isStatic(method.getModifiers()) : z;
            if ((i & 4) != 0) {
                typeArr = method.getGenericParameterTypes();
                Intrinsics.checkExpressionValueIsNotNull(typeArr, "method.genericParameterTypes");
            }
            this(method, z, typeArr);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public Method(java.lang.reflect.Method method, boolean z, Type[] parameterTypes) {
            Intrinsics.checkParameterIsNotNull(method, "method");
            Intrinsics.checkParameterIsNotNull(parameterTypes, "parameterTypes");
            java.lang.reflect.Method method2 = method;
            Type genericReturnType = method.getGenericReturnType();
            Intrinsics.checkExpressionValueIsNotNull(genericReturnType, "method.genericReturnType");
            super(method2, genericReturnType, z ? method.getDeclaringClass() : null, parameterTypes);
            this.isVoidMethod = Intrinsics.areEqual(getReturnType(), Void.TYPE);
        }

        protected final Object callMethod(Object instance, Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            return this.isVoidMethod ? Unit.INSTANCE : getMember$kotlin_reflect_api().invoke(instance, Arrays.copyOf(args, args.length));
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$StaticMethod;", "Lkotlin/reflect/jvm/internal/FunctionCaller$Method;", FirebaseAnalytics.Param.METHOD, "Ljava/lang/reflect/Method;", "(Ljava/lang/reflect/Method;)V", NotificationCompat.CATEGORY_CALL, "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class StaticMethod extends Method {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StaticMethod(java.lang.reflect.Method method) {
            super(method, false, null, 6, null);
            Intrinsics.checkParameterIsNotNull(method, "method");
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            return callMethod(null, args);
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$InstanceMethod;", "Lkotlin/reflect/jvm/internal/FunctionCaller$Method;", FirebaseAnalytics.Param.METHOD, "Ljava/lang/reflect/Method;", "(Ljava/lang/reflect/Method;)V", NotificationCompat.CATEGORY_CALL, "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class InstanceMethod extends Method {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public InstanceMethod(java.lang.reflect.Method method) {
            super(method, false, null, 6, null);
            Intrinsics.checkParameterIsNotNull(method, "method");
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            return callMethod(args[0], FunctionCaller.INSTANCE.dropFirstArg(args));
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$JvmStaticInObject;", "Lkotlin/reflect/jvm/internal/FunctionCaller$Method;", FirebaseAnalytics.Param.METHOD, "Ljava/lang/reflect/Method;", "(Ljava/lang/reflect/Method;)V", NotificationCompat.CATEGORY_CALL, "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class JvmStaticInObject extends Method {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public JvmStaticInObject(java.lang.reflect.Method method) {
            super(method, true, null, 4, null);
            Intrinsics.checkParameterIsNotNull(method, "method");
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            checkObjectInstance(ArraysKt.firstOrNull(args));
            return callMethod(null, FunctionCaller.INSTANCE.dropFirstArg(args));
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0016¢\u0006\u0002\u0010\nR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundStaticMethod;", "Lkotlin/reflect/jvm/internal/FunctionCaller$Method;", FirebaseAnalytics.Param.METHOD, "Ljava/lang/reflect/Method;", "boundReceiver", "", "(Ljava/lang/reflect/Method;Ljava/lang/Object;)V", NotificationCompat.CATEGORY_CALL, "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class BoundStaticMethod extends Method {
        private final Object boundReceiver;

        /* JADX WARN: Illegal instructions before constructor call */
        public BoundStaticMethod(java.lang.reflect.Method method, Object obj) {
            Object objCopyOfRange;
            Intrinsics.checkParameterIsNotNull(method, "method");
            Companion companion = FunctionCaller.INSTANCE;
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            Intrinsics.checkExpressionValueIsNotNull(genericParameterTypes, "method.genericParameterTypes");
            if (genericParameterTypes.length <= 1) {
                objCopyOfRange = new Type[0];
            } else {
                objCopyOfRange = Arrays.copyOfRange(genericParameterTypes, 1, genericParameterTypes.length);
                Intrinsics.checkExpressionValueIsNotNull(objCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
                if (objCopyOfRange == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
            super(method, false, (Type[]) objCopyOfRange);
            this.boundReceiver = obj;
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            return callMethod(null, FunctionCaller.INSTANCE.argsWithReceiver(this.boundReceiver, args));
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0016¢\u0006\u0002\u0010\nR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundInstanceMethod;", "Lkotlin/reflect/jvm/internal/FunctionCaller$Method;", FirebaseAnalytics.Param.METHOD, "Ljava/lang/reflect/Method;", "boundReceiver", "", "(Ljava/lang/reflect/Method;Ljava/lang/Object;)V", NotificationCompat.CATEGORY_CALL, "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class BoundInstanceMethod extends Method {
        private final Object boundReceiver;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BoundInstanceMethod(java.lang.reflect.Method method, Object obj) {
            super(method, false, null, 4, null);
            Intrinsics.checkParameterIsNotNull(method, "method");
            this.boundReceiver = obj;
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            return callMethod(this.boundReceiver, args);
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundJvmStaticInObject;", "Lkotlin/reflect/jvm/internal/FunctionCaller$Method;", FirebaseAnalytics.Param.METHOD, "Ljava/lang/reflect/Method;", "(Ljava/lang/reflect/Method;)V", NotificationCompat.CATEGORY_CALL, "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class BoundJvmStaticInObject extends Method {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BoundJvmStaticInObject(java.lang.reflect.Method method) {
            super(method, false, null, 4, null);
            Intrinsics.checkParameterIsNotNull(method, "method");
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            return callMethod(null, args);
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$FieldGetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Field;", "field", "requiresInstance", "", "(Ljava/lang/reflect/Field;Z)V", NotificationCompat.CATEGORY_CALL, "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static abstract class FieldGetter extends FunctionCaller<Field> {
        public /* synthetic */ FieldGetter(Field field, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(field, (i & 2) != 0 ? !Modifier.isStatic(field.getModifiers()) : z);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public FieldGetter(Field field, boolean z) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Field field2 = field;
            Type genericType = field.getGenericType();
            Intrinsics.checkExpressionValueIsNotNull(genericType, "field.genericType");
            super(field2, genericType, z ? field.getDeclaringClass() : null, new Type[0]);
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            return getMember$kotlin_reflect_api().get(getInstanceClass$kotlin_reflect_api() != null ? ArraysKt.first(args) : null);
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u001b\u0010\b\u001a\u0004\u0018\u00010\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0016¢\u0006\u0002\u0010\fJ\u0019\u0010\r\u001a\u00020\u000e2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0014¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$FieldSetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Field;", "field", "notNull", "", "requiresInstance", "(Ljava/lang/reflect/Field;ZZ)V", NotificationCompat.CATEGORY_CALL, "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "checkArguments", "", "([Ljava/lang/Object;)V", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static abstract class FieldSetter extends FunctionCaller<Field> {
        private final boolean notNull;

        public /* synthetic */ FieldSetter(Field field, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(field, z, (i & 4) != 0 ? !Modifier.isStatic(field.getModifiers()) : z2);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public FieldSetter(Field field, boolean z, boolean z2) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Field field2 = field;
            Class cls = Void.TYPE;
            Intrinsics.checkExpressionValueIsNotNull(cls, "Void.TYPE");
            Class cls2 = cls;
            Class<?> declaringClass = z2 ? field.getDeclaringClass() : null;
            Type genericType = field.getGenericType();
            Intrinsics.checkExpressionValueIsNotNull(genericType, "field.genericType");
            super(field2, cls2, declaringClass, new Type[]{genericType});
            this.notNull = z;
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller
        protected void checkArguments(Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            super.checkArguments(args);
            if (this.notNull && ArraysKt.last(args) == null) {
                throw new IllegalArgumentException("null is not allowed as a value for this property.");
            }
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) throws IllegalAccessException, IllegalArgumentException {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            getMember$kotlin_reflect_api().set(getInstanceClass$kotlin_reflect_api() != null ? ArraysKt.first(args) : null, ArraysKt.last(args));
            return Unit.INSTANCE;
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$StaticFieldGetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldGetter;", "field", "Ljava/lang/reflect/Field;", "(Ljava/lang/reflect/Field;)V", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class StaticFieldGetter extends FieldGetter {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StaticFieldGetter(Field field) {
            super(field, false, 2, null);
            Intrinsics.checkParameterIsNotNull(field, "field");
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$InstanceFieldGetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldGetter;", "field", "Ljava/lang/reflect/Field;", "(Ljava/lang/reflect/Field;)V", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class InstanceFieldGetter extends FieldGetter {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public InstanceFieldGetter(Field field) {
            super(field, false, 2, null);
            Intrinsics.checkParameterIsNotNull(field, "field");
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0014¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$JvmStaticInObjectFieldGetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldGetter;", "field", "Ljava/lang/reflect/Field;", "(Ljava/lang/reflect/Field;)V", "checkArguments", "", "args", "", "([Ljava/lang/Object;)V", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class JvmStaticInObjectFieldGetter extends FieldGetter {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public JvmStaticInObjectFieldGetter(Field field) {
            super(field, true);
            Intrinsics.checkParameterIsNotNull(field, "field");
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller
        protected void checkArguments(Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            super.checkArguments(args);
            checkObjectInstance(ArraysKt.firstOrNull(args));
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$ClassCompanionFieldGetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Field;", "field", "klass", "Ljava/lang/Class;", "(Ljava/lang/reflect/Field;Ljava/lang/Class;)V", NotificationCompat.CATEGORY_CALL, "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class ClassCompanionFieldGetter extends FunctionCaller<Field> {
        /* JADX WARN: Illegal instructions before constructor call */
        public ClassCompanionFieldGetter(Field field, Class<?> klass) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(klass, "klass");
            Field field2 = field;
            Type genericType = field.getGenericType();
            Intrinsics.checkExpressionValueIsNotNull(genericType, "field.genericType");
            super(field2, genericType, klass, new Type[0]);
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            return getMember$kotlin_reflect_api().get(ArraysKt.first(args));
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0016¢\u0006\u0002\u0010\nR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundInstanceFieldGetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldGetter;", "field", "Ljava/lang/reflect/Field;", "boundReceiver", "", "(Ljava/lang/reflect/Field;Ljava/lang/Object;)V", NotificationCompat.CATEGORY_CALL, "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class BoundInstanceFieldGetter extends FieldGetter {
        private final Object boundReceiver;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BoundInstanceFieldGetter(Field field, Object obj) {
            super(field, false);
            Intrinsics.checkParameterIsNotNull(field, "field");
            this.boundReceiver = obj;
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller.FieldGetter, kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            return getMember$kotlin_reflect_api().get(this.boundReceiver);
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundJvmStaticInObjectFieldGetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldGetter;", "field", "Ljava/lang/reflect/Field;", "(Ljava/lang/reflect/Field;)V", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class BoundJvmStaticInObjectFieldGetter extends FieldGetter {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BoundJvmStaticInObjectFieldGetter(Field field) {
            super(field, false);
            Intrinsics.checkParameterIsNotNull(field, "field");
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0016¢\u0006\u0002\u0010\nR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundClassCompanionFieldGetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldGetter;", "field", "Ljava/lang/reflect/Field;", "boundReceiver", "", "(Ljava/lang/reflect/Field;Ljava/lang/Object;)V", NotificationCompat.CATEGORY_CALL, "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class BoundClassCompanionFieldGetter extends FieldGetter {
        private final Object boundReceiver;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BoundClassCompanionFieldGetter(Field field, Object obj) {
            super(field, false);
            Intrinsics.checkParameterIsNotNull(field, "field");
            this.boundReceiver = obj;
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller.FieldGetter, kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            return getMember$kotlin_reflect_api().get(this.boundReceiver);
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$StaticFieldSetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldSetter;", "field", "Ljava/lang/reflect/Field;", "notNull", "", "(Ljava/lang/reflect/Field;Z)V", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class StaticFieldSetter extends FieldSetter {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StaticFieldSetter(Field field, boolean z) {
            super(field, z, false, 4, null);
            Intrinsics.checkParameterIsNotNull(field, "field");
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$InstanceFieldSetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldSetter;", "field", "Ljava/lang/reflect/Field;", "notNull", "", "(Ljava/lang/reflect/Field;Z)V", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class InstanceFieldSetter extends FieldSetter {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public InstanceFieldSetter(Field field, boolean z) {
            super(field, z, false, 4, null);
            Intrinsics.checkParameterIsNotNull(field, "field");
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0014¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$JvmStaticInObjectFieldSetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldSetter;", "field", "Ljava/lang/reflect/Field;", "notNull", "", "(Ljava/lang/reflect/Field;Z)V", "checkArguments", "", "args", "", "([Ljava/lang/Object;)V", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class JvmStaticInObjectFieldSetter extends FieldSetter {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public JvmStaticInObjectFieldSetter(Field field, boolean z) {
            super(field, z, true);
            Intrinsics.checkParameterIsNotNull(field, "field");
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller.FieldSetter, kotlin.reflect.jvm.internal.FunctionCaller
        protected void checkArguments(Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            super.checkArguments(args);
            checkObjectInstance(ArraysKt.firstOrNull(args));
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$ClassCompanionFieldSetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Field;", "field", "klass", "Ljava/lang/Class;", "(Ljava/lang/reflect/Field;Ljava/lang/Class;)V", NotificationCompat.CATEGORY_CALL, "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class ClassCompanionFieldSetter extends FunctionCaller<Field> {
        /* JADX WARN: Illegal instructions before constructor call */
        public ClassCompanionFieldSetter(Field field, Class<?> klass) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(klass, "klass");
            Class cls = Void.TYPE;
            Intrinsics.checkExpressionValueIsNotNull(cls, "Void.TYPE");
            Type genericType = field.getGenericType();
            Intrinsics.checkExpressionValueIsNotNull(genericType, "field.genericType");
            super(field, cls, klass, new Type[]{genericType});
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) throws IllegalAccessException, IllegalArgumentException {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            getMember$kotlin_reflect_api().set(null, ArraysKt.last(args));
            return Unit.INSTANCE;
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u001b\u0010\t\u001a\u0004\u0018\u00010\u00072\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0016¢\u0006\u0002\u0010\fR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundInstanceFieldSetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldSetter;", "field", "Ljava/lang/reflect/Field;", "notNull", "", "boundReceiver", "", "(Ljava/lang/reflect/Field;ZLjava/lang/Object;)V", NotificationCompat.CATEGORY_CALL, "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class BoundInstanceFieldSetter extends FieldSetter {
        private final Object boundReceiver;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BoundInstanceFieldSetter(Field field, boolean z, Object obj) {
            super(field, z, false);
            Intrinsics.checkParameterIsNotNull(field, "field");
            this.boundReceiver = obj;
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller.FieldSetter, kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) throws IllegalAccessException, IllegalArgumentException {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            getMember$kotlin_reflect_api().set(this.boundReceiver, ArraysKt.first(args));
            return Unit.INSTANCE;
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundJvmStaticInObjectFieldSetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldSetter;", "field", "Ljava/lang/reflect/Field;", "notNull", "", "(Ljava/lang/reflect/Field;Z)V", NotificationCompat.CATEGORY_CALL, "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class BoundJvmStaticInObjectFieldSetter extends FieldSetter {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BoundJvmStaticInObjectFieldSetter(Field field, boolean z) {
            super(field, z, false);
            Intrinsics.checkParameterIsNotNull(field, "field");
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller.FieldSetter, kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) throws IllegalAccessException, IllegalArgumentException {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            getMember$kotlin_reflect_api().set(null, ArraysKt.last(args));
            return Unit.INSTANCE;
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundClassCompanionFieldSetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Field;", "field", "klass", "Ljava/lang/Class;", "(Ljava/lang/reflect/Field;Ljava/lang/Class;)V", NotificationCompat.CATEGORY_CALL, "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class BoundClassCompanionFieldSetter extends FunctionCaller<Field> {
        /* JADX WARN: Illegal instructions before constructor call */
        public BoundClassCompanionFieldSetter(Field field, Class<?> klass) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(klass, "klass");
            Class cls = Void.TYPE;
            Intrinsics.checkExpressionValueIsNotNull(cls, "Void.TYPE");
            Type genericType = field.getGenericType();
            Intrinsics.checkExpressionValueIsNotNull(genericType, "field.genericType");
            super(field, cls, klass, new Type[]{genericType});
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) throws IllegalAccessException, IllegalArgumentException {
            Intrinsics.checkParameterIsNotNull(args, "args");
            checkArguments(args);
            getMember$kotlin_reflect_api().set(null, ArraysKt.last(args));
            return Unit.INSTANCE;
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0016¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$ThrowingCaller;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "", "()V", NotificationCompat.CATEGORY_CALL, "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class ThrowingCaller extends FunctionCaller {
        public static final ThrowingCaller INSTANCE = new ThrowingCaller();

        /* JADX WARN: Illegal instructions before constructor call */
        private ThrowingCaller() {
            Class cls = Void.TYPE;
            Intrinsics.checkExpressionValueIsNotNull(cls, "Void.TYPE");
            super(null, cls, null, new Type[0]);
        }

        @Override // kotlin.reflect.jvm.internal.FunctionCaller
        public Object call(Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            throw new UnsupportedOperationException("call/callBy are not supported for this declaration.");
        }
    }

    /* compiled from: FunctionCaller.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00012\u0010\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0004¢\u0006\u0002\u0010\u0007J(\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\t0\u0004\"\u0006\b\u0001\u0010\t\u0018\u0001*\n\u0012\u0006\b\u0001\u0012\u0002H\t0\u0004H\u0086\b¢\u0006\u0002\u0010\nJ\u001b\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004*\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$Companion;", "", "()V", "argsWithReceiver", "", "receiver", "args", "(Ljava/lang/Object;[Ljava/lang/Object;)[Ljava/lang/Object;", "dropFirst", ExifInterface.GPS_DIRECTION_TRUE, "([Ljava/lang/Object;)[Ljava/lang/Object;", "dropFirstArg", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 11})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Object[] argsWithReceiver(Object receiver, Object[] args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            Object[] objArr = new Object[args.length + 1];
            objArr[0] = receiver;
            System.arraycopy(args, 0, objArr, 1, args.length);
            return objArr;
        }

        public final Object[] dropFirstArg(Object[] receiver) {
            Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
            if (receiver.length <= 1) {
                return new Object[0];
            }
            Object[] objArrCopyOfRange = Arrays.copyOfRange(receiver, 1, receiver.length);
            Intrinsics.checkExpressionValueIsNotNull(objArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            if (objArrCopyOfRange != null) {
                return objArrCopyOfRange;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }
}

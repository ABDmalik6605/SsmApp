package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

/* compiled from: IntersectionType.kt */
/* loaded from: classes2.dex */
public final class TypeIntersector {
    public static final TypeIntersector INSTANCE = new TypeIntersector();

    private TypeIntersector() {
    }

    public final SimpleType intersectTypes$descriptors(List<? extends SimpleType> types) {
        Intrinsics.checkParameterIsNotNull(types, "types");
        types.size();
        ArrayList arrayList = new ArrayList();
        for (SimpleType simpleType : types) {
            if (simpleType.getConstructor() instanceof IntersectionTypeConstructor) {
                Collection<KotlinType> supertypes = simpleType.getConstructor().getSupertypes();
                Intrinsics.checkExpressionValueIsNotNull(supertypes, "type.constructor.supertypes");
                Collection<KotlinType> collection = supertypes;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
                for (KotlinType it : collection) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    SimpleType simpleTypeUpperIfFlexible = FlexibleTypesKt.upperIfFlexible(it);
                    if (simpleType.isMarkedNullable()) {
                        simpleTypeUpperIfFlexible = simpleTypeUpperIfFlexible.makeNullableAsSpecified(true);
                    }
                    arrayList2.add(simpleTypeUpperIfFlexible);
                }
                arrayList.addAll(arrayList2);
            } else {
                arrayList.add(simpleType);
            }
        }
        ArrayList<SimpleType> arrayList3 = arrayList;
        ResultNullability resultNullabilityCombine = ResultNullability.START;
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            resultNullabilityCombine = resultNullabilityCombine.combine((UnwrappedType) it2.next());
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (SimpleType simpleTypeMakeSimpleTypeDefinitelyNotNullOrNotNull : arrayList3) {
            if (resultNullabilityCombine == ResultNullability.NOT_NULL) {
                simpleTypeMakeSimpleTypeDefinitelyNotNullOrNotNull = SpecialTypesKt.makeSimpleTypeDefinitelyNotNullOrNotNull(simpleTypeMakeSimpleTypeDefinitelyNotNullOrNotNull);
            }
            linkedHashSet.add(simpleTypeMakeSimpleTypeDefinitelyNotNullOrNotNull);
        }
        return intersectTypesWithoutIntersectionType(linkedHashSet);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final kotlin.reflect.jvm.internal.impl.types.SimpleType intersectTypesWithoutIntersectionType(java.util.Set<? extends kotlin.reflect.jvm.internal.impl.types.SimpleType> r10) {
        /*
            r9 = this;
            int r0 = r10.size()
            r1 = 1
            if (r0 != r1) goto L10
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.lang.Object r10 = kotlin.collections.CollectionsKt.single(r10)
            kotlin.reflect.jvm.internal.impl.types.SimpleType r10 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r10
            return r10
        L10:
            java.util.ArrayList r0 = new java.util.ArrayList
            java.util.Collection r10 = (java.util.Collection) r10
            r0.<init>(r10)
            java.util.Iterator r2 = r0.iterator()
        L1b:
            boolean r3 = r2.hasNext()
            r4 = 0
            if (r3 == 0) goto L76
            java.lang.Object r3 = r2.next()
            kotlin.reflect.jvm.internal.impl.types.SimpleType r3 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r3
            r5 = r0
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            boolean r6 = r5 instanceof java.util.Collection
            if (r6 == 0) goto L39
            r6 = r5
            java.util.Collection r6 = (java.util.Collection) r6
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L39
            goto L70
        L39:
            java.util.Iterator r5 = r5.iterator()
        L3d:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L70
            java.lang.Object r6 = r5.next()
            kotlin.reflect.jvm.internal.impl.types.SimpleType r6 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r6
            if (r6 == r3) goto L6c
            kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector r7 = kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.INSTANCE
            java.lang.String r8 = "lower"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r8)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r6 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r6
            java.lang.String r8 = "upper"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r8)
            r8 = r3
            kotlin.reflect.jvm.internal.impl.types.KotlinType r8 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r8
            boolean r7 = r7.isStrictSupertype(r6, r8)
            if (r7 != 0) goto L6a
            kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker r7 = kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker.INSTANCE
            boolean r6 = r7.equalTypes(r6, r8)
            if (r6 == 0) goto L6c
        L6a:
            r6 = 1
            goto L6d
        L6c:
            r6 = 0
        L6d:
            if (r6 == 0) goto L3d
            r4 = 1
        L70:
            if (r4 == 0) goto L1b
            r2.remove()
            goto L1b
        L76:
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            r1.isEmpty()
            int r1 = r0.size()
            r2 = 2
            if (r1 >= r2) goto L91
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r10 = kotlin.collections.CollectionsKt.single(r0)
            java.lang.String r0 = "filteredSuperAndEqualTypes.single()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r0)
            kotlin.reflect.jvm.internal.impl.types.SimpleType r10 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r10
            return r10
        L91:
            kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor r0 = new kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor
            r0.<init>(r10)
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations$Companion r10 = kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r10 = r10.getEMPTY()
            r1 = r0
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r1 = (kotlin.reflect.jvm.internal.impl.types.TypeConstructor) r1
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
            kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope r0 = r0.createScopeForKotlinType()
            java.lang.String r3 = "constructor.createScopeForKotlinType()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r3)
            kotlin.reflect.jvm.internal.impl.types.SimpleType r10 = kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(r10, r1, r2, r4, r0)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.intersectTypesWithoutIntersectionType(java.util.Set):kotlin.reflect.jvm.internal.impl.types.SimpleType");
    }

    private final boolean isStrictSupertype(KotlinType kotlinType, KotlinType kotlinType2) {
        NewKotlinTypeChecker newKotlinTypeChecker = NewKotlinTypeChecker.INSTANCE;
        return newKotlinTypeChecker.isSubtypeOf(kotlinType, kotlinType2) && !newKotlinTypeChecker.isSubtypeOf(kotlinType2, kotlinType);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: IntersectionType.kt */
    private static final class ResultNullability {
        private static final /* synthetic */ ResultNullability[] $VALUES;
        public static final ResultNullability ACCEPT_NULL;
        public static final ResultNullability NOT_NULL;
        public static final ResultNullability START;
        public static final ResultNullability UNKNOWN;

        static {
            START start = new START("START", 0);
            START = start;
            ACCEPT_NULL accept_null = new ACCEPT_NULL("ACCEPT_NULL", 1);
            ACCEPT_NULL = accept_null;
            UNKNOWN unknown = new UNKNOWN("UNKNOWN", 2);
            UNKNOWN = unknown;
            NOT_NULL not_null = new NOT_NULL("NOT_NULL", 3);
            NOT_NULL = not_null;
            $VALUES = new ResultNullability[]{start, accept_null, unknown, not_null};
        }

        public static ResultNullability valueOf(String str) {
            return (ResultNullability) Enum.valueOf(ResultNullability.class, str);
        }

        public static ResultNullability[] values() {
            return (ResultNullability[]) $VALUES.clone();
        }

        public abstract ResultNullability combine(UnwrappedType unwrappedType);

        /* compiled from: IntersectionType.kt */
        static final class START extends ResultNullability {
            START(String str, int i) {
                super(str, i);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            public ResultNullability combine(UnwrappedType nextType) {
                Intrinsics.checkParameterIsNotNull(nextType, "nextType");
                return getResultNullability(nextType);
            }
        }

        protected ResultNullability(String str, int i) {
        }

        /* compiled from: IntersectionType.kt */
        static final class ACCEPT_NULL extends ResultNullability {
            ACCEPT_NULL(String str, int i) {
                super(str, i);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            public ResultNullability combine(UnwrappedType nextType) {
                Intrinsics.checkParameterIsNotNull(nextType, "nextType");
                return getResultNullability(nextType);
            }
        }

        /* compiled from: IntersectionType.kt */
        static final class UNKNOWN extends ResultNullability {
            UNKNOWN(String str, int i) {
                super(str, i);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            public ResultNullability combine(UnwrappedType nextType) {
                Intrinsics.checkParameterIsNotNull(nextType, "nextType");
                ResultNullability resultNullability = getResultNullability(nextType);
                return resultNullability == ResultNullability.ACCEPT_NULL ? this : resultNullability;
            }
        }

        /* compiled from: IntersectionType.kt */
        static final class NOT_NULL extends ResultNullability {
            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            public NOT_NULL combine(UnwrappedType nextType) {
                Intrinsics.checkParameterIsNotNull(nextType, "nextType");
                return this;
            }

            NOT_NULL(String str, int i) {
                super(str, i);
            }
        }

        protected final ResultNullability getResultNullability(UnwrappedType receiver) {
            Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
            return receiver.isMarkedNullable() ? ACCEPT_NULL : NullabilityChecker.INSTANCE.isSubtypeOfAny(receiver) ? NOT_NULL : UNKNOWN;
        }
    }
}

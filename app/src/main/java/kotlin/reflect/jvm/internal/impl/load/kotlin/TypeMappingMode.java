package kotlin.reflect.jvm.internal.impl.load.kotlin;

import androidx.core.app.FrameMetricsAggregator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* compiled from: TypeMappingMode.kt */
/* loaded from: classes2.dex */
public final class TypeMappingMode {
    public static final TypeMappingMode CLASS_DECLARATION;
    public static final Companion Companion = new Companion(null);
    public static final TypeMappingMode DEFAULT;
    public static final TypeMappingMode GENERIC_ARGUMENT;
    public static final TypeMappingMode RETURN_TYPE_BOXED;
    public static final TypeMappingMode SUPER_TYPE;
    public static final TypeMappingMode SUPER_TYPE_KOTLIN_COLLECTIONS_AS_IS;
    public static final TypeMappingMode VALUE_FOR_ANNOTATION;
    private final TypeMappingMode genericArgumentMode;
    private final TypeMappingMode genericContravariantArgumentMode;
    private final TypeMappingMode genericInvariantArgumentMode;
    private final boolean isForAnnotationParameter;
    private final boolean kotlinCollectionsToJavaCollections;
    private final boolean needInlineClassWrapping;
    private final boolean needPrimitiveBoxing;
    private final boolean skipDeclarationSiteWildcards;
    private final boolean skipDeclarationSiteWildcardsIfPossible;

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Variance.IN_VARIANCE.ordinal()] = 1;
            iArr[Variance.INVARIANT.ordinal()] = 2;
        }
    }

    private TypeMappingMode(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, TypeMappingMode typeMappingMode, boolean z6, TypeMappingMode typeMappingMode2, TypeMappingMode typeMappingMode3) {
        this.needPrimitiveBoxing = z;
        this.needInlineClassWrapping = z2;
        this.isForAnnotationParameter = z3;
        this.skipDeclarationSiteWildcards = z4;
        this.skipDeclarationSiteWildcardsIfPossible = z5;
        this.genericArgumentMode = typeMappingMode;
        this.kotlinCollectionsToJavaCollections = z6;
        this.genericContravariantArgumentMode = typeMappingMode2;
        this.genericInvariantArgumentMode = typeMappingMode3;
    }

    public final boolean getNeedPrimitiveBoxing() {
        return this.needPrimitiveBoxing;
    }

    public final boolean getNeedInlineClassWrapping() {
        return this.needInlineClassWrapping;
    }

    public final boolean isForAnnotationParameter() {
        return this.isForAnnotationParameter;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* synthetic */ TypeMappingMode(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, TypeMappingMode typeMappingMode, boolean z6, TypeMappingMode typeMappingMode2, TypeMappingMode typeMappingMode3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        boolean z7 = (i & 1) != 0 ? true : z;
        boolean z8 = (i & 2) != 0 ? true : z2;
        boolean z9 = (i & 4) != 0 ? false : z3;
        boolean z10 = (i & 8) != 0 ? false : z4;
        boolean z11 = (i & 16) == 0 ? z5 : false;
        TypeMappingMode typeMappingMode4 = (i & 32) != 0 ? (TypeMappingMode) null : typeMappingMode;
        this(z7, z8, z9, z10, z11, typeMappingMode4, (i & 64) == 0 ? z6 : true, (i & 128) != 0 ? typeMappingMode4 : typeMappingMode2, (i & 256) != 0 ? typeMappingMode4 : typeMappingMode3);
    }

    public final boolean getKotlinCollectionsToJavaCollections() {
        return this.kotlinCollectionsToJavaCollections;
    }

    /* compiled from: TypeMappingMode.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        TypeMappingMode typeMappingMode = null;
        TypeMappingMode typeMappingMode2 = null;
        DefaultConstructorMarker defaultConstructorMarker = null;
        TypeMappingMode typeMappingMode3 = new TypeMappingMode(z, z2, z3, z4, z5, null, z6, typeMappingMode, typeMappingMode2, FrameMetricsAggregator.EVERY_DURATION, defaultConstructorMarker);
        GENERIC_ARGUMENT = typeMappingMode3;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        TypeMappingMode typeMappingMode4 = null;
        TypeMappingMode typeMappingMode5 = null;
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        RETURN_TYPE_BOXED = new TypeMappingMode(z7, true, false, z8, z9, null, z10, typeMappingMode4, typeMappingMode5, 509, defaultConstructorMarker2);
        int i = 476;
        DEFAULT = new TypeMappingMode(z, z2, z3, z4, z5, typeMappingMode3, z6, typeMappingMode, typeMappingMode2, i, defaultConstructorMarker);
        CLASS_DECLARATION = new TypeMappingMode(z, true, z3, z4, z5, typeMappingMode3, z6, typeMappingMode, typeMappingMode2, i, defaultConstructorMarker);
        boolean z11 = false;
        boolean z12 = true;
        SUPER_TYPE = new TypeMappingMode(z, z11, z3, z12, z5, typeMappingMode3, z6, typeMappingMode, typeMappingMode2, 471, defaultConstructorMarker);
        SUPER_TYPE_KOTLIN_COLLECTIONS_AS_IS = new TypeMappingMode(z, z11, z3, z12, z5, typeMappingMode3, z6, typeMappingMode, typeMappingMode2, 407, defaultConstructorMarker);
        VALUE_FOR_ANNOTATION = new TypeMappingMode(z7, false, true, z8, z9, new TypeMappingMode(z, z11, true, false, z5, typeMappingMode3, z6, typeMappingMode, typeMappingMode2, 475, defaultConstructorMarker), z10, typeMappingMode4, typeMappingMode5, 472, defaultConstructorMarker2);
    }

    public final TypeMappingMode toGenericArgumentMode(Variance effectiveVariance) {
        Intrinsics.checkParameterIsNotNull(effectiveVariance, "effectiveVariance");
        int i = WhenMappings.$EnumSwitchMapping$0[effectiveVariance.ordinal()];
        if (i == 1) {
            TypeMappingMode typeMappingMode = this.genericContravariantArgumentMode;
            if (typeMappingMode != null) {
                return typeMappingMode;
            }
        } else if (i == 2) {
            TypeMappingMode typeMappingMode2 = this.genericInvariantArgumentMode;
            if (typeMappingMode2 != null) {
                return typeMappingMode2;
            }
        } else {
            TypeMappingMode typeMappingMode3 = this.genericArgumentMode;
            if (typeMappingMode3 != null) {
                return typeMappingMode3;
            }
        }
        return this;
    }

    public final TypeMappingMode wrapInlineClassesMode() {
        return new TypeMappingMode(this.needPrimitiveBoxing, true, this.isForAnnotationParameter, this.skipDeclarationSiteWildcards, this.skipDeclarationSiteWildcardsIfPossible, this.genericArgumentMode, this.kotlinCollectionsToJavaCollections, this.genericContravariantArgumentMode, this.genericInvariantArgumentMode);
    }
}

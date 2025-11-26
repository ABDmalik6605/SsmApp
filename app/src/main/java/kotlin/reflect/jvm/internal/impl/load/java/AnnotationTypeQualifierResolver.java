package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.NullabilityQualifierWithApplicability;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;

/* compiled from: AnnotationTypeQualifierResolver.kt */
/* loaded from: classes2.dex */
public final class AnnotationTypeQualifierResolver {
    private final boolean disabled;
    private final Jsr305State jsr305State;
    private final MemoizedFunctionToNullable<ClassDescriptor, AnnotationDescriptor> resolvedNicknames;

    /* compiled from: AnnotationTypeQualifierResolver.kt */
    public enum QualifierApplicabilityType {
        METHOD_RETURN_TYPE,
        VALUE_PARAMETER,
        FIELD,
        TYPE_USE
    }

    public AnnotationTypeQualifierResolver(StorageManager storageManager, Jsr305State jsr305State) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(jsr305State, "jsr305State");
        this.jsr305State = jsr305State;
        this.resolvedNicknames = storageManager.createMemoizedFunctionWithNullableValues(new AnnotationTypeQualifierResolver$resolvedNicknames$1(this));
        this.disabled = jsr305State.getDisabled();
    }

    /* compiled from: AnnotationTypeQualifierResolver.kt */
    public static final class TypeQualifierWithApplicability {
        private final int applicability;
        private final AnnotationDescriptor typeQualifier;

        public TypeQualifierWithApplicability(AnnotationDescriptor typeQualifier, int i) {
            Intrinsics.checkParameterIsNotNull(typeQualifier, "typeQualifier");
            this.typeQualifier = typeQualifier;
            this.applicability = i;
        }

        public final AnnotationDescriptor component1() {
            return this.typeQualifier;
        }

        public final List<QualifierApplicabilityType> component2() {
            QualifierApplicabilityType[] qualifierApplicabilityTypeArrValues = QualifierApplicabilityType.values();
            ArrayList arrayList = new ArrayList();
            for (QualifierApplicabilityType qualifierApplicabilityType : qualifierApplicabilityTypeArrValues) {
                if (isApplicableTo(qualifierApplicabilityType)) {
                    arrayList.add(qualifierApplicabilityType);
                }
            }
            return arrayList;
        }

        private final boolean isApplicableTo(QualifierApplicabilityType qualifierApplicabilityType) {
            return isApplicableConsideringMask(QualifierApplicabilityType.TYPE_USE) || isApplicableConsideringMask(qualifierApplicabilityType);
        }

        private final boolean isApplicableConsideringMask(QualifierApplicabilityType qualifierApplicabilityType) {
            return ((1 << qualifierApplicabilityType.ordinal()) & this.applicability) != 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AnnotationDescriptor computeTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (!classDescriptor.getAnnotations().hasAnnotation(AnnotationTypeQualifierResolverKt.TYPE_QUALIFIER_NICKNAME_FQNAME)) {
            return null;
        }
        Iterator<AnnotationDescriptor> it = classDescriptor.getAnnotations().iterator();
        while (it.hasNext()) {
            AnnotationDescriptor annotationDescriptorResolveTypeQualifierAnnotation = resolveTypeQualifierAnnotation(it.next());
            if (annotationDescriptorResolveTypeQualifierAnnotation != null) {
                return annotationDescriptorResolveTypeQualifierAnnotation;
            }
        }
        return null;
    }

    private final AnnotationDescriptor resolveTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (classDescriptor.getKind() != ClassKind.ANNOTATION_CLASS) {
            return null;
        }
        return this.resolvedNicknames.invoke(classDescriptor);
    }

    public final AnnotationDescriptor resolveTypeQualifierAnnotation(AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass;
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (this.jsr305State.getDisabled() || (annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor)) == null) {
            return null;
        }
        return AnnotationTypeQualifierResolverKt.isAnnotatedWithTypeQualifier(annotationClass) ? annotationDescriptor : resolveTypeQualifierNickname(annotationClass);
    }

    public final NullabilityQualifierWithApplicability resolveQualifierBuiltInDefaultAnnotation(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (this.jsr305State.getDisabled()) {
            return null;
        }
        NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability = (NullabilityQualifierWithApplicability) AnnotationTypeQualifierResolverKt.BUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS.get(annotationDescriptor.getFqName());
        if (nullabilityQualifierWithApplicability == null) {
            return (NullabilityQualifierWithApplicability) null;
        }
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusComponent1 = nullabilityQualifierWithApplicability.component1();
        Collection<QualifierApplicabilityType> collectionComponent2 = nullabilityQualifierWithApplicability.component2();
        ReportLevel reportLevelResolveJsr305AnnotationState = resolveJsr305AnnotationState(annotationDescriptor);
        if (!(reportLevelResolveJsr305AnnotationState != ReportLevel.IGNORE)) {
            reportLevelResolveJsr305AnnotationState = null;
        }
        if (reportLevelResolveJsr305AnnotationState != null) {
            return new NullabilityQualifierWithApplicability(NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatusComponent1, null, reportLevelResolveJsr305AnnotationState.isWarning(), 1, null), collectionComponent2);
        }
        return null;
    }

    public final TypeQualifierWithApplicability resolveTypeQualifierDefaultAnnotation(AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass;
        AnnotationDescriptor next;
        List<QualifierApplicabilityType> listEmptyList;
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (!this.jsr305State.getDisabled() && (annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor)) != null) {
            if (!annotationClass.getAnnotations().hasAnnotation(AnnotationTypeQualifierResolverKt.TYPE_QUALIFIER_DEFAULT_FQNAME)) {
                annotationClass = null;
            }
            if (annotationClass != null) {
                ClassDescriptor annotationClass2 = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
                if (annotationClass2 == null) {
                    Intrinsics.throwNpe();
                }
                AnnotationDescriptor annotationDescriptorMo1326findAnnotation = annotationClass2.getAnnotations().mo1326findAnnotation(AnnotationTypeQualifierResolverKt.TYPE_QUALIFIER_DEFAULT_FQNAME);
                if (annotationDescriptorMo1326findAnnotation == null) {
                    Intrinsics.throwNpe();
                }
                Map<Name, ConstantValue<?>> allValueArguments = annotationDescriptorMo1326findAnnotation.getAllValueArguments();
                ArrayList arrayList = new ArrayList();
                for (Map.Entry<Name, ConstantValue<?>> entry : allValueArguments.entrySet()) {
                    Name key = entry.getKey();
                    ConstantValue<?> value = entry.getValue();
                    if (Intrinsics.areEqual(key, JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME)) {
                        listEmptyList = mapConstantToQualifierApplicabilityTypes(value);
                    } else {
                        listEmptyList = CollectionsKt.emptyList();
                    }
                    CollectionsKt.addAll(arrayList, listEmptyList);
                }
                Iterator it = arrayList.iterator();
                int iOrdinal = 0;
                while (it.hasNext()) {
                    iOrdinal |= 1 << ((QualifierApplicabilityType) it.next()).ordinal();
                }
                Iterator<AnnotationDescriptor> it2 = annotationClass.getAnnotations().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it2.next();
                    if (resolveTypeQualifierAnnotation(next) != null) {
                        break;
                    }
                }
                AnnotationDescriptor annotationDescriptor2 = next;
                if (annotationDescriptor2 != null) {
                    return new TypeQualifierWithApplicability(annotationDescriptor2, iOrdinal);
                }
            }
        }
        return null;
    }

    public final ReportLevel resolveJsr305AnnotationState(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        ReportLevel reportLevelResolveJsr305CustomState = resolveJsr305CustomState(annotationDescriptor);
        return reportLevelResolveJsr305CustomState != null ? reportLevelResolveJsr305CustomState : this.jsr305State.getGlobal();
    }

    public final ReportLevel resolveJsr305CustomState(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        Map<String, ReportLevel> user = this.jsr305State.getUser();
        FqName fqName = annotationDescriptor.getFqName();
        ReportLevel reportLevel = user.get(fqName != null ? fqName.asString() : null);
        if (reportLevel != null) {
            return reportLevel;
        }
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        if (annotationClass != null) {
            return migrationAnnotationStatus(annotationClass);
        }
        return null;
    }

    private final ReportLevel migrationAnnotationStatus(ClassDescriptor classDescriptor) {
        AnnotationDescriptor annotationDescriptorMo1326findAnnotation = classDescriptor.getAnnotations().mo1326findAnnotation(AnnotationTypeQualifierResolverKt.MIGRATION_ANNOTATION_FQNAME);
        ConstantValue<?> constantValueFirstArgument = annotationDescriptorMo1326findAnnotation != null ? DescriptorUtilsKt.firstArgument(annotationDescriptorMo1326findAnnotation) : null;
        if (!(constantValueFirstArgument instanceof EnumValue)) {
            constantValueFirstArgument = null;
        }
        EnumValue enumValue = (EnumValue) constantValueFirstArgument;
        if (enumValue == null) {
            return null;
        }
        ReportLevel migration = this.jsr305State.getMigration();
        if (migration != null) {
            return migration;
        }
        String strAsString = enumValue.getEnumEntryName().asString();
        int iHashCode = strAsString.hashCode();
        if (iHashCode == -2137067054) {
            if (strAsString.equals("IGNORE")) {
                return ReportLevel.IGNORE;
            }
            return null;
        }
        if (iHashCode == -1838656823) {
            if (strAsString.equals("STRICT")) {
                return ReportLevel.STRICT;
            }
            return null;
        }
        if (iHashCode == 2656902 && strAsString.equals("WARN")) {
            return ReportLevel.WARN;
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.util.List<kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType> mapConstantToQualifierApplicabilityTypes(kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue<?> r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue
            if (r0 == 0) goto L30
            kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue r3 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue) r3
            java.lang.Object r3 = r3.getValue()
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r3 = r3.iterator()
        L17:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto L2d
            java.lang.Object r1 = r3.next()
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r1 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r1
            java.util.List r1 = r2.mapConstantToQualifierApplicabilityTypes(r1)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            kotlin.collections.CollectionsKt.addAll(r0, r1)
            goto L17
        L2d:
            java.util.List r0 = (java.util.List) r0
            goto L7c
        L30:
            boolean r0 = r3 instanceof kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue
            if (r0 == 0) goto L78
            kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue r3 = (kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue) r3
            kotlin.reflect.jvm.internal.impl.name.Name r3 = r3.getEnumEntryName()
            java.lang.String r3 = r3.getIdentifier()
            int r0 = r3.hashCode()
            switch(r0) {
                case -2024225567: goto L67;
                case 66889946: goto L5c;
                case 107598562: goto L51;
                case 446088073: goto L46;
                default: goto L45;
            }
        L45:
            goto L72
        L46:
            java.lang.String r0 = "PARAMETER"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L72
            kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver$QualifierApplicabilityType r3 = kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType.VALUE_PARAMETER
            goto L73
        L51:
            java.lang.String r0 = "TYPE_USE"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L72
            kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver$QualifierApplicabilityType r3 = kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType.TYPE_USE
            goto L73
        L5c:
            java.lang.String r0 = "FIELD"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L72
            kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver$QualifierApplicabilityType r3 = kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType.FIELD
            goto L73
        L67:
            java.lang.String r0 = "METHOD"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L72
            kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver$QualifierApplicabilityType r3 = kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType.METHOD_RETURN_TYPE
            goto L73
        L72:
            r3 = 0
        L73:
            java.util.List r0 = kotlin.collections.CollectionsKt.listOfNotNull(r3)
            goto L7c
        L78:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L7c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.mapConstantToQualifierApplicabilityTypes(kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue):java.util.List");
    }

    public final boolean getDisabled() {
        return this.disabled;
    }
}

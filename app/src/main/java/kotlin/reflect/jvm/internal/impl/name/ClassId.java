package kotlin.reflect.jvm.internal.impl.name;

import kotlin.text.StringsKt;
import org.apache.commons.lang.ClassUtils;

/* loaded from: classes2.dex */
public final class ClassId {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final boolean local;
    private final FqName packageFqName;
    private final FqName relativeClassName;

    public static ClassId topLevel(FqName fqName) {
        return new ClassId(fqName.parent(), fqName.shortName());
    }

    public ClassId(FqName fqName, FqName fqName2, boolean z) {
        this.packageFqName = fqName;
        this.relativeClassName = fqName2;
        this.local = z;
    }

    public ClassId(FqName fqName, Name name) {
        this(fqName, FqName.topLevel(name), false);
    }

    public FqName getPackageFqName() {
        return this.packageFqName;
    }

    public FqName getRelativeClassName() {
        return this.relativeClassName;
    }

    public Name getShortClassName() {
        return this.relativeClassName.shortName();
    }

    public boolean isLocal() {
        return this.local;
    }

    public ClassId createNestedClassId(Name name) {
        return new ClassId(getPackageFqName(), this.relativeClassName.child(name), this.local);
    }

    public ClassId getOuterClassId() {
        FqName fqNameParent = this.relativeClassName.parent();
        if (fqNameParent.isRoot()) {
            return null;
        }
        return new ClassId(getPackageFqName(), fqNameParent, this.local);
    }

    public boolean isNestedClass() {
        return !this.relativeClassName.parent().isRoot();
    }

    public FqName asSingleFqName() {
        if (this.packageFqName.isRoot()) {
            return this.relativeClassName;
        }
        return new FqName(this.packageFqName.asString() + "." + this.relativeClassName.asString());
    }

    public static ClassId fromString(String str) {
        return fromString(str, false);
    }

    public static ClassId fromString(String str, boolean z) {
        return new ClassId(new FqName(StringsKt.substringBeforeLast(str, '/', "").replace('/', ClassUtils.PACKAGE_SEPARATOR_CHAR)), new FqName(StringsKt.substringAfterLast(str, '/', str)), z);
    }

    public String asString() {
        if (this.packageFqName.isRoot()) {
            return this.relativeClassName.asString();
        }
        return this.packageFqName.asString().replace(ClassUtils.PACKAGE_SEPARATOR_CHAR, '/') + "/" + this.relativeClassName.asString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ClassId classId = (ClassId) obj;
        return this.packageFqName.equals(classId.packageFqName) && this.relativeClassName.equals(classId.relativeClassName) && this.local == classId.local;
    }

    public int hashCode() {
        return (((this.packageFqName.hashCode() * 31) + this.relativeClassName.hashCode()) * 31) + Boolean.valueOf(this.local).hashCode();
    }

    public String toString() {
        if (!this.packageFqName.isRoot()) {
            return asString();
        }
        return "/" + asString();
    }
}

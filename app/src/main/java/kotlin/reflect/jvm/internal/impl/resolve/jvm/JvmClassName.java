package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.apache.commons.lang.ClassUtils;

/* loaded from: classes2.dex */
public class JvmClassName {
    private FqName fqName;
    private final String internalName;

    public static JvmClassName byInternalName(String str) {
        return new JvmClassName(str);
    }

    public static JvmClassName byClassId(ClassId classId) {
        FqName packageFqName = classId.getPackageFqName();
        String strReplace = classId.getRelativeClassName().asString().replace(ClassUtils.PACKAGE_SEPARATOR_CHAR, '$');
        if (packageFqName.isRoot()) {
            return new JvmClassName(strReplace);
        }
        return new JvmClassName(packageFqName.asString().replace(ClassUtils.PACKAGE_SEPARATOR_CHAR, '/') + "/" + strReplace);
    }

    public static JvmClassName byFqNameWithoutInnerClasses(FqName fqName) {
        JvmClassName jvmClassName = new JvmClassName(fqName.asString().replace(ClassUtils.PACKAGE_SEPARATOR_CHAR, '/'));
        jvmClassName.fqName = fqName;
        return jvmClassName;
    }

    private JvmClassName(String str) {
        this.internalName = str;
    }

    public FqName getFqNameForTopLevelClassMaybeWithDollars() {
        return new FqName(this.internalName.replace('/', ClassUtils.PACKAGE_SEPARATOR_CHAR));
    }

    public FqName getPackageFqName() {
        int iLastIndexOf = this.internalName.lastIndexOf("/");
        if (iLastIndexOf == -1) {
            return FqName.ROOT;
        }
        return new FqName(this.internalName.substring(0, iLastIndexOf).replace('/', ClassUtils.PACKAGE_SEPARATOR_CHAR));
    }

    public String getInternalName() {
        return this.internalName;
    }

    public String toString() {
        return this.internalName;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.internalName.equals(((JvmClassName) obj).internalName);
    }

    public int hashCode() {
        return this.internalName.hashCode();
    }
}

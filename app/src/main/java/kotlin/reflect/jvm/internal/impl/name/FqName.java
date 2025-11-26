package kotlin.reflect.jvm.internal.impl.name;

import java.util.List;

/* loaded from: classes2.dex */
public final class FqName {
    public static final FqName ROOT = new FqName("");
    private final FqNameUnsafe fqName;
    private transient FqName parent;

    public FqName(String str) {
        this.fqName = new FqNameUnsafe(str, this);
    }

    public FqName(FqNameUnsafe fqNameUnsafe) {
        this.fqName = fqNameUnsafe;
    }

    private FqName(FqNameUnsafe fqNameUnsafe, FqName fqName) {
        this.fqName = fqNameUnsafe;
        this.parent = fqName;
    }

    public String asString() {
        return this.fqName.asString();
    }

    public FqNameUnsafe toUnsafe() {
        return this.fqName;
    }

    public boolean isRoot() {
        return this.fqName.isRoot();
    }

    public FqName parent() {
        FqName fqName = this.parent;
        if (fqName != null) {
            return fqName;
        }
        if (isRoot()) {
            throw new IllegalStateException("root");
        }
        FqName fqName2 = new FqName(this.fqName.parent());
        this.parent = fqName2;
        return fqName2;
    }

    public FqName child(Name name) {
        return new FqName(this.fqName.child(name), this);
    }

    public Name shortName() {
        return this.fqName.shortName();
    }

    public Name shortNameOrSpecial() {
        return this.fqName.shortNameOrSpecial();
    }

    public List<Name> pathSegments() {
        return this.fqName.pathSegments();
    }

    public boolean startsWith(Name name) {
        return this.fqName.startsWith(name);
    }

    public static FqName topLevel(Name name) {
        return new FqName(FqNameUnsafe.topLevel(name));
    }

    public String toString() {
        return this.fqName.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FqName) && this.fqName.equals(((FqName) obj).fqName);
    }

    public int hashCode() {
        return this.fqName.hashCode();
    }
}

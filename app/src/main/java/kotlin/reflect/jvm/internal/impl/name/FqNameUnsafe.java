package kotlin.reflect.jvm.internal.impl.name;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes2.dex */
public final class FqNameUnsafe {
    private static final Name ROOT_NAME = Name.special("<root>");
    private static final Pattern SPLIT_BY_DOTS = Pattern.compile("\\.");
    private static final Function1<String, Name> STRING_TO_NAME = new Function1<String, Name>() { // from class: kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe.1
        @Override // kotlin.jvm.functions.Function1
        public Name invoke(String str) {
            return Name.guessByFirstCharacter(str);
        }
    };
    private final String fqName;
    private transient FqNameUnsafe parent;
    private transient FqName safe;
    private transient Name shortName;

    FqNameUnsafe(String str, FqName fqName) {
        this.fqName = str;
        this.safe = fqName;
    }

    public FqNameUnsafe(String str) {
        this.fqName = str;
    }

    private FqNameUnsafe(String str, FqNameUnsafe fqNameUnsafe, Name name) {
        this.fqName = str;
        this.parent = fqNameUnsafe;
        this.shortName = name;
    }

    private void compute() {
        int iLastIndexOf = this.fqName.lastIndexOf(46);
        if (iLastIndexOf >= 0) {
            this.shortName = Name.guessByFirstCharacter(this.fqName.substring(iLastIndexOf + 1));
            this.parent = new FqNameUnsafe(this.fqName.substring(0, iLastIndexOf));
        } else {
            this.shortName = Name.guessByFirstCharacter(this.fqName);
            this.parent = FqName.ROOT.toUnsafe();
        }
    }

    public String asString() {
        return this.fqName;
    }

    public boolean isSafe() {
        return this.safe != null || asString().indexOf(60) < 0;
    }

    public FqName toSafe() {
        FqName fqName = this.safe;
        if (fqName != null) {
            return fqName;
        }
        FqName fqName2 = new FqName(this);
        this.safe = fqName2;
        return fqName2;
    }

    public boolean isRoot() {
        return this.fqName.isEmpty();
    }

    public FqNameUnsafe parent() {
        FqNameUnsafe fqNameUnsafe = this.parent;
        if (fqNameUnsafe != null) {
            return fqNameUnsafe;
        }
        if (isRoot()) {
            throw new IllegalStateException("root");
        }
        compute();
        return this.parent;
    }

    public FqNameUnsafe child(Name name) {
        String strAsString;
        if (isRoot()) {
            strAsString = name.asString();
        } else {
            strAsString = this.fqName + "." + name.asString();
        }
        return new FqNameUnsafe(strAsString, this, name);
    }

    public Name shortName() {
        Name name = this.shortName;
        if (name != null) {
            return name;
        }
        if (isRoot()) {
            throw new IllegalStateException("root");
        }
        compute();
        return this.shortName;
    }

    public Name shortNameOrSpecial() {
        if (isRoot()) {
            return ROOT_NAME;
        }
        return shortName();
    }

    public List<Name> pathSegments() {
        return isRoot() ? Collections.emptyList() : ArraysKt.map(SPLIT_BY_DOTS.split(this.fqName), STRING_TO_NAME);
    }

    public boolean startsWith(Name name) {
        int iIndexOf = this.fqName.indexOf(46);
        if (isRoot()) {
            return false;
        }
        String str = this.fqName;
        String strAsString = name.asString();
        if (iIndexOf == -1) {
            iIndexOf = this.fqName.length();
        }
        return str.regionMatches(0, strAsString, 0, iIndexOf);
    }

    public static FqNameUnsafe topLevel(Name name) {
        return new FqNameUnsafe(name.asString(), FqName.ROOT.toUnsafe(), name);
    }

    public String toString() {
        return isRoot() ? ROOT_NAME.asString() : this.fqName;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FqNameUnsafe) && this.fqName.equals(((FqNameUnsafe) obj).fqName);
    }

    public int hashCode() {
        return this.fqName.hashCode();
    }
}

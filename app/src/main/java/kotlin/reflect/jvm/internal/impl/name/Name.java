package kotlin.reflect.jvm.internal.impl.name;

/* loaded from: classes2.dex */
public final class Name implements Comparable<Name> {
    private final String name;
    private final boolean special;

    private Name(String str, boolean z) {
        this.name = str;
        this.special = z;
    }

    public String asString() {
        return this.name;
    }

    public String getIdentifier() {
        if (this.special) {
            throw new IllegalStateException("not identifier: " + this);
        }
        return asString();
    }

    public boolean isSpecial() {
        return this.special;
    }

    @Override // java.lang.Comparable
    public int compareTo(Name name) {
        return this.name.compareTo(name.name);
    }

    public static Name identifier(String str) {
        return new Name(str, false);
    }

    public static boolean isValidIdentifier(String str) {
        if (str.isEmpty() || str.startsWith("<")) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '.' || cCharAt == '/' || cCharAt == '\\') {
                return false;
            }
        }
        return true;
    }

    public static Name special(String str) {
        if (!str.startsWith("<")) {
            throw new IllegalArgumentException("special name must start with '<': " + str);
        }
        return new Name(str, true);
    }

    public static Name guessByFirstCharacter(String str) {
        if (str.startsWith("<")) {
            return special(str);
        }
        return identifier(str);
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Name)) {
            return false;
        }
        Name name = (Name) obj;
        return this.special == name.special && this.name.equals(name.name);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + (this.special ? 1 : 0);
    }
}

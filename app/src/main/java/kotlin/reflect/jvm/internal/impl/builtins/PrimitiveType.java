package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* loaded from: classes2.dex */
public enum PrimitiveType {
    BOOLEAN("Boolean"),
    CHAR("Char"),
    BYTE("Byte"),
    SHORT("Short"),
    INT("Int"),
    FLOAT("Float"),
    LONG("Long"),
    DOUBLE("Double");

    private final Name arrayTypeName;
    private final Name typeName;
    public static final Set<PrimitiveType> NUMBER_TYPES = Collections.unmodifiableSet(EnumSet.of(CHAR, BYTE, SHORT, INT, FLOAT, LONG, DOUBLE));
    private FqName typeFqName = null;
    private FqName arrayTypeFqName = null;

    PrimitiveType(String str) {
        this.typeName = Name.identifier(str);
        this.arrayTypeName = Name.identifier(str + "Array");
    }

    public Name getTypeName() {
        return this.typeName;
    }

    public Name getArrayTypeName() {
        return this.arrayTypeName;
    }
}

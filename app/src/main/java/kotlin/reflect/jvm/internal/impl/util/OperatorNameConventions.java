package kotlin.reflect.jvm.internal.impl.util;

import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.text.Regex;

/* compiled from: OperatorNameConventions.kt */
/* loaded from: classes2.dex */
public final class OperatorNameConventions {
    public static final Set<Name> ASSIGNMENT_OPERATIONS;
    public static final Set<Name> BINARY_OPERATION_NAMES;
    public static final Name DEC;
    public static final Name DIV;
    public static final Name DIV_ASSIGN;
    public static final Name INC;
    public static final Name MINUS;
    public static final Name MINUS_ASSIGN;
    public static final Name MOD;
    public static final Name MOD_ASSIGN;
    public static final Name NOT;
    public static final Name PLUS;
    public static final Name PLUS_ASSIGN;
    public static final Name RANGE_TO;
    public static final Name REM;
    public static final Name REM_ASSIGN;
    public static final Set<Name> SIMPLE_UNARY_OPERATION_NAMES;
    public static final Name TIMES;
    public static final Name TIMES_ASSIGN;
    public static final Name UNARY_MINUS;
    public static final Set<Name> UNARY_OPERATION_NAMES;
    public static final Name UNARY_PLUS;
    public static final OperatorNameConventions INSTANCE = new OperatorNameConventions();
    public static final Name GET_VALUE = Name.identifier("getValue");
    public static final Name SET_VALUE = Name.identifier("setValue");
    public static final Name PROVIDE_DELEGATE = Name.identifier("provideDelegate");
    public static final Name EQUALS = Name.identifier("equals");
    public static final Name COMPARE_TO = Name.identifier("compareTo");
    public static final Name CONTAINS = Name.identifier("contains");
    public static final Name INVOKE = Name.identifier("invoke");
    public static final Name ITERATOR = Name.identifier("iterator");
    public static final Name GET = Name.identifier("get");
    public static final Name SET = Name.identifier("set");
    public static final Name NEXT = Name.identifier("next");
    public static final Name HAS_NEXT = Name.identifier("hasNext");
    public static final Regex COMPONENT_REGEX = new Regex("component\\d+");
    public static final Name AND = Name.identifier("and");
    public static final Name OR = Name.identifier("or");

    static {
        Name nameIdentifier = Name.identifier("inc");
        INC = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("dec");
        DEC = nameIdentifier2;
        Name nameIdentifier3 = Name.identifier("plus");
        PLUS = nameIdentifier3;
        Name nameIdentifier4 = Name.identifier("minus");
        MINUS = nameIdentifier4;
        Name nameIdentifier5 = Name.identifier("not");
        NOT = nameIdentifier5;
        Name nameIdentifier6 = Name.identifier("unaryMinus");
        UNARY_MINUS = nameIdentifier6;
        Name nameIdentifier7 = Name.identifier("unaryPlus");
        UNARY_PLUS = nameIdentifier7;
        Name nameIdentifier8 = Name.identifier("times");
        TIMES = nameIdentifier8;
        Name nameIdentifier9 = Name.identifier("div");
        DIV = nameIdentifier9;
        Name nameIdentifier10 = Name.identifier("mod");
        MOD = nameIdentifier10;
        Name nameIdentifier11 = Name.identifier("rem");
        REM = nameIdentifier11;
        Name nameIdentifier12 = Name.identifier("rangeTo");
        RANGE_TO = nameIdentifier12;
        Name nameIdentifier13 = Name.identifier("timesAssign");
        TIMES_ASSIGN = nameIdentifier13;
        Name nameIdentifier14 = Name.identifier("divAssign");
        DIV_ASSIGN = nameIdentifier14;
        Name nameIdentifier15 = Name.identifier("modAssign");
        MOD_ASSIGN = nameIdentifier15;
        Name nameIdentifier16 = Name.identifier("remAssign");
        REM_ASSIGN = nameIdentifier16;
        Name nameIdentifier17 = Name.identifier("plusAssign");
        PLUS_ASSIGN = nameIdentifier17;
        Name nameIdentifier18 = Name.identifier("minusAssign");
        MINUS_ASSIGN = nameIdentifier18;
        UNARY_OPERATION_NAMES = SetsKt.setOf((Object[]) new Name[]{nameIdentifier, nameIdentifier2, nameIdentifier7, nameIdentifier6, nameIdentifier5});
        SIMPLE_UNARY_OPERATION_NAMES = SetsKt.setOf((Object[]) new Name[]{nameIdentifier7, nameIdentifier6, nameIdentifier5});
        BINARY_OPERATION_NAMES = SetsKt.setOf((Object[]) new Name[]{nameIdentifier8, nameIdentifier3, nameIdentifier4, nameIdentifier9, nameIdentifier10, nameIdentifier11, nameIdentifier12});
        ASSIGNMENT_OPERATIONS = SetsKt.setOf((Object[]) new Name[]{nameIdentifier13, nameIdentifier14, nameIdentifier15, nameIdentifier16, nameIdentifier17, nameIdentifier18});
    }

    private OperatorNameConventions() {
    }
}

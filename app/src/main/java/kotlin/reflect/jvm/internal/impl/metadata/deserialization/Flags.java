package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.protobuf.Internal;

/* loaded from: classes2.dex */
public class Flags {
    public static final FlagField<ProtoBuf.Class.Kind> CLASS_KIND;
    public static final BooleanFlagField DECLARES_DEFAULT_VALUE;
    public static final BooleanFlagField HAS_ANNOTATIONS;
    public static final BooleanFlagField HAS_CONSTANT;
    public static final BooleanFlagField HAS_GETTER;
    public static final BooleanFlagField HAS_SETTER;
    public static final BooleanFlagField IS_CONST;
    public static final BooleanFlagField IS_CROSSINLINE;
    public static final BooleanFlagField IS_DATA;
    public static final BooleanFlagField IS_DELEGATED;
    public static final BooleanFlagField IS_EXPECT_CLASS;
    public static final BooleanFlagField IS_EXPECT_FUNCTION;
    public static final BooleanFlagField IS_EXPECT_PROPERTY;
    public static final BooleanFlagField IS_EXTERNAL_ACCESSOR;
    public static final BooleanFlagField IS_EXTERNAL_CLASS;
    public static final BooleanFlagField IS_EXTERNAL_FUNCTION;
    public static final BooleanFlagField IS_EXTERNAL_PROPERTY;
    public static final BooleanFlagField IS_INFIX;
    public static final BooleanFlagField IS_INLINE;
    public static final BooleanFlagField IS_INLINE_ACCESSOR;
    public static final BooleanFlagField IS_INLINE_CLASS;
    public static final BooleanFlagField IS_INNER;
    public static final BooleanFlagField IS_LATEINIT;
    public static final BooleanFlagField IS_NEGATED;
    public static final BooleanFlagField IS_NOINLINE;
    public static final BooleanFlagField IS_NOT_DEFAULT;
    public static final BooleanFlagField IS_NULL_CHECK_PREDICATE;
    public static final BooleanFlagField IS_OPERATOR;
    public static final BooleanFlagField IS_SECONDARY;
    public static final BooleanFlagField IS_SUSPEND;
    public static final BooleanFlagField IS_TAILREC;
    public static final BooleanFlagField IS_UNSIGNED;
    public static final BooleanFlagField IS_VAR;
    public static final FlagField<ProtoBuf.MemberKind> MEMBER_KIND;
    public static final FlagField<ProtoBuf.Modality> MODALITY;
    public static final BooleanFlagField SUSPEND_TYPE = FlagField.booleanFirst();
    public static final FlagField<ProtoBuf.Visibility> VISIBILITY;

    static {
        BooleanFlagField booleanFlagFieldBooleanFirst = FlagField.booleanFirst();
        HAS_ANNOTATIONS = booleanFlagFieldBooleanFirst;
        FlagField<ProtoBuf.Visibility> flagFieldAfter = FlagField.after(booleanFlagFieldBooleanFirst, ProtoBuf.Visibility.values());
        VISIBILITY = flagFieldAfter;
        FlagField<ProtoBuf.Modality> flagFieldAfter2 = FlagField.after(flagFieldAfter, ProtoBuf.Modality.values());
        MODALITY = flagFieldAfter2;
        FlagField<ProtoBuf.Class.Kind> flagFieldAfter3 = FlagField.after(flagFieldAfter2, ProtoBuf.Class.Kind.values());
        CLASS_KIND = flagFieldAfter3;
        BooleanFlagField booleanFlagFieldBooleanAfter = FlagField.booleanAfter(flagFieldAfter3);
        IS_INNER = booleanFlagFieldBooleanAfter;
        BooleanFlagField booleanFlagFieldBooleanAfter2 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter);
        IS_DATA = booleanFlagFieldBooleanAfter2;
        BooleanFlagField booleanFlagFieldBooleanAfter3 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter2);
        IS_EXTERNAL_CLASS = booleanFlagFieldBooleanAfter3;
        BooleanFlagField booleanFlagFieldBooleanAfter4 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter3);
        IS_EXPECT_CLASS = booleanFlagFieldBooleanAfter4;
        IS_INLINE_CLASS = FlagField.booleanAfter(booleanFlagFieldBooleanAfter4);
        IS_SECONDARY = FlagField.booleanAfter(flagFieldAfter);
        FlagField<ProtoBuf.MemberKind> flagFieldAfter4 = FlagField.after(flagFieldAfter2, ProtoBuf.MemberKind.values());
        MEMBER_KIND = flagFieldAfter4;
        BooleanFlagField booleanFlagFieldBooleanAfter5 = FlagField.booleanAfter(flagFieldAfter4);
        IS_OPERATOR = booleanFlagFieldBooleanAfter5;
        BooleanFlagField booleanFlagFieldBooleanAfter6 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter5);
        IS_INFIX = booleanFlagFieldBooleanAfter6;
        BooleanFlagField booleanFlagFieldBooleanAfter7 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter6);
        IS_INLINE = booleanFlagFieldBooleanAfter7;
        BooleanFlagField booleanFlagFieldBooleanAfter8 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter7);
        IS_TAILREC = booleanFlagFieldBooleanAfter8;
        BooleanFlagField booleanFlagFieldBooleanAfter9 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter8);
        IS_EXTERNAL_FUNCTION = booleanFlagFieldBooleanAfter9;
        BooleanFlagField booleanFlagFieldBooleanAfter10 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter9);
        IS_SUSPEND = booleanFlagFieldBooleanAfter10;
        IS_EXPECT_FUNCTION = FlagField.booleanAfter(booleanFlagFieldBooleanAfter10);
        BooleanFlagField booleanFlagFieldBooleanAfter11 = FlagField.booleanAfter(flagFieldAfter4);
        IS_VAR = booleanFlagFieldBooleanAfter11;
        BooleanFlagField booleanFlagFieldBooleanAfter12 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter11);
        HAS_GETTER = booleanFlagFieldBooleanAfter12;
        BooleanFlagField booleanFlagFieldBooleanAfter13 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter12);
        HAS_SETTER = booleanFlagFieldBooleanAfter13;
        BooleanFlagField booleanFlagFieldBooleanAfter14 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter13);
        IS_CONST = booleanFlagFieldBooleanAfter14;
        BooleanFlagField booleanFlagFieldBooleanAfter15 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter14);
        IS_LATEINIT = booleanFlagFieldBooleanAfter15;
        BooleanFlagField booleanFlagFieldBooleanAfter16 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter15);
        HAS_CONSTANT = booleanFlagFieldBooleanAfter16;
        BooleanFlagField booleanFlagFieldBooleanAfter17 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter16);
        IS_EXTERNAL_PROPERTY = booleanFlagFieldBooleanAfter17;
        BooleanFlagField booleanFlagFieldBooleanAfter18 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter17);
        IS_DELEGATED = booleanFlagFieldBooleanAfter18;
        IS_EXPECT_PROPERTY = FlagField.booleanAfter(booleanFlagFieldBooleanAfter18);
        BooleanFlagField booleanFlagFieldBooleanAfter19 = FlagField.booleanAfter(booleanFlagFieldBooleanFirst);
        DECLARES_DEFAULT_VALUE = booleanFlagFieldBooleanAfter19;
        BooleanFlagField booleanFlagFieldBooleanAfter20 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter19);
        IS_CROSSINLINE = booleanFlagFieldBooleanAfter20;
        IS_NOINLINE = FlagField.booleanAfter(booleanFlagFieldBooleanAfter20);
        BooleanFlagField booleanFlagFieldBooleanAfter21 = FlagField.booleanAfter(flagFieldAfter2);
        IS_NOT_DEFAULT = booleanFlagFieldBooleanAfter21;
        BooleanFlagField booleanFlagFieldBooleanAfter22 = FlagField.booleanAfter(booleanFlagFieldBooleanAfter21);
        IS_EXTERNAL_ACCESSOR = booleanFlagFieldBooleanAfter22;
        IS_INLINE_ACCESSOR = FlagField.booleanAfter(booleanFlagFieldBooleanAfter22);
        BooleanFlagField booleanFlagFieldBooleanFirst2 = FlagField.booleanFirst();
        IS_NEGATED = booleanFlagFieldBooleanFirst2;
        IS_NULL_CHECK_PREDICATE = FlagField.booleanAfter(booleanFlagFieldBooleanFirst2);
        IS_UNSIGNED = FlagField.booleanFirst();
    }

    public static abstract class FlagField<E> {
        public final int bitWidth;
        public final int offset;

        public abstract E get(int i);

        /* JADX WARN: Incorrect types in method signature: <E::Lkotlin/reflect/jvm/internal/impl/protobuf/Internal$EnumLite;>(Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/Flags$FlagField<*>;[TE;)Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/Flags$FlagField<TE;>; */
        public static FlagField after(FlagField flagField, Internal.EnumLite[] enumLiteArr) {
            return new EnumLiteFlagField(flagField.offset + flagField.bitWidth, enumLiteArr);
        }

        public static BooleanFlagField booleanFirst() {
            return new BooleanFlagField(0);
        }

        public static BooleanFlagField booleanAfter(FlagField<?> flagField) {
            return new BooleanFlagField(flagField.offset + flagField.bitWidth);
        }

        private FlagField(int i, int i2) {
            this.offset = i;
            this.bitWidth = i2;
        }
    }

    public static class BooleanFlagField extends FlagField<Boolean> {
        public BooleanFlagField(int i) {
            super(i, 1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.FlagField
        public Boolean get(int i) {
            return Boolean.valueOf((i & (1 << this.offset)) != 0);
        }
    }

    private static class EnumLiteFlagField<E extends Internal.EnumLite> extends FlagField<E> {
        private final E[] values;

        public EnumLiteFlagField(int i, E[] eArr) {
            super(i, bitWidth(eArr));
            this.values = eArr;
        }

        private static <E> int bitWidth(E[] eArr) {
            int length = eArr.length - 1;
            if (length == 0) {
                return 1;
            }
            for (int i = 31; i >= 0; i--) {
                if (((1 << i) & length) != 0) {
                    return i + 1;
                }
            }
            throw new IllegalStateException("Empty enum: " + eArr.getClass());
        }

        @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.FlagField
        public E get(int i) {
            int i2 = (i & (((1 << this.bitWidth) - 1) << this.offset)) >> this.offset;
            for (E e : this.values) {
                if (e.getNumber() == i2) {
                    return e;
                }
            }
            return null;
        }
    }
}

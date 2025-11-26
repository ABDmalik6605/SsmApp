package com.integratedbiometrics.ibscanmatcher;

/* loaded from: classes2.dex */
public class IBMatcherException extends Exception {
    private final Type type;

    public enum Type {
        INVALID_PARAM_VALUE(-1),
        MEM_ALLOC(-2),
        NOT_SUPPORTED(-3),
        FILE_OPEN(-4),
        FILE_READ(-5),
        RESOURCE_LOCKED(-6),
        MISSING_RESOURCE(-7),
        INVALID_ACCESS_POINTER(-8),
        THREAD_CREATE(-9),
        COMMAND_FAILED(-10),
        FILE_SAVE(-11),
        OPEN_MATCHER_FAILED(-600),
        CLOSE_MATCHER_FAILED(-601),
        NO_MATCHER_INSTANCE(-602),
        INVALID_HANDLE(-603),
        EXTRACTION_FAILED(-604),
        ENROLLMENT_FAILED(-605),
        MATCHING_FAILED(-606),
        COMPRESSION_FAILED(-607),
        DECOMPRESSION_FAILED(-608),
        CONVERT_FAILED(-609),
        THERE_IS_NO_DATA(-610),
        NOT_SUPPORTED_FUNCTION(-611),
        NOT_SUPPORTED_IMAGE_FORMAT(-612),
        NOT_SUPPORTED_DEVICE_TYPE(-613),
        INCORRECT_ISO_FILE(-700);

        private final int code;

        Type(int i) {
            this.code = i;
        }

        protected static Type fromCode(int i) {
            for (Type type : values()) {
                if (type.code == i) {
                    return type;
                }
            }
            return null;
        }

        protected int toCode() {
            return this.code;
        }
    }

    IBMatcherException(Type type) {
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }
}

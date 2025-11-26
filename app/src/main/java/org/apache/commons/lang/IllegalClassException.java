package org.apache.commons.lang;

/* loaded from: classes3.dex */
public class IllegalClassException extends IllegalArgumentException {
    private static final long serialVersionUID = 8063272569377254819L;

    public IllegalClassException(Class cls, Object obj) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Expected: ");
        stringBuffer.append(safeGetClassName(cls));
        stringBuffer.append(", actual: ");
        stringBuffer.append(obj == null ? "null" : obj.getClass().getName());
        super(stringBuffer.toString());
    }

    public IllegalClassException(Class cls, Class cls2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Expected: ");
        stringBuffer.append(safeGetClassName(cls));
        stringBuffer.append(", actual: ");
        stringBuffer.append(safeGetClassName(cls2));
        super(stringBuffer.toString());
    }

    public IllegalClassException(String str) {
        super(str);
    }

    private static final String safeGetClassName(Class cls) {
        if (cls == null) {
            return null;
        }
        return cls.getName();
    }
}

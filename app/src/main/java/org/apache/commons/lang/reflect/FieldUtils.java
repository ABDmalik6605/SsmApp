package org.apache.commons.lang.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import org.apache.commons.lang.ClassUtils;

/* loaded from: classes3.dex */
public class FieldUtils {
    public static Field getField(Class cls, String str) throws NoSuchFieldException, SecurityException {
        Field field = getField(cls, str, false);
        MemberUtils.setAccessibleWorkaround(field);
        return field;
    }

    public static Field getField(Class cls, String str, boolean z) throws NoSuchFieldException {
        Field field;
        Field declaredField;
        if (cls == null) {
            throw new IllegalArgumentException("The class must not be null");
        }
        if (str == null) {
            throw new IllegalArgumentException("The field name must not be null");
        }
        for (Class superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
            try {
                declaredField = superclass.getDeclaredField(str);
            } catch (NoSuchFieldException unused) {
            }
            if (!Modifier.isPublic(declaredField.getModifiers())) {
                if (!z) {
                    continue;
                } else {
                    declaredField.setAccessible(true);
                }
            }
            return declaredField;
        }
        Field field2 = null;
        Iterator it = ClassUtils.getAllInterfaces(cls).iterator();
        while (it.hasNext()) {
            try {
                field = ((Class) it.next()).getField(str);
            } catch (NoSuchFieldException unused2) {
            }
            if (field2 != null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Reference to field ");
                stringBuffer.append(str);
                stringBuffer.append(" is ambiguous relative to ");
                stringBuffer.append(cls);
                stringBuffer.append("; a matching field exists on two or more implemented interfaces.");
                throw new IllegalArgumentException(stringBuffer.toString());
            }
            field2 = field;
        }
        return field2;
    }

    public static Field getDeclaredField(Class cls, String str) {
        return getDeclaredField(cls, str, false);
    }

    public static Field getDeclaredField(Class cls, String str, boolean z) throws NoSuchFieldException {
        if (cls == null) {
            throw new IllegalArgumentException("The class must not be null");
        }
        if (str == null) {
            throw new IllegalArgumentException("The field name must not be null");
        }
        try {
            Field declaredField = cls.getDeclaredField(str);
            if (!MemberUtils.isAccessible(declaredField)) {
                if (!z) {
                    return null;
                }
                declaredField.setAccessible(true);
            }
            return declaredField;
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }

    public static Object readStaticField(Field field) throws IllegalAccessException {
        return readStaticField(field, false);
    }

    public static Object readStaticField(Field field, boolean z) throws IllegalAccessException {
        if (field == null) {
            throw new IllegalArgumentException("The field must not be null");
        }
        if (!Modifier.isStatic(field.getModifiers())) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("The field '");
            stringBuffer.append(field.getName());
            stringBuffer.append("' is not static");
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        return readField(field, (Object) null, z);
    }

    public static Object readStaticField(Class cls, String str) throws IllegalAccessException {
        return readStaticField(cls, str, false);
    }

    public static Object readStaticField(Class cls, String str, boolean z) throws IllegalAccessException, NoSuchFieldException {
        Field field = getField(cls, str, z);
        if (field == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot locate field ");
            stringBuffer.append(str);
            stringBuffer.append(" on ");
            stringBuffer.append(cls);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        return readStaticField(field, false);
    }

    public static Object readDeclaredStaticField(Class cls, String str) throws IllegalAccessException {
        return readDeclaredStaticField(cls, str, false);
    }

    public static Object readDeclaredStaticField(Class cls, String str, boolean z) throws IllegalAccessException, NoSuchFieldException {
        Field declaredField = getDeclaredField(cls, str, z);
        if (declaredField == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot locate declared field ");
            stringBuffer.append(cls.getName());
            stringBuffer.append(".");
            stringBuffer.append(str);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        return readStaticField(declaredField, false);
    }

    public static Object readField(Field field, Object obj) throws IllegalAccessException {
        return readField(field, obj, false);
    }

    public static Object readField(Field field, Object obj, boolean z) throws IllegalAccessException, SecurityException {
        if (field == null) {
            throw new IllegalArgumentException("The field must not be null");
        }
        if (z && !field.isAccessible()) {
            field.setAccessible(true);
        } else {
            MemberUtils.setAccessibleWorkaround(field);
        }
        return field.get(obj);
    }

    public static Object readField(Object obj, String str) throws IllegalAccessException {
        return readField(obj, str, false);
    }

    public static Object readField(Object obj, String str, boolean z) throws IllegalAccessException, NoSuchFieldException {
        if (obj == null) {
            throw new IllegalArgumentException("target object must not be null");
        }
        Class<?> cls = obj.getClass();
        Field field = getField(cls, str, z);
        if (field == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot locate field ");
            stringBuffer.append(str);
            stringBuffer.append(" on ");
            stringBuffer.append(cls);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        return readField(field, obj);
    }

    public static Object readDeclaredField(Object obj, String str) throws IllegalAccessException {
        return readDeclaredField(obj, str, false);
    }

    public static Object readDeclaredField(Object obj, String str, boolean z) throws IllegalAccessException, NoSuchFieldException {
        if (obj == null) {
            throw new IllegalArgumentException("target object must not be null");
        }
        Class<?> cls = obj.getClass();
        Field declaredField = getDeclaredField(cls, str, z);
        if (declaredField == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot locate declared field ");
            stringBuffer.append(cls.getName());
            stringBuffer.append(".");
            stringBuffer.append(str);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        return readField(declaredField, obj);
    }

    public static void writeStaticField(Field field, Object obj) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        writeStaticField(field, obj, false);
    }

    public static void writeStaticField(Field field, Object obj, boolean z) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        if (field == null) {
            throw new IllegalArgumentException("The field must not be null");
        }
        if (!Modifier.isStatic(field.getModifiers())) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("The field '");
            stringBuffer.append(field.getName());
            stringBuffer.append("' is not static");
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        writeField(field, (Object) null, obj, z);
    }

    public static void writeStaticField(Class cls, String str, Object obj) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        writeStaticField(cls, str, obj, false);
    }

    public static void writeStaticField(Class cls, String str, Object obj, boolean z) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        Field field = getField(cls, str, z);
        if (field == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot locate field ");
            stringBuffer.append(str);
            stringBuffer.append(" on ");
            stringBuffer.append(cls);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        writeStaticField(field, obj);
    }

    public static void writeDeclaredStaticField(Class cls, String str, Object obj) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        writeDeclaredStaticField(cls, str, obj, false);
    }

    public static void writeDeclaredStaticField(Class cls, String str, Object obj, boolean z) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        Field declaredField = getDeclaredField(cls, str, z);
        if (declaredField == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot locate declared field ");
            stringBuffer.append(cls.getName());
            stringBuffer.append(".");
            stringBuffer.append(str);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        writeField(declaredField, (Object) null, obj);
    }

    public static void writeField(Field field, Object obj, Object obj2) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        writeField(field, obj, obj2, false);
    }

    public static void writeField(Field field, Object obj, Object obj2, boolean z) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        if (field == null) {
            throw new IllegalArgumentException("The field must not be null");
        }
        if (z && !field.isAccessible()) {
            field.setAccessible(true);
        } else {
            MemberUtils.setAccessibleWorkaround(field);
        }
        field.set(obj, obj2);
    }

    public static void writeField(Object obj, String str, Object obj2) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        writeField(obj, str, obj2, false);
    }

    public static void writeField(Object obj, String str, Object obj2, boolean z) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException("target object must not be null");
        }
        Class<?> cls = obj.getClass();
        Field field = getField(cls, str, z);
        if (field == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot locate declared field ");
            stringBuffer.append(cls.getName());
            stringBuffer.append(".");
            stringBuffer.append(str);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        writeField(field, obj, obj2);
    }

    public static void writeDeclaredField(Object obj, String str, Object obj2) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        writeDeclaredField(obj, str, obj2, false);
    }

    public static void writeDeclaredField(Object obj, String str, Object obj2, boolean z) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException("target object must not be null");
        }
        Class<?> cls = obj.getClass();
        Field declaredField = getDeclaredField(cls, str, z);
        if (declaredField == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot locate declared field ");
            stringBuffer.append(cls.getName());
            stringBuffer.append(".");
            stringBuffer.append(str);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        writeField(declaredField, obj, obj2);
    }
}

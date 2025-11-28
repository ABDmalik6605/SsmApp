package org.apache.commons.lang.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;

/* loaded from: classes3.dex */
public class ConstructorUtils {
    public static Object invokeConstructor(Class cls, Object obj) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        return invokeConstructor(cls, new Object[]{obj});
    }

    public static Object invokeConstructor(Class cls, Object[] objArr) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        if (objArr == null) {
            objArr = ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        Class[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return invokeConstructor(cls, objArr, clsArr);
    }

    public static Object invokeConstructor(Class cls, Object[] objArr, Class[] clsArr) throws IllegalAccessException, NoSuchMethodException, InstantiationException, SecurityException, InvocationTargetException {
        if (clsArr == null) {
            clsArr = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        if (objArr == null) {
            objArr = ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        Constructor matchingAccessibleConstructor = getMatchingAccessibleConstructor(cls, clsArr);
        if (matchingAccessibleConstructor == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("No such accessible constructor on object: ");
            stringBuffer.append(cls.getName());
            throw new NoSuchMethodException(stringBuffer.toString());
        }
        return matchingAccessibleConstructor.newInstance(objArr);
    }

    public static Object invokeExactConstructor(Class cls, Object obj) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        return invokeExactConstructor(cls, new Object[]{obj});
    }

    public static Object invokeExactConstructor(Class cls, Object[] objArr) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        if (objArr == null) {
            objArr = ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        int length = objArr.length;
        Class[] clsArr = new Class[length];
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return invokeExactConstructor(cls, objArr, clsArr);
    }

    public static Object invokeExactConstructor(Class cls, Object[] objArr, Class[] clsArr) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        if (objArr == null) {
            objArr = ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        if (clsArr == null) {
            clsArr = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        Constructor accessibleConstructor = getAccessibleConstructor(cls, clsArr);
        if (accessibleConstructor == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("No such accessible constructor on object: ");
            stringBuffer.append(cls.getName());
            throw new NoSuchMethodException(stringBuffer.toString());
        }
        return accessibleConstructor.newInstance(objArr);
    }

    public static Constructor getAccessibleConstructor(Class cls, Class cls2) {
        return getAccessibleConstructor(cls, new Class[]{cls2});
    }

    public static Constructor getAccessibleConstructor(Class cls, Class[] clsArr) {
        try {
            return getAccessibleConstructor(cls.getConstructor(clsArr));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static Constructor getAccessibleConstructor(Constructor constructor) {
        if (MemberUtils.isAccessible(constructor) && Modifier.isPublic(constructor.getDeclaringClass().getModifiers())) {
            return constructor;
        }
        return null;
    }

    public static Constructor getMatchingAccessibleConstructor(Class cls, Class[] clsArr) throws NoSuchMethodException, SecurityException {
        Constructor accessibleConstructor;
        try {
            Constructor constructor = cls.getConstructor(clsArr);
            MemberUtils.setAccessibleWorkaround(constructor);
            return constructor;
        } catch (NoSuchMethodException unused) {
            Constructor constructor2 = null;
            Constructor<?>[] constructors = cls.getConstructors();
            for (int i = 0; i < constructors.length; i++) {
                if (ClassUtils.isAssignable(clsArr, (Class[]) constructors[i].getParameterTypes(), true) && (accessibleConstructor = getAccessibleConstructor(constructors[i])) != null) {
                    MemberUtils.setAccessibleWorkaround(accessibleConstructor);
                    if (constructor2 == null || MemberUtils.compareParameterTypes(accessibleConstructor.getParameterTypes(), constructor2.getParameterTypes(), clsArr) < 0) {
                        constructor2 = accessibleConstructor;
                    }
                }
            }
            return constructor2;
        }
    }
}
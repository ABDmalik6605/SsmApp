package org.apache.commons.lang;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class SerializationUtils {
    public static Object clone(Serializable serializable) {
        return deserialize(serialize(serializable));
    }

    public static void serialize(Serializable serializable, OutputStream outputStream) throws Throwable {
        if (outputStream == null) {
            throw new IllegalArgumentException("The OutputStream must not be null");
        }
        ObjectOutputStream objectOutputStream = null;
        try {
            try {
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(outputStream);
                try {
                    objectOutputStream2.writeObject(serializable);
                    try {
                        objectOutputStream2.close();
                    } catch (IOException unused) {
                    }
                } catch (IOException e) {
                    e = e;
                    objectOutputStream = objectOutputStream2;
                    throw new SerializationException(e);
                } catch (Throwable th) {
                    th = th;
                    objectOutputStream = objectOutputStream2;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    public static byte[] serialize(Serializable serializable) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        serialize(serializable, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Object deserialize(InputStream inputStream) throws Throwable {
        if (inputStream == null) {
            throw new IllegalArgumentException("The InputStream must not be null");
        }
        ObjectInputStream objectInputStream = null;
        try {
            try {
                ObjectInputStream objectInputStream2 = new ObjectInputStream(inputStream);
                try {
                    Object object = objectInputStream2.readObject();
                    try {
                        objectInputStream2.close();
                    } catch (IOException unused) {
                    }
                    return object;
                } catch (IOException e) {
                    e = e;
                    throw new SerializationException(e);
                } catch (ClassNotFoundException e2) {
                    e = e2;
                    throw new SerializationException(e);
                } catch (Throwable th) {
                    th = th;
                    objectInputStream = objectInputStream2;
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
            } catch (ClassNotFoundException e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static Object deserialize(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("The byte[] must not be null");
        }
        return deserialize(new ByteArrayInputStream(bArr));
    }
}
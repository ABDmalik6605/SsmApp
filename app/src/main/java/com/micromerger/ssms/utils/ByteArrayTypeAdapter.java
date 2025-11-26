package com.micromerger.ssms.utils;

import com.google.common.io.BaseEncoding;
import com.google.common.io.ByteStreams;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.ByteArrayInputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class ByteArrayTypeAdapter extends TypeAdapter<byte[]> {
    private static final Method beforeValueMethod;
    private static final TypeAdapter<byte[]> byteArrayTypeAdapter;
    private static final Method writeDeferredNameMethod;
    private static final Field writerField;

    static {
        try {
            Method declaredMethod = JsonWriter.class.getDeclaredMethod("writeDeferredName", new Class[0]);
            writeDeferredNameMethod = declaredMethod;
            declaredMethod.setAccessible(true);
            Method declaredMethod2 = JsonWriter.class.getDeclaredMethod("beforeValue", new Class[0]);
            beforeValueMethod = declaredMethod2;
            declaredMethod2.setAccessible(true);
            Field declaredField = JsonWriter.class.getDeclaredField("out");
            writerField = declaredField;
            declaredField.setAccessible(true);
            byteArrayTypeAdapter = new ByteArrayTypeAdapter();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            throw new RuntimeException(e2);
        }
    }

    private ByteArrayTypeAdapter() {
    }

    public static TypeAdapter<byte[]> getByteArrayTypeAdapter() {
        return byteArrayTypeAdapter;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(final JsonWriter out, final byte[] bytes) throws IOException, IllegalArgumentException {
        try {
            writeDeferredNameAndFlush(out);
            writeRawBase64ValueAndFlush(bytes, (Writer) writerField.get(out));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IOException(e);
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            throw new IOException(e2);
        }
    }

    @Override // com.google.gson.TypeAdapter
    /* renamed from: read, reason: avoid collision after fix types in other method */
    public byte[] read2(final JsonReader in) {
        throw new UnsupportedOperationException();
    }

    private static void writeDeferredNameAndFlush(final Flushable out) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        writeDeferredNameMethod.invoke(out, new Object[0]);
        beforeValueMethod.invoke(out, new Object[0]);
        out.flush();
    }

    private static void writeRawBase64ValueAndFlush(final byte[] bytes, final Writer writer) throws IOException {
        writer.write(34);
        OutputStream outputStreamEncodingStream = BaseEncoding.base64().encodingStream(writer);
        ByteStreams.copy(new ByteArrayInputStream(bytes), outputStreamEncodingStream);
        outputStreamEncodingStream.close();
        writer.write(34);
        writer.flush();
    }
}

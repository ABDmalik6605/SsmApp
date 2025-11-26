package com.google.common.io;

import com.google.common.base.Preconditions;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.stream.Stream;

/* loaded from: classes2.dex */
public abstract class CharSink {
    public abstract Writer openStream() throws IOException;

    protected CharSink() {
    }

    public Writer openBufferedStream() throws IOException {
        Writer writerOpenStream = openStream();
        return writerOpenStream instanceof BufferedWriter ? (BufferedWriter) writerOpenStream : new BufferedWriter(writerOpenStream);
    }

    public void write(CharSequence charSequence) throws Throwable {
        Preconditions.checkNotNull(charSequence);
        try {
            Writer writer = (Writer) Closer.create().register(openStream());
            writer.append(charSequence);
            writer.flush();
        } finally {
        }
    }

    public void writeLines(Iterable<? extends CharSequence> iterable) throws IOException {
        writeLines(iterable, System.getProperty("line.separator"));
    }

    public void writeLines(Iterable<? extends CharSequence> iterable, String str) throws IOException {
        writeLines(iterable.iterator(), str);
    }

    public void writeLines(Stream<? extends CharSequence> stream) throws IOException {
        writeLines(stream, System.getProperty("line.separator"));
    }

    public void writeLines(Stream<? extends CharSequence> stream, String str) throws IOException {
        writeLines(stream.iterator(), str);
    }

    private void writeLines(Iterator<? extends CharSequence> it, String str) throws IOException {
        Preconditions.checkNotNull(str);
        Writer writerOpenBufferedStream = openBufferedStream();
        while (it.hasNext()) {
            try {
                writerOpenBufferedStream.append(it.next()).append((CharSequence) str);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (writerOpenBufferedStream != null) {
                        if (th != null) {
                            try {
                                writerOpenBufferedStream.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        } else {
                            writerOpenBufferedStream.close();
                        }
                    }
                    throw th2;
                }
            }
        }
        if (writerOpenBufferedStream != null) {
            writerOpenBufferedStream.close();
        }
    }

    public long writeFrom(Readable readable) throws Throwable {
        Preconditions.checkNotNull(readable);
        try {
            Writer writer = (Writer) Closer.create().register(openStream());
            long jCopy = CharStreams.copy(readable, writer);
            writer.flush();
            return jCopy;
        } finally {
        }
    }
}

package com.google.common.io;

import java.nio.file.FileSystemException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
public final class InsecureRecursiveDeleteException extends FileSystemException {
    public InsecureRecursiveDeleteException(@NullableDecl String str) {
        super(str, null, "unable to guarantee security of recursive delete");
    }
}

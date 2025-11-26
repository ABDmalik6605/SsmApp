package androidx.sqlite.db;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SupportSQLiteDatabase.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a>\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00010\u0006¢\u0006\u0002\b\u0007H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"transaction", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/sqlite/db/SupportSQLiteDatabase;", "exclusive", "", "body", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/sqlite/db/SupportSQLiteDatabase;ZLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "sqlite-ktx_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SupportSQLiteDatabaseKt {
    public static /* synthetic */ Object transaction$default(SupportSQLiteDatabase supportSQLiteDatabase, boolean z, Function1 body, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "<this>");
        Intrinsics.checkNotNullParameter(body, "body");
        if (z) {
            supportSQLiteDatabase.beginTransaction();
        } else {
            supportSQLiteDatabase.beginTransactionNonExclusive();
        }
        try {
            Object objInvoke = body.invoke(supportSQLiteDatabase);
            InlineMarker.finallyStart(1);
            supportSQLiteDatabase.setTransactionSuccessful();
            supportSQLiteDatabase.endTransaction();
            InlineMarker.finallyEnd(1);
            return objInvoke;
        } finally {
        }
    }

    public static final <T> T transaction(SupportSQLiteDatabase supportSQLiteDatabase, boolean z, Function1<? super SupportSQLiteDatabase, ? extends T> body) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "<this>");
        Intrinsics.checkNotNullParameter(body, "body");
        if (z) {
            supportSQLiteDatabase.beginTransaction();
        } else {
            supportSQLiteDatabase.beginTransactionNonExclusive();
        }
        try {
            T tInvoke = body.invoke(supportSQLiteDatabase);
            InlineMarker.finallyStart(1);
            supportSQLiteDatabase.setTransactionSuccessful();
            supportSQLiteDatabase.endTransaction();
            InlineMarker.finallyEnd(1);
            return tInvoke;
        } finally {
        }
    }
}

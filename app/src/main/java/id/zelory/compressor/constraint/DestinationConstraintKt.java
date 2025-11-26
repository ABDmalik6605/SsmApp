package id.zelory.compressor.constraint;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DestinationConstraint.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0000\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {FirebaseAnalytics.Param.DESTINATION, "", "Lid/zelory/compressor/constraint/Compression;", "Ljava/io/File;", "compressor_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class DestinationConstraintKt {
    public static final void destination(Compression destination, File destination2) {
        Intrinsics.checkParameterIsNotNull(destination, "$this$destination");
        Intrinsics.checkParameterIsNotNull(destination2, "destination");
        destination.constraint(new DestinationConstraint(destination2));
    }
}

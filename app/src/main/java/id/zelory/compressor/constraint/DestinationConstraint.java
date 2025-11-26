package id.zelory.compressor.constraint;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DestinationConstraint.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lid/zelory/compressor/constraint/DestinationConstraint;", "Lid/zelory/compressor/constraint/Constraint;", FirebaseAnalytics.Param.DESTINATION, "Ljava/io/File;", "(Ljava/io/File;)V", "isSatisfied", "", "imageFile", "satisfy", "compressor_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class DestinationConstraint implements Constraint {
    private final File destination;

    public DestinationConstraint(File destination) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        this.destination = destination;
    }

    @Override // id.zelory.compressor.constraint.Constraint
    public boolean isSatisfied(File imageFile) {
        Intrinsics.checkParameterIsNotNull(imageFile, "imageFile");
        return Intrinsics.areEqual(imageFile.getAbsolutePath(), this.destination.getAbsolutePath());
    }

    @Override // id.zelory.compressor.constraint.Constraint
    public File satisfy(File imageFile) {
        Intrinsics.checkParameterIsNotNull(imageFile, "imageFile");
        return FilesKt.copyTo$default(imageFile, this.destination, true, 0, 4, null);
    }
}

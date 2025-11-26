package id.zelory.compressor.constraint;

import id.zelory.compressor.UtilKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QualityConstraint.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lid/zelory/compressor/constraint/QualityConstraint;", "Lid/zelory/compressor/constraint/Constraint;", "quality", "", "(I)V", "isResolved", "", "isSatisfied", "imageFile", "Ljava/io/File;", "satisfy", "compressor_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class QualityConstraint implements Constraint {
    private boolean isResolved;
    private final int quality;

    public QualityConstraint(int i) {
        this.quality = i;
    }

    @Override // id.zelory.compressor.constraint.Constraint
    public boolean isSatisfied(File imageFile) {
        Intrinsics.checkParameterIsNotNull(imageFile, "imageFile");
        return this.isResolved;
    }

    @Override // id.zelory.compressor.constraint.Constraint
    public File satisfy(File imageFile) {
        Intrinsics.checkParameterIsNotNull(imageFile, "imageFile");
        File fileOverWrite$default = UtilKt.overWrite$default(imageFile, UtilKt.loadBitmap(imageFile), null, this.quality, 4, null);
        this.isResolved = true;
        return fileOverWrite$default;
    }
}

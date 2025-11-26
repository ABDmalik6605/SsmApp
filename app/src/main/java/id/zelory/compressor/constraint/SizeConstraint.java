package id.zelory.compressor.constraint;

import id.zelory.compressor.UtilKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SizeConstraint.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lid/zelory/compressor/constraint/SizeConstraint;", "Lid/zelory/compressor/constraint/Constraint;", "maxFileSize", "", "stepSize", "", "maxIteration", "minQuality", "(JIII)V", "iteration", "isSatisfied", "", "imageFile", "Ljava/io/File;", "satisfy", "compressor_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class SizeConstraint implements Constraint {
    private int iteration;
    private final long maxFileSize;
    private final int maxIteration;
    private final int minQuality;
    private final int stepSize;

    public SizeConstraint(long j, int i, int i2, int i3) {
        this.maxFileSize = j;
        this.stepSize = i;
        this.maxIteration = i2;
        this.minQuality = i3;
    }

    public /* synthetic */ SizeConstraint(long j, int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, (i4 & 2) != 0 ? 10 : i, (i4 & 4) != 0 ? 10 : i2, (i4 & 8) != 0 ? 10 : i3);
    }

    @Override // id.zelory.compressor.constraint.Constraint
    public boolean isSatisfied(File imageFile) {
        Intrinsics.checkParameterIsNotNull(imageFile, "imageFile");
        return imageFile.length() <= this.maxFileSize || this.iteration >= this.maxIteration;
    }

    @Override // id.zelory.compressor.constraint.Constraint
    public File satisfy(File imageFile) {
        Intrinsics.checkParameterIsNotNull(imageFile, "imageFile");
        int i = this.iteration + 1;
        this.iteration = i;
        Integer numValueOf = Integer.valueOf(100 - (i * this.stepSize));
        int iIntValue = numValueOf.intValue();
        int iIntValue2 = this.minQuality;
        if (!(iIntValue >= iIntValue2)) {
            numValueOf = null;
        }
        if (numValueOf != null) {
            iIntValue2 = numValueOf.intValue();
        }
        return UtilKt.overWrite$default(imageFile, UtilKt.loadBitmap(imageFile), null, iIntValue2, 4, null);
    }
}

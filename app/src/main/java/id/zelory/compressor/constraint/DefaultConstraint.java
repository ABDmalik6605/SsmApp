package id.zelory.compressor.constraint;

import android.graphics.Bitmap;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.fourmob.datetimepicker.date.SimpleMonthView;
import id.zelory.compressor.UtilKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultConstraint.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lid/zelory/compressor/constraint/DefaultConstraint;", "Lid/zelory/compressor/constraint/Constraint;", "width", "", SimpleMonthView.VIEW_PARAMS_HEIGHT, "format", "Landroid/graphics/Bitmap$CompressFormat;", "quality", "(IILandroid/graphics/Bitmap$CompressFormat;I)V", "isResolved", "", "isSatisfied", "imageFile", "Ljava/io/File;", "satisfy", "compressor_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class DefaultConstraint implements Constraint {
    private final Bitmap.CompressFormat format;
    private final int height;
    private boolean isResolved;
    private final int quality;
    private final int width;

    public DefaultConstraint() {
        this(0, 0, null, 0, 15, null);
    }

    public DefaultConstraint(int i, int i2, Bitmap.CompressFormat format, int i3) {
        Intrinsics.checkParameterIsNotNull(format, "format");
        this.width = i;
        this.height = i2;
        this.format = format;
        this.quality = i3;
    }

    public /* synthetic */ DefaultConstraint(int i, int i2, Bitmap.CompressFormat compressFormat, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_ID : i, (i4 & 2) != 0 ? 816 : i2, (i4 & 4) != 0 ? Bitmap.CompressFormat.JPEG : compressFormat, (i4 & 8) != 0 ? 80 : i3);
    }

    @Override // id.zelory.compressor.constraint.Constraint
    public boolean isSatisfied(File imageFile) {
        Intrinsics.checkParameterIsNotNull(imageFile, "imageFile");
        return this.isResolved;
    }

    @Override // id.zelory.compressor.constraint.Constraint
    public File satisfy(File imageFile) throws Throwable {
        Intrinsics.checkParameterIsNotNull(imageFile, "imageFile");
        File fileOverWrite = UtilKt.overWrite(imageFile, UtilKt.determineImageRotation(imageFile, UtilKt.decodeSampledBitmapFromFile(imageFile, this.width, this.height)), this.format, this.quality);
        this.isResolved = true;
        return fileOverWrite;
    }
}

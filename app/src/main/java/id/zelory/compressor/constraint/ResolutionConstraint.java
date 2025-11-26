package id.zelory.compressor.constraint;

import android.graphics.BitmapFactory;
import com.fourmob.datetimepicker.date.SimpleMonthView;
import id.zelory.compressor.UtilKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ResolutionConstraint.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lid/zelory/compressor/constraint/ResolutionConstraint;", "Lid/zelory/compressor/constraint/Constraint;", "width", "", SimpleMonthView.VIEW_PARAMS_HEIGHT, "(II)V", "isSatisfied", "", "imageFile", "Ljava/io/File;", "satisfy", "compressor_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ResolutionConstraint implements Constraint {
    private final int height;
    private final int width;

    public ResolutionConstraint(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    @Override // id.zelory.compressor.constraint.Constraint
    public boolean isSatisfied(File imageFile) {
        Intrinsics.checkParameterIsNotNull(imageFile, "imageFile");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
        return UtilKt.calculateInSampleSize(options, this.width, this.height) <= 1;
    }

    @Override // id.zelory.compressor.constraint.Constraint
    public File satisfy(File imageFile) {
        Intrinsics.checkParameterIsNotNull(imageFile, "imageFile");
        return UtilKt.overWrite$default(imageFile, UtilKt.determineImageRotation(imageFile, UtilKt.decodeSampledBitmapFromFile(imageFile, this.width, this.height)), null, 0, 12, null);
    }
}

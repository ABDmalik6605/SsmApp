package id.zelory.compressor.constraint;

import android.graphics.Bitmap;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.fourmob.datetimepicker.date.SimpleMonthView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultConstraint.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a2\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0004¨\u0006\t"}, d2 = {"default", "", "Lid/zelory/compressor/constraint/Compression;", "width", "", SimpleMonthView.VIEW_PARAMS_HEIGHT, "format", "Landroid/graphics/Bitmap$CompressFormat;", "quality", "compressor_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class DefaultConstraintKt {
    public static /* synthetic */ void default$default(Compression compression, int i, int i2, Bitmap.CompressFormat compressFormat, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_ID;
        }
        if ((i4 & 2) != 0) {
            i2 = 816;
        }
        if ((i4 & 4) != 0) {
            compressFormat = Bitmap.CompressFormat.JPEG;
        }
        if ((i4 & 8) != 0) {
            i3 = 80;
        }
        m72default(compression, i, i2, compressFormat, i3);
    }

    /* renamed from: default, reason: not valid java name */
    public static final void m72default(Compression compression, int i, int i2, Bitmap.CompressFormat format, int i3) {
        Intrinsics.checkParameterIsNotNull(compression, "$this$default");
        Intrinsics.checkParameterIsNotNull(format, "format");
        compression.constraint(new DefaultConstraint(i, i2, format, i3));
    }
}

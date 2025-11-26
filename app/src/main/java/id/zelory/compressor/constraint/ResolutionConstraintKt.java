package id.zelory.compressor.constraint;

import com.fourmob.datetimepicker.date.SimpleMonthView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ResolutionConstraint.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"resolution", "", "Lid/zelory/compressor/constraint/Compression;", "width", "", SimpleMonthView.VIEW_PARAMS_HEIGHT, "compressor_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ResolutionConstraintKt {
    public static final void resolution(Compression resolution, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(resolution, "$this$resolution");
        resolution.constraint(new ResolutionConstraint(i, i2));
    }
}

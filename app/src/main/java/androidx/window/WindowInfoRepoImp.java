package androidx.window;

import android.app.Activity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ContextKt;

/* compiled from: WindowInfoRepoImp.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/window/WindowInfoRepoImp;", "Landroidx/window/WindowInfoRepo;", "activity", "Landroid/app/Activity;", "windowMetricsCalculator", "Landroidx/window/WindowMetricsCalculator;", "windowBackend", "Landroidx/window/WindowBackend;", "(Landroid/app/Activity;Landroidx/window/WindowMetricsCalculator;Landroidx/window/WindowBackend;)V", "currentWindowMetrics", "Landroidx/window/WindowMetrics;", "getCurrentWindowMetrics", "()Landroidx/window/WindowMetrics;", "maximumWindowMetrics", "getMaximumWindowMetrics", "windowLayoutInfo", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/window/WindowLayoutInfo;", "getWindowLayoutInfo", "()Lkotlinx/coroutines/flow/Flow;", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class WindowInfoRepoImp implements WindowInfoRepo {
    private final Activity activity;
    private final WindowBackend windowBackend;
    private final WindowMetricsCalculator windowMetricsCalculator;

    public WindowInfoRepoImp(Activity activity, WindowMetricsCalculator windowMetricsCalculator, WindowBackend windowBackend) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(windowMetricsCalculator, "windowMetricsCalculator");
        Intrinsics.checkNotNullParameter(windowBackend, "windowBackend");
        this.activity = activity;
        this.windowMetricsCalculator = windowMetricsCalculator;
        this.windowBackend = windowBackend;
    }

    @Override // androidx.window.WindowInfoRepo
    public WindowMetrics getCurrentWindowMetrics() {
        return this.windowMetricsCalculator.computeCurrentWindowMetrics(this.activity);
    }

    @Override // androidx.window.WindowInfoRepo
    public WindowMetrics getMaximumWindowMetrics() {
        return this.windowMetricsCalculator.computeMaximumWindowMetrics(this.activity);
    }

    @Override // androidx.window.WindowInfoRepo
    public Flow<WindowLayoutInfo> getWindowLayoutInfo() {
        return FlowKt__ContextKt.buffer$default(FlowKt.callbackFlow(new WindowInfoRepoImp$windowLayoutInfo$1(this, null)), Integer.MAX_VALUE, null, 2, null);
    }
}

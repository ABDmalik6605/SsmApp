package androidx.window;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import androidx.core.util.Consumer;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WindowManager.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010J\u001c\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017J\u0014\u0010\u0019\u001a\u00020\u00132\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Landroidx/window/WindowManager;", "", "context", "Landroid/content/Context;", "windowBackend", "Landroidx/window/WindowBackend;", "(Landroid/content/Context;Landroidx/window/WindowBackend;)V", "activity", "Landroid/app/Activity;", "windowMetricsCalculator", "Landroidx/window/WindowMetricsCalculator;", "getWindowMetricsCalculator$window_release", "()Landroidx/window/WindowMetricsCalculator;", "setWindowMetricsCalculator$window_release", "(Landroidx/window/WindowMetricsCalculator;)V", "getCurrentWindowMetrics", "Landroidx/window/WindowMetrics;", "getMaximumWindowMetrics", "registerLayoutChangeCallback", "", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "Landroidx/window/WindowLayoutInfo;", "unregisterLayoutChangeCallback", "Companion", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class WindowManager {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Activity activity;
    private final WindowBackend windowBackend;
    private WindowMetricsCalculator windowMetricsCalculator;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WindowManager(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public WindowManager(Context context, WindowBackend windowBackend) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(windowBackend, "windowBackend");
        this.windowBackend = windowBackend;
        this.windowMetricsCalculator = WindowMetricsCalculatorCompat.INSTANCE;
        Activity activityFromContext = INSTANCE.getActivityFromContext(context);
        if (activityFromContext == null) {
            throw new IllegalArgumentException("Used non-visual Context to obtain an instance of WindowManager. Please use an Activity or a ContextWrapper around one instead.");
        }
        this.activity = activityFromContext;
    }

    public /* synthetic */ WindowManager(Context context, ExtensionWindowBackend extensionWindowBackend, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? ExtensionWindowBackend.INSTANCE.getInstance(context) : extensionWindowBackend);
    }

    /* renamed from: getWindowMetricsCalculator$window_release, reason: from getter */
    public final WindowMetricsCalculator getWindowMetricsCalculator() {
        return this.windowMetricsCalculator;
    }

    public final void setWindowMetricsCalculator$window_release(WindowMetricsCalculator windowMetricsCalculator) {
        Intrinsics.checkNotNullParameter(windowMetricsCalculator, "<set-?>");
        this.windowMetricsCalculator = windowMetricsCalculator;
    }

    public final void registerLayoutChangeCallback(Executor executor, Consumer<WindowLayoutInfo> callback) {
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.windowBackend.registerLayoutChangeCallback(this.activity, executor, callback);
    }

    public final void unregisterLayoutChangeCallback(Consumer<WindowLayoutInfo> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.windowBackend.unregisterLayoutChangeCallback(callback);
    }

    public final WindowMetrics getCurrentWindowMetrics() {
        return this.windowMetricsCalculator.computeCurrentWindowMetrics(this.activity);
    }

    public final WindowMetrics getMaximumWindowMetrics() {
        return this.windowMetricsCalculator.computeMaximumWindowMetrics(this.activity);
    }

    /* compiled from: WindowManager.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Landroidx/window/WindowManager$Companion;", "", "()V", "getActivityFromContext", "Landroid/app/Activity;", "initialContext", "Landroid/content/Context;", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Activity getActivityFromContext(Context initialContext) {
            Intrinsics.checkNotNullParameter(initialContext, "initialContext");
            while (initialContext instanceof ContextWrapper) {
                if (initialContext instanceof Activity) {
                    return (Activity) initialContext;
                }
                initialContext = ((ContextWrapper) initialContext).getBaseContext();
            }
            return null;
        }
    }
}

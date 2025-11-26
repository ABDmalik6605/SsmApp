package androidx.window;

import android.app.Activity;
import android.os.Looper;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

/* compiled from: WindowInfoRepo.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 \r2\u00020\u0001:\u0001\rR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Landroidx/window/WindowInfoRepo;", "", "currentWindowMetrics", "Landroidx/window/WindowMetrics;", "getCurrentWindowMetrics", "()Landroidx/window/WindowMetrics;", "maximumWindowMetrics", "getMaximumWindowMetrics", "windowLayoutInfo", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/window/WindowLayoutInfo;", "getWindowLayoutInfo", "()Lkotlinx/coroutines/flow/Flow;", "Companion", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public interface WindowInfoRepo {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* compiled from: WindowInfoRepo.kt */
    /* renamed from: androidx.window.WindowInfoRepo$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        @JvmStatic
        public static WindowInfoRepo create(Activity activity) {
            return WindowInfoRepo.INSTANCE.create(activity);
        }

        @JvmStatic
        public static void overrideDecorator(WindowInfoRepoDecorator windowInfoRepoDecorator) {
            WindowInfoRepo.INSTANCE.overrideDecorator(windowInfoRepoDecorator);
        }

        @JvmStatic
        public static void reset() {
            WindowInfoRepo.INSTANCE.reset();
        }
    }

    WindowMetrics getCurrentWindowMetrics();

    WindowMetrics getMaximumWindowMetrics();

    Flow<WindowLayoutInfo> getWindowLayoutInfo();

    /* compiled from: WindowInfoRepo.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0007J\b\u0010\f\u001a\u00020\nH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/window/WindowInfoRepo$Companion;", "", "()V", "decorator", "Landroidx/window/WindowInfoRepoDecorator;", "create", "Landroidx/window/WindowInfoRepo;", "activity", "Landroid/app/Activity;", "overrideDecorator", "", "overridingDecorator", "reset", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static WindowInfoRepoDecorator decorator = EmptyDecorator.INSTANCE;

        private Companion() {
        }

        @JvmStatic
        public final WindowInfoRepo create(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Object tag = activity.getWindow().getDecorView().getTag(R.id.androidx_window_activity_scope);
            if (!(tag instanceof WindowInfoRepo)) {
                tag = null;
            }
            WindowInfoRepoImp windowInfoRepoImp = (WindowInfoRepo) tag;
            if (!(windowInfoRepoImp instanceof WindowInfoRepo)) {
                windowInfoRepoImp = null;
            }
            if (windowInfoRepoImp == null) {
                int i = R.id.androidx_window_activity_scope;
                Object tag2 = activity.getWindow().getDecorView().getTag(i);
                WindowInfoRepoImp windowInfoRepoImp2 = (WindowInfoRepoImp) (tag2 instanceof WindowInfoRepoImp ? tag2 : null);
                if (windowInfoRepoImp2 == null) {
                    Intrinsics.areEqual(Looper.getMainLooper(), Looper.myLooper());
                    windowInfoRepoImp2 = new WindowInfoRepoImp(activity, WindowMetricsCalculatorCompat.INSTANCE, ExtensionWindowBackend.INSTANCE.getInstance(activity));
                    activity.getWindow().getDecorView().setTag(i, windowInfoRepoImp2);
                }
                windowInfoRepoImp = windowInfoRepoImp2;
            }
            return decorator.decorate(windowInfoRepoImp);
        }

        @JvmStatic
        public final void overrideDecorator(WindowInfoRepoDecorator overridingDecorator) {
            Intrinsics.checkNotNullParameter(overridingDecorator, "overridingDecorator");
            decorator = overridingDecorator;
        }

        @JvmStatic
        public final void reset() {
            decorator = EmptyDecorator.INSTANCE;
        }
    }
}

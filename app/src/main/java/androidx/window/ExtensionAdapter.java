package androidx.window;

import android.app.Activity;
import android.graphics.Rect;
import androidx.window.FoldingFeature;
import androidx.window.extensions.ExtensionDisplayFeature;
import androidx.window.extensions.ExtensionFoldingFeature;
import androidx.window.extensions.ExtensionWindowLayoutInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExtensionAdapter.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u0005\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/window/ExtensionAdapter;", "", "windowMetricsCalculator", "Landroidx/window/WindowMetricsCalculator;", "(Landroidx/window/WindowMetricsCalculator;)V", "translate", "Landroidx/window/DisplayFeature;", "activity", "Landroid/app/Activity;", "displayFeature", "Landroidx/window/extensions/ExtensionDisplayFeature;", "Landroidx/window/WindowLayoutInfo;", "layoutInfo", "Landroidx/window/extensions/ExtensionWindowLayoutInfo;", "Companion", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ExtensionAdapter {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "ExtensionAdapter";
    private final WindowMetricsCalculator windowMetricsCalculator;

    public ExtensionAdapter(WindowMetricsCalculator windowMetricsCalculator) {
        Intrinsics.checkNotNullParameter(windowMetricsCalculator, "windowMetricsCalculator");
        this.windowMetricsCalculator = windowMetricsCalculator;
    }

    public final WindowLayoutInfo translate(Activity activity, ExtensionWindowLayoutInfo layoutInfo) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(layoutInfo, "layoutInfo");
        List<ExtensionDisplayFeature> displayFeatures = layoutInfo.getDisplayFeatures();
        Intrinsics.checkNotNullExpressionValue(displayFeatures, "layoutInfo.displayFeatures");
        ArrayList arrayList = new ArrayList();
        for (ExtensionDisplayFeature it : displayFeatures) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            DisplayFeature displayFeatureTranslate = translate(activity, it);
            if (displayFeatureTranslate != null) {
                arrayList.add(displayFeatureTranslate);
            }
        }
        return new WindowLayoutInfo(arrayList);
    }

    public final DisplayFeature translate(Activity activity, ExtensionDisplayFeature displayFeature) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(displayFeature, "displayFeature");
        if (!(displayFeature instanceof ExtensionFoldingFeature)) {
            return null;
        }
        return INSTANCE.translateFoldFeature$window_release(this.windowMetricsCalculator.computeCurrentWindowMetrics(activity).getBounds(), (ExtensionFoldingFeature) displayFeature);
    }

    /* compiled from: ExtensionAdapter.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u001f\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/window/ExtensionAdapter$Companion;", "", "()V", "TAG", "", "hasMatchingDimension", "", "lhs", "Landroid/graphics/Rect;", "rhs", "isValid", "windowBounds", "feature", "Landroidx/window/extensions/ExtensionFoldingFeature;", "translateFoldFeature", "Landroidx/window/DisplayFeature;", "translateFoldFeature$window_release", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DisplayFeature translateFoldFeature$window_release(Rect windowBounds, ExtensionFoldingFeature feature) {
            FoldingFeature.Type type;
            FoldingFeature.State state;
            Intrinsics.checkNotNullParameter(windowBounds, "windowBounds");
            Intrinsics.checkNotNullParameter(feature, "feature");
            if (!isValid(windowBounds, feature)) {
                return null;
            }
            int type2 = feature.getType();
            if (type2 == 1) {
                type = FoldingFeature.Type.FOLD;
            } else {
                if (type2 != 2) {
                    return null;
                }
                type = FoldingFeature.Type.HINGE;
            }
            int state2 = feature.getState();
            if (state2 == 1) {
                state = FoldingFeature.State.FLAT;
            } else {
                if (state2 != 2) {
                    return null;
                }
                state = FoldingFeature.State.HALF_OPENED;
            }
            Rect bounds = feature.getBounds();
            Intrinsics.checkNotNullExpressionValue(bounds, "feature.bounds");
            return new FoldingFeature(new Bounds(bounds), type, state);
        }

        private final boolean isValid(Rect windowBounds, ExtensionFoldingFeature feature) {
            if (feature.getBounds().width() == 0 && feature.getBounds().height() == 0) {
                return false;
            }
            if (feature.getType() == 1 && !feature.getBounds().isEmpty()) {
                return false;
            }
            if (feature.getType() != 1 && feature.getType() != 2) {
                return false;
            }
            Rect bounds = feature.getBounds();
            Intrinsics.checkNotNullExpressionValue(bounds, "feature.bounds");
            return hasMatchingDimension(bounds, windowBounds);
        }

        private final boolean hasMatchingDimension(Rect lhs, Rect rhs) {
            return (lhs.left == rhs.left && lhs.right == rhs.right) || (lhs.top == rhs.top && lhs.bottom == rhs.bottom);
        }
    }
}

package androidx.window;

import android.app.Activity;
import androidx.window.ExtensionInterfaceCompat;
import androidx.window.extensions.ExtensionInterface;
import androidx.window.extensions.ExtensionWindowLayoutInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExtensionTranslatingCallback.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/window/ExtensionTranslatingCallback;", "Landroidx/window/extensions/ExtensionInterface$ExtensionCallback;", "callback", "Landroidx/window/ExtensionInterfaceCompat$ExtensionCallbackInterface;", "adapter", "Landroidx/window/ExtensionAdapter;", "(Landroidx/window/ExtensionInterfaceCompat$ExtensionCallbackInterface;Landroidx/window/ExtensionAdapter;)V", "onWindowLayoutChanged", "", "activity", "Landroid/app/Activity;", "newLayout", "Landroidx/window/extensions/ExtensionWindowLayoutInfo;", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ExtensionTranslatingCallback implements ExtensionInterface.ExtensionCallback {
    private final ExtensionAdapter adapter;
    private final ExtensionInterfaceCompat.ExtensionCallbackInterface callback;

    public ExtensionTranslatingCallback(ExtensionInterfaceCompat.ExtensionCallbackInterface callback, ExtensionAdapter adapter) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        this.callback = callback;
        this.adapter = adapter;
    }

    public void onWindowLayoutChanged(Activity activity, ExtensionWindowLayoutInfo newLayout) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(newLayout, "newLayout");
        this.callback.onWindowLayoutChanged(activity, this.adapter.translate(activity, newLayout));
    }
}

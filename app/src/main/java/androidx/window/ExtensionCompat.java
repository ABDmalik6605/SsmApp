package androidx.window;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import androidx.window.ExtensionInterfaceCompat;
import androidx.window.extensions.ExtensionFoldingFeature;
import androidx.window.extensions.ExtensionInterface;
import androidx.window.extensions.ExtensionProvider;
import androidx.window.extensions.ExtensionWindowLayoutInfo;
import java.lang.reflect.Method;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExtensionCompat.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0007\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Landroidx/window/ExtensionCompat;", "Landroidx/window/ExtensionInterfaceCompat;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "windowExtension", "Landroidx/window/extensions/ExtensionInterface;", "adapter", "Landroidx/window/ExtensionAdapter;", "(Landroidx/window/extensions/ExtensionInterface;Landroidx/window/ExtensionAdapter;)V", "getWindowExtension", "()Landroidx/window/extensions/ExtensionInterface;", "onWindowLayoutChangeListenerAdded", "", "activity", "Landroid/app/Activity;", "onWindowLayoutChangeListenerRemoved", "setExtensionCallback", "extensionCallback", "Landroidx/window/ExtensionInterfaceCompat$ExtensionCallbackInterface;", "validateExtensionInterface", "", "Companion", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ExtensionCompat implements ExtensionInterfaceCompat {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final boolean DEBUG = false;
    private static final String TAG = "ExtensionVersionCompat";
    private final ExtensionAdapter adapter;
    private final ExtensionInterface windowExtension;

    public ExtensionCompat(ExtensionInterface extensionInterface, ExtensionAdapter adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        this.windowExtension = extensionInterface;
        this.adapter = adapter;
    }

    public final ExtensionInterface getWindowExtension() {
        return this.windowExtension;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ExtensionCompat(Context context) {
        this(ExtensionProvider.getExtensionImpl(context), new ExtensionAdapter(WindowMetricsCalculatorCompat.INSTANCE));
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.windowExtension == null) {
            throw new IllegalArgumentException("Extension provider returned null".toString());
        }
    }

    @Override // androidx.window.ExtensionInterfaceCompat
    public void setExtensionCallback(ExtensionInterfaceCompat.ExtensionCallbackInterface extensionCallback) {
        Intrinsics.checkNotNullParameter(extensionCallback, "extensionCallback");
        ExtensionTranslatingCallback extensionTranslatingCallback = new ExtensionTranslatingCallback(extensionCallback, this.adapter);
        ExtensionInterface extensionInterface = this.windowExtension;
        if (extensionInterface == null) {
            return;
        }
        extensionInterface.setExtensionCallback(extensionTranslatingCallback);
    }

    @Override // androidx.window.ExtensionInterfaceCompat
    public void onWindowLayoutChangeListenerAdded(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ExtensionInterface extensionInterface = this.windowExtension;
        if (extensionInterface == null) {
            return;
        }
        extensionInterface.onWindowLayoutChangeListenerAdded(activity);
    }

    @Override // androidx.window.ExtensionInterfaceCompat
    public void onWindowLayoutChangeListenerRemoved(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ExtensionInterface extensionInterface = this.windowExtension;
        if (extensionInterface == null) {
            return;
        }
        extensionInterface.onWindowLayoutChangeListenerRemoved(activity);
    }

    @Override // androidx.window.ExtensionInterfaceCompat
    public boolean validateExtensionInterface() {
        Class<?> cls;
        Class<?> cls2;
        Class<?> cls3;
        try {
            ExtensionInterface extensionInterface = this.windowExtension;
            Class<?> returnType = null;
            Method method = (extensionInterface == null || (cls = extensionInterface.getClass()) == null) ? null : cls.getMethod("setExtensionCallback", ExtensionInterface.ExtensionCallback.class);
            Class<?> returnType2 = method == null ? null : method.getReturnType();
            if (!Intrinsics.areEqual(returnType2, Void.TYPE)) {
                throw new NoSuchMethodException(Intrinsics.stringPlus("Illegal return type for 'setExtensionCallback': ", returnType2));
            }
            ExtensionInterface extensionInterface2 = this.windowExtension;
            Method method2 = (extensionInterface2 == null || (cls2 = extensionInterface2.getClass()) == null) ? null : cls2.getMethod("onWindowLayoutChangeListenerAdded", Activity.class);
            Class<?> returnType3 = method2 == null ? null : method2.getReturnType();
            if (!Intrinsics.areEqual(returnType3, Void.TYPE)) {
                throw new NoSuchMethodException(Intrinsics.stringPlus("Illegal return type for 'onWindowLayoutChangeListenerAdded': ", returnType3));
            }
            ExtensionInterface extensionInterface3 = this.windowExtension;
            Method method3 = (extensionInterface3 == null || (cls3 = extensionInterface3.getClass()) == null) ? null : cls3.getMethod("onWindowLayoutChangeListenerRemoved", Activity.class);
            if (method3 != null) {
                returnType = method3.getReturnType();
            }
            if (!Intrinsics.areEqual(returnType, Void.TYPE)) {
                throw new NoSuchMethodException(Intrinsics.stringPlus("Illegal return type for 'onWindowLayoutChangeListenerRemoved': ", returnType));
            }
            ExtensionFoldingFeature extensionFoldingFeature = new ExtensionFoldingFeature(new Rect(0, 0, 100, 0), 1, 1);
            Intrinsics.checkNotNullExpressionValue(extensionFoldingFeature.getBounds(), "displayFoldingFeature.bounds");
            extensionFoldingFeature.getState();
            extensionFoldingFeature.getType();
            Intrinsics.checkNotNullExpressionValue(new ExtensionWindowLayoutInfo(new ArrayList()).getDisplayFeatures(), "windowLayoutInfo.displayFeatures");
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* compiled from: ExtensionCompat.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/window/ExtensionCompat$Companion;", "", "()V", "DEBUG", "", "TAG", "", "extensionVersion", "Landroidx/window/Version;", "getExtensionVersion", "()Landroidx/window/Version;", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Version getExtensionVersion() {
            try {
                String apiVersion = ExtensionProvider.getApiVersion();
                if (TextUtils.isEmpty(apiVersion)) {
                    return null;
                }
                return Version.INSTANCE.parse(apiVersion);
            } catch (NoClassDefFoundError unused) {
                return (Version) null;
            } catch (UnsupportedOperationException unused2) {
                return (Version) null;
            }
        }
    }
}

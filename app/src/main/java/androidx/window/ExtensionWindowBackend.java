package androidx.window;

import android.app.Activity;
import android.content.Context;
import androidx.core.util.Consumer;
import androidx.window.ExtensionInterfaceCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExtensionWindowBackend.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u001c2\u00020\u0001:\u0003\u001c\u001d\u001eB\u0011\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0003J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J&\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0016J\u0016\u0010\u001b\u001a\u00020\u00102\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0016R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u001f"}, d2 = {"Landroidx/window/ExtensionWindowBackend;", "Landroidx/window/WindowBackend;", "windowExtension", "Landroidx/window/ExtensionInterfaceCompat;", "(Landroidx/window/ExtensionInterfaceCompat;)V", "getWindowExtension", "()Landroidx/window/ExtensionInterfaceCompat;", "setWindowExtension", "windowLayoutChangeCallbacks", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Landroidx/window/ExtensionWindowBackend$WindowLayoutChangeCallbackWrapper;", "getWindowLayoutChangeCallbacks$annotations", "()V", "getWindowLayoutChangeCallbacks", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "callbackRemovedForActivity", "", "activity", "Landroid/app/Activity;", "isActivityRegistered", "", "registerLayoutChangeCallback", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "Landroidx/window/WindowLayoutInfo;", "unregisterLayoutChangeCallback", "Companion", "ExtensionListenerImpl", "WindowLayoutChangeCallbackWrapper", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ExtensionWindowBackend implements WindowBackend {
    private static final String TAG = "WindowServer";
    private static volatile ExtensionWindowBackend globalInstance;
    private ExtensionInterfaceCompat windowExtension;
    private final CopyOnWriteArrayList<WindowLayoutChangeCallbackWrapper> windowLayoutChangeCallbacks;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ReentrantLock globalLock = new ReentrantLock();

    public static /* synthetic */ void getWindowLayoutChangeCallbacks$annotations() {
    }

    public ExtensionWindowBackend(ExtensionInterfaceCompat extensionInterfaceCompat) {
        this.windowExtension = extensionInterfaceCompat;
        if (extensionInterfaceCompat != null) {
            extensionInterfaceCompat.setExtensionCallback(new ExtensionListenerImpl(this));
        }
        this.windowLayoutChangeCallbacks = new CopyOnWriteArrayList<>();
    }

    public final ExtensionInterfaceCompat getWindowExtension() {
        return this.windowExtension;
    }

    public final void setWindowExtension(ExtensionInterfaceCompat extensionInterfaceCompat) {
        this.windowExtension = extensionInterfaceCompat;
    }

    public final CopyOnWriteArrayList<WindowLayoutChangeCallbackWrapper> getWindowLayoutChangeCallbacks() {
        return this.windowLayoutChangeCallbacks;
    }

    @Override // androidx.window.WindowBackend
    public void registerLayoutChangeCallback(Activity activity, Executor executor, Consumer<WindowLayoutInfo> callback) {
        WindowLayoutInfo lastInfo;
        Object next;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(callback, "callback");
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            ExtensionInterfaceCompat windowExtension = getWindowExtension();
            if (windowExtension == null) {
                callback.accept(new WindowLayoutInfo(CollectionsKt.emptyList()));
                return;
            }
            boolean zIsActivityRegistered = isActivityRegistered(activity);
            WindowLayoutChangeCallbackWrapper windowLayoutChangeCallbackWrapper = new WindowLayoutChangeCallbackWrapper(activity, executor, callback);
            getWindowLayoutChangeCallbacks().add(windowLayoutChangeCallbackWrapper);
            if (!zIsActivityRegistered) {
                windowExtension.onWindowLayoutChangeListenerAdded(activity);
            } else {
                Iterator<T> it = getWindowLayoutChangeCallbacks().iterator();
                while (true) {
                    lastInfo = null;
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    } else {
                        next = it.next();
                        if (Intrinsics.areEqual(activity, ((WindowLayoutChangeCallbackWrapper) next).getActivity())) {
                            break;
                        }
                    }
                }
                WindowLayoutChangeCallbackWrapper windowLayoutChangeCallbackWrapper2 = (WindowLayoutChangeCallbackWrapper) next;
                if (windowLayoutChangeCallbackWrapper2 != null) {
                    lastInfo = windowLayoutChangeCallbackWrapper2.getLastInfo();
                }
                if (lastInfo != null) {
                    windowLayoutChangeCallbackWrapper.accept(lastInfo);
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    private final boolean isActivityRegistered(Activity activity) {
        CopyOnWriteArrayList<WindowLayoutChangeCallbackWrapper> copyOnWriteArrayList = this.windowLayoutChangeCallbacks;
        if ((copyOnWriteArrayList instanceof Collection) && copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        Iterator<T> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((WindowLayoutChangeCallbackWrapper) it.next()).getActivity(), activity)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.window.WindowBackend
    public void unregisterLayoutChangeCallback(Consumer<WindowLayoutInfo> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        synchronized (globalLock) {
            if (getWindowExtension() == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<WindowLayoutChangeCallbackWrapper> it = getWindowLayoutChangeCallbacks().iterator();
            while (it.hasNext()) {
                WindowLayoutChangeCallbackWrapper callbackWrapper = it.next();
                if (callbackWrapper.getCallback() == callback) {
                    Intrinsics.checkNotNullExpressionValue(callbackWrapper, "callbackWrapper");
                    arrayList.add(callbackWrapper);
                }
            }
            getWindowLayoutChangeCallbacks().removeAll(arrayList);
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                callbackRemovedForActivity(((WindowLayoutChangeCallbackWrapper) it2.next()).getActivity());
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void callbackRemovedForActivity(Activity activity) {
        ExtensionInterfaceCompat extensionInterfaceCompat;
        CopyOnWriteArrayList<WindowLayoutChangeCallbackWrapper> copyOnWriteArrayList = this.windowLayoutChangeCallbacks;
        boolean z = false;
        if (!(copyOnWriteArrayList instanceof Collection) || !copyOnWriteArrayList.isEmpty()) {
            Iterator<T> it = copyOnWriteArrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (Intrinsics.areEqual(((WindowLayoutChangeCallbackWrapper) it.next()).getActivity(), activity)) {
                    z = true;
                    break;
                }
            }
        }
        if (z || (extensionInterfaceCompat = this.windowExtension) == null) {
            return;
        }
        extensionInterfaceCompat.onWindowLayoutChangeListenerRemoved(activity);
    }

    /* compiled from: ExtensionWindowBackend.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0081\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0017¨\u0006\t"}, d2 = {"Landroidx/window/ExtensionWindowBackend$ExtensionListenerImpl;", "Landroidx/window/ExtensionInterfaceCompat$ExtensionCallbackInterface;", "(Landroidx/window/ExtensionWindowBackend;)V", "onWindowLayoutChanged", "", "activity", "Landroid/app/Activity;", "newLayout", "Landroidx/window/WindowLayoutInfo;", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public final class ExtensionListenerImpl implements ExtensionInterfaceCompat.ExtensionCallbackInterface {
        final /* synthetic */ ExtensionWindowBackend this$0;

        public ExtensionListenerImpl(ExtensionWindowBackend this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // androidx.window.ExtensionInterfaceCompat.ExtensionCallbackInterface
        public void onWindowLayoutChanged(Activity activity, WindowLayoutInfo newLayout) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(newLayout, "newLayout");
            Iterator<WindowLayoutChangeCallbackWrapper> it = this.this$0.getWindowLayoutChangeCallbacks().iterator();
            while (it.hasNext()) {
                WindowLayoutChangeCallbackWrapper next = it.next();
                if (Intrinsics.areEqual(next.getActivity(), activity)) {
                    next.accept(newLayout);
                }
            }
        }
    }

    /* compiled from: ExtensionWindowBackend.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Landroidx/window/ExtensionWindowBackend$WindowLayoutChangeCallbackWrapper;", "", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "Landroidx/window/WindowLayoutInfo;", "(Landroid/app/Activity;Ljava/util/concurrent/Executor;Landroidx/core/util/Consumer;)V", "getActivity", "()Landroid/app/Activity;", "getCallback", "()Landroidx/core/util/Consumer;", "lastInfo", "getLastInfo", "()Landroidx/window/WindowLayoutInfo;", "setLastInfo", "(Landroidx/window/WindowLayoutInfo;)V", "accept", "", "newLayoutInfo", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class WindowLayoutChangeCallbackWrapper {
        private final Activity activity;
        private final Consumer<WindowLayoutInfo> callback;
        private final Executor executor;
        private WindowLayoutInfo lastInfo;

        public WindowLayoutChangeCallbackWrapper(Activity activity, Executor executor, Consumer<WindowLayoutInfo> callback) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(executor, "executor");
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.activity = activity;
            this.executor = executor;
            this.callback = callback;
        }

        public final Activity getActivity() {
            return this.activity;
        }

        public final Consumer<WindowLayoutInfo> getCallback() {
            return this.callback;
        }

        public final WindowLayoutInfo getLastInfo() {
            return this.lastInfo;
        }

        public final void setLastInfo(WindowLayoutInfo windowLayoutInfo) {
            this.lastInfo = windowLayoutInfo;
        }

        public final void accept(final WindowLayoutInfo newLayoutInfo) {
            Intrinsics.checkNotNullParameter(newLayoutInfo, "newLayoutInfo");
            this.lastInfo = newLayoutInfo;
            this.executor.execute(new Runnable() { // from class: androidx.window.ExtensionWindowBackend$WindowLayoutChangeCallbackWrapper$accept$1
                @Override // java.lang.Runnable
                public final void run() {
                    this.this$0.getCallback().accept(newLayoutInfo);
                }
            });
        }
    }

    /* compiled from: ExtensionWindowBackend.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u000bJ\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007J\b\u0010\u0012\u001a\u00020\u0013H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/window/ExtensionWindowBackend$Companion;", "", "()V", "TAG", "", "globalInstance", "Landroidx/window/ExtensionWindowBackend;", "globalLock", "Ljava/util/concurrent/locks/ReentrantLock;", "getInstance", "context", "Landroid/content/Context;", "initAndVerifyExtension", "Landroidx/window/ExtensionInterfaceCompat;", "isExtensionVersionSupported", "", "extensionVersion", "Landroidx/window/Version;", "resetInstance", "", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ExtensionWindowBackend getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (ExtensionWindowBackend.globalInstance == null) {
                ReentrantLock reentrantLock = ExtensionWindowBackend.globalLock;
                reentrantLock.lock();
                try {
                    if (ExtensionWindowBackend.globalInstance == null) {
                        ExtensionInterfaceCompat extensionInterfaceCompatInitAndVerifyExtension = ExtensionWindowBackend.INSTANCE.initAndVerifyExtension(context);
                        Companion companion = ExtensionWindowBackend.INSTANCE;
                        ExtensionWindowBackend.globalInstance = new ExtensionWindowBackend(extensionInterfaceCompatInitAndVerifyExtension);
                    }
                    Unit unit = Unit.INSTANCE;
                } finally {
                    reentrantLock.unlock();
                }
            }
            ExtensionWindowBackend extensionWindowBackend = ExtensionWindowBackend.globalInstance;
            Intrinsics.checkNotNull(extensionWindowBackend);
            return extensionWindowBackend;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0022  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final androidx.window.ExtensionInterfaceCompat initAndVerifyExtension(android.content.Context r4) {
            /*
                r3 = this;
                java.lang.String r0 = "context"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                r0 = 0
                androidx.window.ExtensionCompat$Companion r1 = androidx.window.ExtensionCompat.INSTANCE     // Catch: java.lang.Throwable -> L22
                androidx.window.Version r1 = r1.getExtensionVersion()     // Catch: java.lang.Throwable -> L22
                boolean r1 = r3.isExtensionVersionSupported(r1)     // Catch: java.lang.Throwable -> L22
                if (r1 == 0) goto L22
                androidx.window.ExtensionCompat r1 = new androidx.window.ExtensionCompat     // Catch: java.lang.Throwable -> L22
                r1.<init>(r4)     // Catch: java.lang.Throwable -> L22
                androidx.window.ExtensionInterfaceCompat r1 = (androidx.window.ExtensionInterfaceCompat) r1     // Catch: java.lang.Throwable -> L22
                r2 = r1
                androidx.window.ExtensionCompat r2 = (androidx.window.ExtensionCompat) r2     // Catch: java.lang.Throwable -> L22
                boolean r2 = r2.validateExtensionInterface()     // Catch: java.lang.Throwable -> L22
                if (r2 != 0) goto L23
            L22:
                r1 = r0
            L23:
                if (r1 != 0) goto L44
                androidx.window.SidecarCompat$Companion r2 = androidx.window.SidecarCompat.INSTANCE     // Catch: java.lang.Throwable -> L42
                androidx.window.Version r2 = r2.getSidecarVersion()     // Catch: java.lang.Throwable -> L42
                boolean r2 = r3.isExtensionVersionSupported(r2)     // Catch: java.lang.Throwable -> L42
                if (r2 == 0) goto L44
                androidx.window.SidecarCompat r1 = new androidx.window.SidecarCompat     // Catch: java.lang.Throwable -> L42
                r1.<init>(r4)     // Catch: java.lang.Throwable -> L42
                androidx.window.ExtensionInterfaceCompat r1 = (androidx.window.ExtensionInterfaceCompat) r1     // Catch: java.lang.Throwable -> L42
                r4 = r1
                androidx.window.SidecarCompat r4 = (androidx.window.SidecarCompat) r4     // Catch: java.lang.Throwable -> L42
                boolean r4 = r4.validateExtensionInterface()     // Catch: java.lang.Throwable -> L42
                if (r4 != 0) goto L44
                goto L45
            L42:
                goto L45
            L44:
                r0 = r1
            L45:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.window.ExtensionWindowBackend.Companion.initAndVerifyExtension(android.content.Context):androidx.window.ExtensionInterfaceCompat");
        }

        public final boolean isExtensionVersionSupported(Version extensionVersion) {
            return (extensionVersion == null || extensionVersion.getMajor() == 1 || Version.CURRENT.getMajor() < extensionVersion.getMajor()) ? false : true;
        }

        public final void resetInstance() {
            ExtensionWindowBackend.globalInstance = null;
        }
    }
}

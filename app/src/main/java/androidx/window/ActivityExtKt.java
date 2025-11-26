package androidx.window;

import android.app.Activity;
import android.os.IBinder;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ActivityExt.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0000\u001a3\u0010\u0004\u001a\u0002H\u0005\"\u0006\b\u0000\u0010\u0005\u0018\u0001*\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00050\tH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\n\u001a$\u0010\u000b\u001a\u0004\u0018\u0001H\u0005\"\u0006\b\u0000\u0010\u0005\u0018\u0001*\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0080\b¢\u0006\u0002\u0010\f\u001a\f\u0010\r\u001a\u00020\u000e*\u00020\u0003H\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000f"}, d2 = {"getActivityWindowToken", "Landroid/os/IBinder;", "activity", "Landroid/app/Activity;", "getOrCreateTag", ExifInterface.GPS_DIRECTION_TRUE, "id", "", "producer", "Lkotlin/Function0;", "(Landroid/app/Activity;ILkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getTag", "(Landroid/app/Activity;I)Ljava/lang/Object;", "windowInfoRepository", "Landroidx/window/WindowInfoRepo;", "window_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ActivityExtKt {
    public static final IBinder getActivityWindowToken(Activity activity) {
        WindowManager.LayoutParams attributes;
        Window window = activity == null ? null : activity.getWindow();
        if (window == null || (attributes = window.getAttributes()) == null) {
            return null;
        }
        return attributes.token;
    }

    public static final /* synthetic */ <T> T getTag(Activity activity, int i) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Object tag = activity.getWindow().getDecorView().getTag(i);
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) tag;
    }

    public static final /* synthetic */ <T> T getOrCreateTag(Activity activity, int i, Function0<? extends T> producer) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(producer, "producer");
        Object tag = activity.getWindow().getDecorView().getTag(i);
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        T t = (T) tag;
        if (t != null) {
            return t;
        }
        Intrinsics.areEqual(Looper.getMainLooper(), Looper.myLooper());
        T tInvoke = producer.invoke();
        activity.getWindow().getDecorView().setTag(i, tInvoke);
        return tInvoke;
    }

    public static final WindowInfoRepo windowInfoRepository(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        return WindowInfoRepo.INSTANCE.create(activity);
    }
}

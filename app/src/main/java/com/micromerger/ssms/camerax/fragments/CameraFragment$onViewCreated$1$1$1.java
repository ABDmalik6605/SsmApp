package com.micromerger.ssms.camerax.fragments;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.SurfaceOrientedMeteringPointFactory;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraFragment.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/view/View;", "kotlin.jvm.PlatformType", "event", "Landroid/view/MotionEvent;"}, k = 3, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
final class CameraFragment$onViewCreated$1$1$1 implements View.OnTouchListener {
    final /* synthetic */ CameraFragment this$0;

    CameraFragment$onViewCreated$1$1$1(CameraFragment cameraFragment) {
        this.this$0 = cameraFragment;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        CameraControl cameraControl;
        int action = motionEvent.getAction();
        if (action == 0) {
            return true;
        }
        if (action != 1) {
            return false;
        }
        MeteringPoint meteringPointCreatePoint = new SurfaceOrientedMeteringPointFactory(this.this$0.getFragmentCameraBinding().viewFinder.getWidth(), this.this$0.getFragmentCameraBinding().viewFinder.getHeight()).createPoint(motionEvent.getX(), motionEvent.getY());
        Intrinsics.checkNotNullExpressionValue(meteringPointCreatePoint, "factory.createPoint(event.x, event.y)");
        try {
            Camera camera = this.this$0.camera;
            if (camera != null && (cameraControl = camera.getCameraControl()) != null) {
                FocusMeteringAction.Builder builder = new FocusMeteringAction.Builder(meteringPointCreatePoint, 1);
                builder.disableAutoCancel();
                Unit unit = Unit.INSTANCE;
                cameraControl.startFocusAndMetering(builder.build());
                return true;
            }
            return true;
        } catch (CameraInfoUnavailableException e) {
            Log.e("CameraX", "onViewCreated: ", e);
            return true;
        }
    }
}

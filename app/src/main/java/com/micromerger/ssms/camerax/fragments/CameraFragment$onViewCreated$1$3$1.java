package com.micromerger.ssms.camerax.fragments;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import kotlin.Metadata;

/* compiled from: CameraFragment.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/view/View;", "kotlin.jvm.PlatformType", "event", "Landroid/view/MotionEvent;"}, k = 3, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
final class CameraFragment$onViewCreated$1$3$1 implements View.OnTouchListener {
    final /* synthetic */ ScaleGestureDetector $scaleGestureDetector;

    CameraFragment$onViewCreated$1$3$1(ScaleGestureDetector scaleGestureDetector) {
        this.$scaleGestureDetector = scaleGestureDetector;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        return this.$scaleGestureDetector.onTouchEvent(motionEvent);
    }
}

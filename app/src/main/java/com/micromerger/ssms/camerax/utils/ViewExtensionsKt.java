package com.micromerger.ssms.camerax.utils;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewExtensions.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u000e\b\u0004\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006H\u0086\bø\u0001\u0000\u001a\u0014\u0010\u0007\u001a\u00020\u0003*\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"}, d2 = {"ANIMATION_FAST_MILLIS", "", "afterMeasured", "", "Landroid/view/View;", "block", "Lkotlin/Function0;", "simulateClick", "Landroid/widget/ImageButton;", "delay", "app_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class ViewExtensionsKt {
    public static final long ANIMATION_FAST_MILLIS = 50;

    public static /* synthetic */ void simulateClick$default(ImageButton imageButton, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 50;
        }
        simulateClick(imageButton, j);
    }

    public static final void simulateClick(final ImageButton imageButton, long j) {
        Intrinsics.checkNotNullParameter(imageButton, "<this>");
        imageButton.performClick();
        imageButton.setPressed(true);
        imageButton.invalidate();
        imageButton.postDelayed(new Runnable() { // from class: com.micromerger.ssms.camerax.utils.-$$Lambda$ViewExtensionsKt$5jWyyYC8eJ7jgtHu7xlJSTMbP5U
            @Override // java.lang.Runnable
            public final void run() {
                ViewExtensionsKt.m64simulateClick$lambda0(imageButton);
            }
        }, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: simulateClick$lambda-0, reason: not valid java name */
    public static final void m64simulateClick$lambda0(ImageButton this_simulateClick) {
        Intrinsics.checkNotNullParameter(this_simulateClick, "$this_simulateClick");
        this_simulateClick.invalidate();
        this_simulateClick.setPressed(false);
    }

    public static final void afterMeasured(final View view, final Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        if (view.getMeasuredWidth() > 0 && view.getMeasuredHeight() > 0) {
            block.invoke();
        } else {
            view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.micromerger.ssms.camerax.utils.ViewExtensionsKt.afterMeasured.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0) {
                        return;
                    }
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    block.invoke();
                }
            });
        }
    }
}

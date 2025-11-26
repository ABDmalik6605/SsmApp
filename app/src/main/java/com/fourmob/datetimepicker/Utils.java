package com.fourmob.datetimepicker;

import android.os.Build;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import com.nineoldandroids.animation.Keyframe;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;

/* loaded from: classes.dex */
public class Utils {
    public static final int PULSE_ANIMATOR_DURATION = 544;

    public static int getDaysInMonth(int i, int i2) {
        switch (i) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
                return 31;
            case 1:
                return i2 % 4 == 0 ? 29 : 28;
            case 3:
            case 5:
            case 8:
            case 10:
                return 30;
            default:
                throw new IllegalArgumentException("Invalid Month");
        }
    }

    public static ObjectAnimator getPulseAnimator(View view, float f, float f2) {
        Keyframe keyframeOfFloat = Keyframe.ofFloat(0.0f, 1.0f);
        Keyframe keyframeOfFloat2 = Keyframe.ofFloat(0.275f, f);
        Keyframe keyframeOfFloat3 = Keyframe.ofFloat(0.69f, f2);
        Keyframe keyframeOfFloat4 = Keyframe.ofFloat(1.0f, 1.0f);
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofKeyframe("scaleX", keyframeOfFloat, keyframeOfFloat2, keyframeOfFloat3, keyframeOfFloat4), PropertyValuesHolder.ofKeyframe("scaleY", keyframeOfFloat, keyframeOfFloat2, keyframeOfFloat3, keyframeOfFloat4));
        objectAnimatorOfPropertyValuesHolder.setDuration(544L);
        return objectAnimatorOfPropertyValuesHolder;
    }

    public static boolean isJellybeanOrLater() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public static void tryAccessibilityAnnounce(View view, CharSequence charSequence) {
        if (!isJellybeanOrLater() || view == null || charSequence == null) {
            return;
        }
        view.announceForAccessibility(charSequence);
    }

    public static boolean isTouchExplorationEnabled(AccessibilityManager accessibilityManager) {
        if (Build.VERSION.SDK_INT >= 14) {
            return accessibilityManager.isTouchExplorationEnabled();
        }
        return false;
    }
}

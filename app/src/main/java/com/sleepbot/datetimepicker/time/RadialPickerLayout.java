package com.sleepbot.datetimepicker.time;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import com.fourmob.datetimepicker.R;
import com.fourmob.datetimepicker.Utils;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

/* loaded from: classes2.dex */
public class RadialPickerLayout extends FrameLayout implements View.OnTouchListener {
    private static final int AM = 0;
    private static final int AMPM_INDEX = 2;
    private static final int ENABLE_PICKER_INDEX = 3;
    private static final int HOUR_INDEX = 0;
    private static final int HOUR_VALUE_TO_DEGREES_STEP_SIZE = 30;
    private static final int MINUTE_INDEX = 1;
    private static final int MINUTE_VALUE_TO_DEGREES_STEP_SIZE = 6;
    private static final int PM = 1;
    private static final String TAG = "RadialPickerLayout";
    private static final int VISIBLE_DEGREES_STEP_SIZE = 30;
    private final int TAP_TIMEOUT;
    private final int TOUCH_SLOP;
    private AccessibilityManager mAccessibilityManager;
    private AmPmCirclesView mAmPmCirclesView;
    private CircleView mCircleView;
    private int mCurrentHoursOfDay;
    private int mCurrentItemShowing;
    private int mCurrentMinutes;
    private boolean mDoingMove;
    private boolean mDoingTouch;
    private int mDownDegrees;
    private float mDownX;
    private float mDownY;
    private View mGrayBox;
    private Handler mHandler;
    private boolean mHideAmPm;
    private RadialSelectorView mHourRadialSelectorView;
    private RadialTextsView mHourRadialTextsView;
    private boolean mInputEnabled;
    private boolean mIs24HourMode;
    private int mIsTouchingAmOrPm;
    private int mLastValueSelected;
    private long mLastVibrate;
    private OnValueSelectedListener mListener;
    private RadialSelectorView mMinuteRadialSelectorView;
    private RadialTextsView mMinuteRadialTextsView;
    private int[] mSnapPrefer30sMap;
    private boolean mTimeInitialized;
    private AnimatorSet mTransition;
    private boolean mVibrate;
    private Vibrator mVibrator;

    public interface OnValueSelectedListener {
        void onValueSelected(int i, int i2, boolean z);
    }

    public RadialPickerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mVibrate = true;
        this.mIsTouchingAmOrPm = -1;
        this.mHandler = new Handler();
        setOnTouchListener(this);
        this.TOUCH_SLOP = ViewConfiguration.get(context).getScaledTouchSlop();
        this.TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
        this.mDoingMove = false;
        CircleView circleView = new CircleView(context);
        this.mCircleView = circleView;
        addView(circleView);
        AmPmCirclesView amPmCirclesView = new AmPmCirclesView(context);
        this.mAmPmCirclesView = amPmCirclesView;
        addView(amPmCirclesView);
        RadialTextsView radialTextsView = new RadialTextsView(context);
        this.mHourRadialTextsView = radialTextsView;
        addView(radialTextsView);
        RadialTextsView radialTextsView2 = new RadialTextsView(context);
        this.mMinuteRadialTextsView = radialTextsView2;
        addView(radialTextsView2);
        RadialSelectorView radialSelectorView = new RadialSelectorView(context);
        this.mHourRadialSelectorView = radialSelectorView;
        addView(radialSelectorView);
        RadialSelectorView radialSelectorView2 = new RadialSelectorView(context);
        this.mMinuteRadialSelectorView = radialSelectorView2;
        addView(radialSelectorView2);
        preparePrefer30sMap();
        this.mVibrator = (Vibrator) context.getSystemService("vibrator");
        this.mLastVibrate = 0L;
        this.mLastValueSelected = -1;
        this.mInputEnabled = true;
        View view = new View(context);
        this.mGrayBox = view;
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.mGrayBox.setBackgroundColor(getResources().getColor(R.color.transparent_black));
        this.mGrayBox.setVisibility(4);
        addView(this.mGrayBox);
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        this.mTimeInitialized = false;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        int iMin = Math.min(size, size2);
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, mode), View.MeasureSpec.makeMeasureSpec(iMin, mode2));
    }

    public void setOnValueSelectedListener(OnValueSelectedListener onValueSelectedListener) {
        this.mListener = onValueSelectedListener;
    }

    public void initialize(Context context, int i, int i2, boolean z, boolean z2) {
        char c;
        String str;
        if (this.mTimeInitialized) {
            Log.e(TAG, "Time has already been initialized.");
            return;
        }
        this.mIs24HourMode = z;
        boolean z3 = Utils.isTouchExplorationEnabled(this.mAccessibilityManager) ? true : this.mIs24HourMode;
        this.mHideAmPm = z3;
        this.mVibrate = z2;
        this.mCircleView.initialize(context, z3);
        this.mCircleView.invalidate();
        if (!this.mHideAmPm) {
            this.mAmPmCirclesView.initialize(context, i < 12 ? 0 : 1);
            this.mAmPmCirclesView.invalidate();
        }
        Resources resources = context.getResources();
        int[] iArr = {12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] iArr2 = {0, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        int[] iArr3 = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
        String[] strArr = new String[12];
        String[] strArr2 = new String[12];
        String[] strArr3 = new String[12];
        int i3 = 0;
        for (int i4 = 12; i3 < i4; i4 = 12) {
            if (z) {
                c = 0;
                str = String.format("%02d", Integer.valueOf(iArr2[i3]));
            } else {
                c = 0;
                str = String.format("%d", Integer.valueOf(iArr[i3]));
            }
            strArr[i3] = str;
            Object[] objArr = new Object[1];
            objArr[c] = Integer.valueOf(iArr[i3]);
            strArr2[i3] = String.format("%d", objArr);
            Object[] objArr2 = new Object[1];
            objArr2[c] = Integer.valueOf(iArr3[i3]);
            strArr3[i3] = String.format("%02d", objArr2);
            i3++;
        }
        this.mHourRadialTextsView.initialize(resources, strArr, z ? strArr2 : null, this.mHideAmPm, true);
        this.mHourRadialTextsView.invalidate();
        this.mMinuteRadialTextsView.initialize(resources, strArr3, null, this.mHideAmPm, false);
        this.mMinuteRadialTextsView.invalidate();
        setValueForItem(0, i);
        setValueForItem(1, i2);
        this.mHourRadialSelectorView.initialize(context, this.mHideAmPm, z, true, (i % 12) * 30, isHourInnerCircle(i));
        this.mMinuteRadialSelectorView.initialize(context, this.mHideAmPm, false, false, i2 * 6, false);
        this.mTimeInitialized = true;
    }

    public void setTime(int i, int i2) {
        setItem(0, i);
        setItem(1, i2);
    }

    public void setVibrate(boolean z) {
        this.mVibrate = z;
    }

    private void setItem(int i, int i2) {
        if (i == 0) {
            setValueForItem(0, i2);
            this.mHourRadialSelectorView.setSelection((i2 % 12) * 30, isHourInnerCircle(i2), false);
            this.mHourRadialSelectorView.invalidate();
            return;
        }
        if (i == 1) {
            setValueForItem(1, i2);
            this.mMinuteRadialSelectorView.setSelection(i2 * 6, false, false);
            this.mMinuteRadialSelectorView.invalidate();
        }
    }

    private boolean isHourInnerCircle(int i) {
        return this.mIs24HourMode && i <= 12 && i != 0;
    }

    public int getHours() {
        return this.mCurrentHoursOfDay;
    }

    public int getMinutes() {
        return this.mCurrentMinutes;
    }

    private int getCurrentlyShowingValue() {
        int currentItemShowing = getCurrentItemShowing();
        if (currentItemShowing == 0) {
            return this.mCurrentHoursOfDay;
        }
        if (currentItemShowing == 1) {
            return this.mCurrentMinutes;
        }
        return -1;
    }

    public int getIsCurrentlyAmOrPm() {
        int i = this.mCurrentHoursOfDay;
        if (i < 12) {
            return 0;
        }
        return i < 24 ? 1 : -1;
    }

    private void setValueForItem(int i, int i2) {
        if (i == 0) {
            this.mCurrentHoursOfDay = i2;
            return;
        }
        if (i == 1) {
            this.mCurrentMinutes = i2;
            return;
        }
        if (i == 2) {
            if (i2 == 0) {
                this.mCurrentHoursOfDay %= 12;
            } else if (i2 == 1) {
                this.mCurrentHoursOfDay = (this.mCurrentHoursOfDay % 12) + 12;
            }
        }
    }

    public void setAmOrPm(int i) {
        this.mAmPmCirclesView.setAmOrPm(i);
        this.mAmPmCirclesView.invalidate();
        setValueForItem(2, i);
    }

    private void preparePrefer30sMap() {
        this.mSnapPrefer30sMap = new int[361];
        int i = 0;
        int i2 = 8;
        int i3 = 1;
        for (int i4 = 0; i4 < 361; i4++) {
            this.mSnapPrefer30sMap[i4] = i;
            if (i3 == i2) {
                i += 6;
                if (i == 360) {
                    i2 = 7;
                } else {
                    i2 = i % 30 == 0 ? 14 : 4;
                }
                i3 = 1;
            } else {
                i3++;
            }
        }
    }

    private int snapPrefer30s(int i) {
        int[] iArr = this.mSnapPrefer30sMap;
        if (iArr == null) {
            return -1;
        }
        return iArr[i];
    }

    private int snapOnly30s(int i, int i2) {
        int i3 = (i / 30) * 30;
        int i4 = i3 + 30;
        if (i2 != 1) {
            if (i2 == -1) {
                return i == i3 ? i3 - 30 : i3;
            }
            if (i - i3 < i4 - i) {
                return i3;
            }
        }
        return i4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int reselectSelector(int r5, boolean r6, boolean r7, boolean r8) {
        /*
            r4 = this;
            r0 = -1
            if (r5 != r0) goto L4
            return r0
        L4:
            int r0 = r4.getCurrentItemShowing()
            r1 = 1
            r2 = 0
            if (r7 != 0) goto L10
            if (r0 != r1) goto L10
            r7 = 1
            goto L11
        L10:
            r7 = 0
        L11:
            if (r7 == 0) goto L18
            int r5 = r4.snapPrefer30s(r5)
            goto L1c
        L18:
            int r5 = r4.snapOnly30s(r5, r2)
        L1c:
            if (r0 != 0) goto L23
            com.sleepbot.datetimepicker.time.RadialSelectorView r7 = r4.mHourRadialSelectorView
            r3 = 30
            goto L26
        L23:
            com.sleepbot.datetimepicker.time.RadialSelectorView r7 = r4.mMinuteRadialSelectorView
            r3 = 6
        L26:
            r7.setSelection(r5, r6, r8)
            r7.invalidate()
            r7 = 360(0x168, float:5.04E-43)
            if (r0 != 0) goto L43
            boolean r8 = r4.mIs24HourMode
            if (r8 == 0) goto L3e
            if (r5 != 0) goto L39
            if (r6 == 0) goto L39
            goto L40
        L39:
            if (r5 != r7) goto L48
            if (r6 != 0) goto L48
            goto L49
        L3e:
            if (r5 != 0) goto L48
        L40:
            r2 = 360(0x168, float:5.04E-43)
            goto L49
        L43:
            if (r5 != r7) goto L48
            if (r0 != r1) goto L48
            goto L49
        L48:
            r2 = r5
        L49:
            int r5 = r2 / r3
            if (r0 != 0) goto L57
            boolean r7 = r4.mIs24HourMode
            if (r7 == 0) goto L57
            if (r6 != 0) goto L57
            if (r2 == 0) goto L57
            int r5 = r5 + 12
        L57:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sleepbot.datetimepicker.time.RadialPickerLayout.reselectSelector(int, boolean, boolean, boolean):int");
    }

    private int getDegreesFromCoords(float f, float f2, boolean z, Boolean[] boolArr) {
        int currentItemShowing = getCurrentItemShowing();
        if (currentItemShowing == 0) {
            return this.mHourRadialSelectorView.getDegreesFromCoords(f, f2, z, boolArr);
        }
        if (currentItemShowing == 1) {
            return this.mMinuteRadialSelectorView.getDegreesFromCoords(f, f2, z, boolArr);
        }
        return -1;
    }

    public int getCurrentItemShowing() {
        int i = this.mCurrentItemShowing;
        if (i == 0 || i == 1) {
            return i;
        }
        Log.e(TAG, "Current item showing was unfortunately set to " + this.mCurrentItemShowing);
        return -1;
    }

    public void setCurrentItemShowing(int i, boolean z) {
        int i2;
        if (i != 0 && i != 1) {
            Log.e(TAG, "TimePicker does not support view at index " + i);
            return;
        }
        boolean z2 = z && Build.VERSION.SDK_INT >= 14;
        int currentItemShowing = getCurrentItemShowing();
        this.mCurrentItemShowing = i;
        if (z2 && i != currentItemShowing) {
            ObjectAnimator[] objectAnimatorArr = new ObjectAnimator[4];
            if (i == 1) {
                objectAnimatorArr[0] = this.mHourRadialTextsView.getDisappearAnimator();
                objectAnimatorArr[1] = this.mHourRadialSelectorView.getDisappearAnimator();
                objectAnimatorArr[2] = this.mMinuteRadialTextsView.getReappearAnimator();
                objectAnimatorArr[3] = this.mMinuteRadialSelectorView.getReappearAnimator();
            } else if (i == 0) {
                objectAnimatorArr[0] = this.mHourRadialTextsView.getReappearAnimator();
                objectAnimatorArr[1] = this.mHourRadialSelectorView.getReappearAnimator();
                objectAnimatorArr[2] = this.mMinuteRadialTextsView.getDisappearAnimator();
                objectAnimatorArr[3] = this.mMinuteRadialSelectorView.getDisappearAnimator();
            }
            AnimatorSet animatorSet = this.mTransition;
            if (animatorSet != null && animatorSet.isRunning()) {
                this.mTransition.end();
            }
            AnimatorSet animatorSet2 = new AnimatorSet();
            this.mTransition = animatorSet2;
            animatorSet2.playTogether(objectAnimatorArr);
            this.mTransition.start();
            return;
        }
        if (Build.VERSION.SDK_INT >= 11) {
            int i3 = i == 0 ? 255 : 0;
            i2 = i == 1 ? 255 : 0;
            float f = i3;
            this.mHourRadialTextsView.setAlpha(f);
            this.mHourRadialSelectorView.setAlpha(f);
            float f2 = i2;
            this.mMinuteRadialTextsView.setAlpha(f2);
            this.mMinuteRadialSelectorView.setAlpha(f2);
            return;
        }
        int i4 = i == 0 ? 0 : 4;
        i2 = i != 1 ? 4 : 0;
        this.mHourRadialTextsView.setVisibility(i4);
        this.mHourRadialSelectorView.setVisibility(i4);
        this.mMinuteRadialTextsView.setVisibility(i2);
        this.mMinuteRadialSelectorView.setVisibility(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0052  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r10, android.view.MotionEvent r11) {
        /*
            Method dump skipped, instructions count: 399
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sleepbot.datetimepicker.time.RadialPickerLayout.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public void tryVibrate() {
        if (!this.mVibrate || this.mVibrator == null) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (jUptimeMillis - this.mLastVibrate >= 125) {
            this.mVibrator.vibrate(5L);
            this.mLastVibrate = jUptimeMillis;
        }
    }

    public boolean trySettingInputEnabled(boolean z) {
        if (this.mDoingTouch && !z) {
            return false;
        }
        this.mInputEnabled = z;
        this.mGrayBox.setVisibility(z ? 4 : 0);
        return true;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.addAction(4096);
            accessibilityNodeInfo.addAction(8192);
        }
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 32) {
            accessibilityEvent.getText().clear();
            Time time = new Time();
            time.hour = getHours();
            time.minute = getMinutes();
            accessibilityEvent.getText().add(DateUtils.formatDateTime(getContext(), time.normalize(true), this.mIs24HourMode ? 129 : 1));
            return true;
        }
        return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0047  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean performAccessibilityAction(int r5, android.os.Bundle r6) {
        /*
            r4 = this;
            boolean r6 = super.performAccessibilityAction(r5, r6)
            r0 = 1
            if (r6 == 0) goto L8
            return r0
        L8:
            r6 = 4096(0x1000, float:5.74E-42)
            r1 = 0
            if (r5 != r6) goto Lf
            r5 = 1
            goto L16
        Lf:
            r6 = 8192(0x2000, float:1.148E-41)
            if (r5 != r6) goto L15
            r5 = -1
            goto L16
        L15:
            r5 = 0
        L16:
            if (r5 == 0) goto L53
            int r6 = r4.getCurrentlyShowingValue()
            int r2 = r4.getCurrentItemShowing()
            if (r2 != 0) goto L27
            r3 = 30
            int r6 = r6 % 12
            goto L2c
        L27:
            if (r2 != r0) goto L2b
            r3 = 6
            goto L2c
        L2b:
            r3 = 0
        L2c:
            int r6 = r6 * r3
            int r5 = r4.snapOnly30s(r6, r5)
            int r5 = r5 / r3
            if (r2 != 0) goto L40
            boolean r6 = r4.mIs24HourMode
            if (r6 == 0) goto L3c
            r6 = 23
            goto L42
        L3c:
            r6 = 12
            r3 = 1
            goto L43
        L40:
            r6 = 55
        L42:
            r3 = 0
        L43:
            if (r5 <= r6) goto L47
            r5 = r3
            goto L4a
        L47:
            if (r5 >= r3) goto L4a
            r5 = r6
        L4a:
            r4.setItem(r2, r5)
            com.sleepbot.datetimepicker.time.RadialPickerLayout$OnValueSelectedListener r6 = r4.mListener
            r6.onValueSelected(r2, r5, r1)
            return r0
        L53:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sleepbot.datetimepicker.time.RadialPickerLayout.performAccessibilityAction(int, android.os.Bundle):boolean");
    }
}

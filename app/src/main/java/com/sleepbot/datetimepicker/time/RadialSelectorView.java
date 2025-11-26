package com.sleepbot.datetimepicker.time;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import com.fourmob.datetimepicker.R;
import com.nineoldandroids.animation.Keyframe;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.nineoldandroids.animation.ValueAnimator;

/* loaded from: classes2.dex */
public class RadialSelectorView extends View {
    private static final String TAG = "RadialSelectorView";
    private float mAmPmCircleRadiusMultiplier;
    private float mAnimationRadiusMultiplier;
    private int mCircleRadius;
    private float mCircleRadiusMultiplier;
    private boolean mDrawValuesReady;
    private boolean mForceDrawDot;
    private boolean mHasInnerCircle;
    private float mInnerNumbersRadiusMultiplier;
    private InvalidateUpdateListener mInvalidateUpdateListener;
    private boolean mIs24HourMode;
    private boolean mIsInitialized;
    private int mLineLength;
    private float mNumbersRadiusMultiplier;
    private float mOuterNumbersRadiusMultiplier;
    private final Paint mPaint;
    private int mSelectionDegrees;
    private double mSelectionRadians;
    private int mSelectionRadius;
    private float mSelectionRadiusMultiplier;
    private float mTransitionEndRadiusMultiplier;
    private float mTransitionMidRadiusMultiplier;
    private int mXCenter;
    private int mYCenter;

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public RadialSelectorView(Context context) {
        super(context);
        this.mPaint = new Paint();
        this.mIsInitialized = false;
    }

    public void initialize(Context context, boolean z, boolean z2, boolean z3, int i, boolean z4) {
        if (this.mIsInitialized) {
            Log.e(TAG, "This RadialSelectorView may only be initialized once.");
            return;
        }
        Resources resources = context.getResources();
        this.mPaint.setColor(resources.getColor(R.color.blue));
        this.mPaint.setAntiAlias(true);
        this.mIs24HourMode = z;
        if (z) {
            this.mCircleRadiusMultiplier = Float.parseFloat(resources.getString(R.string.circle_radius_multiplier_24HourMode));
        } else {
            this.mCircleRadiusMultiplier = Float.parseFloat(resources.getString(R.string.circle_radius_multiplier));
            this.mAmPmCircleRadiusMultiplier = Float.parseFloat(resources.getString(R.string.ampm_circle_radius_multiplier));
        }
        this.mHasInnerCircle = z2;
        if (z2) {
            this.mInnerNumbersRadiusMultiplier = Float.parseFloat(resources.getString(R.string.numbers_radius_multiplier_inner));
            this.mOuterNumbersRadiusMultiplier = Float.parseFloat(resources.getString(R.string.numbers_radius_multiplier_outer));
        } else {
            this.mNumbersRadiusMultiplier = Float.parseFloat(resources.getString(R.string.numbers_radius_multiplier_normal));
        }
        this.mSelectionRadiusMultiplier = Float.parseFloat(resources.getString(R.string.selection_radius_multiplier));
        this.mAnimationRadiusMultiplier = 1.0f;
        this.mTransitionMidRadiusMultiplier = ((z3 ? -1 : 1) * 0.05f) + 1.0f;
        this.mTransitionEndRadiusMultiplier = ((z3 ? 1 : -1) * 0.3f) + 1.0f;
        this.mInvalidateUpdateListener = new InvalidateUpdateListener();
        setSelection(i, z4, false);
        this.mIsInitialized = true;
    }

    public void setSelection(int i, boolean z, boolean z2) {
        this.mSelectionDegrees = i;
        this.mSelectionRadians = (i * 3.141592653589793d) / 180.0d;
        this.mForceDrawDot = z2;
        if (this.mHasInnerCircle) {
            if (z) {
                this.mNumbersRadiusMultiplier = this.mInnerNumbersRadiusMultiplier;
            } else {
                this.mNumbersRadiusMultiplier = this.mOuterNumbersRadiusMultiplier;
            }
        }
    }

    public void setAnimationRadiusMultiplier(float f) {
        this.mAnimationRadiusMultiplier = f;
    }

    public int getDegreesFromCoords(float f, float f2, boolean z, Boolean[] boolArr) {
        if (!this.mDrawValuesReady) {
            return -1;
        }
        int i = this.mYCenter;
        float f3 = (f2 - i) * (f2 - i);
        int i2 = this.mXCenter;
        double dSqrt = Math.sqrt(f3 + ((f - i2) * (f - i2)));
        if (this.mHasInnerCircle) {
            if (z) {
                boolArr[0] = Boolean.valueOf(((int) Math.abs(dSqrt - ((double) ((int) (((float) this.mCircleRadius) * this.mInnerNumbersRadiusMultiplier))))) <= ((int) Math.abs(dSqrt - ((double) ((int) (((float) this.mCircleRadius) * this.mOuterNumbersRadiusMultiplier))))));
            } else {
                int i3 = this.mCircleRadius;
                float f4 = this.mInnerNumbersRadiusMultiplier;
                int i4 = this.mSelectionRadius;
                int i5 = ((int) (i3 * f4)) - i4;
                float f5 = this.mOuterNumbersRadiusMultiplier;
                int i6 = ((int) (i3 * f5)) + i4;
                int i7 = (int) (i3 * ((f5 + f4) / 2.0f));
                if (dSqrt >= i5 && dSqrt <= i7) {
                    boolArr[0] = true;
                } else {
                    if (dSqrt > i6 || dSqrt < i7) {
                        return -1;
                    }
                    boolArr[0] = false;
                }
            }
        } else if (!z && ((int) Math.abs(dSqrt - this.mLineLength)) > ((int) (this.mCircleRadius * (1.0f - this.mNumbersRadiusMultiplier)))) {
            return -1;
        }
        int iAsin = (int) ((Math.asin(Math.abs(f2 - this.mYCenter) / dSqrt) * 180.0d) / 3.141592653589793d);
        boolean z2 = f > ((float) this.mXCenter);
        boolean z3 = f2 < ((float) this.mYCenter);
        return (z2 && z3) ? 90 - iAsin : (!z2 || z3) ? (z2 || z3) ? (z2 || !z3) ? iAsin : iAsin + 270 : 270 - iAsin : iAsin + 90;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (getWidth() == 0 || !this.mIsInitialized) {
            return;
        }
        if (!this.mDrawValuesReady) {
            this.mXCenter = getWidth() / 2;
            this.mYCenter = getHeight() / 2;
            int iMin = (int) (Math.min(this.mXCenter, r0) * this.mCircleRadiusMultiplier);
            this.mCircleRadius = iMin;
            if (!this.mIs24HourMode) {
                this.mYCenter -= ((int) (iMin * this.mAmPmCircleRadiusMultiplier)) / 2;
            }
            this.mSelectionRadius = (int) (iMin * this.mSelectionRadiusMultiplier);
            this.mDrawValuesReady = true;
        }
        int i = (int) (this.mCircleRadius * this.mNumbersRadiusMultiplier * this.mAnimationRadiusMultiplier);
        this.mLineLength = i;
        int iSin = this.mXCenter + ((int) (i * Math.sin(this.mSelectionRadians)));
        int iCos = this.mYCenter - ((int) (this.mLineLength * Math.cos(this.mSelectionRadians)));
        this.mPaint.setAlpha(51);
        float f = iSin;
        float f2 = iCos;
        canvas.drawCircle(f, f2, this.mSelectionRadius, this.mPaint);
        if ((this.mSelectionDegrees % 30 != 0) | this.mForceDrawDot) {
            this.mPaint.setAlpha(255);
            canvas.drawCircle(f, f2, (this.mSelectionRadius * 2) / 7, this.mPaint);
        } else {
            double d = this.mLineLength - this.mSelectionRadius;
            int iSin2 = ((int) (Math.sin(this.mSelectionRadians) * d)) + this.mXCenter;
            int iCos2 = this.mYCenter - ((int) (d * Math.cos(this.mSelectionRadians)));
            iSin = iSin2;
            iCos = iCos2;
        }
        this.mPaint.setAlpha(255);
        this.mPaint.setStrokeWidth(1.0f);
        canvas.drawLine(this.mXCenter, this.mYCenter, iSin, iCos, this.mPaint);
    }

    public ObjectAnimator getDisappearAnimator() {
        if (!this.mIsInitialized || !this.mDrawValuesReady) {
            Log.e(TAG, "RadialSelectorView was not ready for animation.");
            return null;
        }
        ObjectAnimator duration = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", Keyframe.ofFloat(0.0f, 1.0f), Keyframe.ofFloat(0.2f, this.mTransitionMidRadiusMultiplier), Keyframe.ofFloat(1.0f, this.mTransitionEndRadiusMultiplier)), PropertyValuesHolder.ofKeyframe("alpha", Keyframe.ofFloat(0.0f, 1.0f), Keyframe.ofFloat(1.0f, 0.0f))).setDuration(500);
        duration.addUpdateListener(this.mInvalidateUpdateListener);
        return duration;
    }

    public ObjectAnimator getReappearAnimator() {
        if (!this.mIsInitialized || !this.mDrawValuesReady) {
            Log.e(TAG, "RadialSelectorView was not ready for animation.");
            return null;
        }
        float f = 500;
        int i = (int) (1.25f * f);
        float f2 = (f * 0.25f) / i;
        ObjectAnimator duration = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", Keyframe.ofFloat(0.0f, this.mTransitionEndRadiusMultiplier), Keyframe.ofFloat(f2, this.mTransitionEndRadiusMultiplier), Keyframe.ofFloat(1.0f - ((1.0f - f2) * 0.2f), this.mTransitionMidRadiusMultiplier), Keyframe.ofFloat(1.0f, 1.0f)), PropertyValuesHolder.ofKeyframe("alpha", Keyframe.ofFloat(0.0f, 0.0f), Keyframe.ofFloat(f2, 0.0f), Keyframe.ofFloat(1.0f, 1.0f))).setDuration(i);
        duration.addUpdateListener(this.mInvalidateUpdateListener);
        return duration;
    }

    private class InvalidateUpdateListener implements ValueAnimator.AnimatorUpdateListener {
        private InvalidateUpdateListener() {
        }

        @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            RadialSelectorView.this.invalidate();
        }
    }
}

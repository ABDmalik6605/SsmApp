package com.sleepbot.datetimepicker.time;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import com.fourmob.datetimepicker.R;
import com.nineoldandroids.animation.Keyframe;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.nineoldandroids.animation.ValueAnimator;

/* loaded from: classes2.dex */
public class RadialTextsView extends View {
    private static final String TAG = "RadialTextsView";
    private float mAmPmCircleRadiusMultiplier;
    private float mAnimationRadiusMultiplier;
    private float mCircleRadius;
    private float mCircleRadiusMultiplier;
    ObjectAnimator mDisappearAnimator;
    private boolean mDrawValuesReady;
    private boolean mHasInnerCircle;
    private float mInnerNumbersRadiusMultiplier;
    private float[] mInnerTextGridHeights;
    private float[] mInnerTextGridWidths;
    private float mInnerTextSize;
    private float mInnerTextSizeMultiplier;
    private String[] mInnerTexts;
    private InvalidateUpdateListener mInvalidateUpdateListener;
    private boolean mIs24HourMode;
    private boolean mIsInitialized;
    private float mNumbersRadiusMultiplier;
    private final Paint mPaint;
    ObjectAnimator mReappearAnimator;
    private float[] mTextGridHeights;
    private boolean mTextGridValuesDirty;
    private float[] mTextGridWidths;
    private float mTextSize;
    private float mTextSizeMultiplier;
    private String[] mTexts;
    private float mTransitionEndRadiusMultiplier;
    private float mTransitionMidRadiusMultiplier;
    private Typeface mTypefaceLight;
    private Typeface mTypefaceRegular;
    private int mXCenter;
    private int mYCenter;

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public RadialTextsView(Context context) {
        super(context);
        this.mPaint = new Paint();
        this.mIsInitialized = false;
    }

    public void initialize(Resources resources, String[] strArr, String[] strArr2, boolean z, boolean z2) {
        if (this.mIsInitialized) {
            Log.e(TAG, "This RadialTextsView may only be initialized once.");
            return;
        }
        this.mPaint.setColor(resources.getColor(R.color.numbers_text_color));
        this.mTypefaceLight = Typeface.create(resources.getString(R.string.radial_numbers_typeface), 0);
        this.mTypefaceRegular = Typeface.create(resources.getString(R.string.sans_serif), 0);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mTexts = strArr;
        this.mInnerTexts = strArr2;
        this.mIs24HourMode = z;
        this.mHasInnerCircle = strArr2 != null;
        if (z) {
            this.mCircleRadiusMultiplier = Float.parseFloat(resources.getString(R.string.circle_radius_multiplier_24HourMode));
        } else {
            this.mCircleRadiusMultiplier = Float.parseFloat(resources.getString(R.string.circle_radius_multiplier));
            this.mAmPmCircleRadiusMultiplier = Float.parseFloat(resources.getString(R.string.ampm_circle_radius_multiplier));
        }
        this.mTextGridHeights = new float[7];
        this.mTextGridWidths = new float[7];
        if (this.mHasInnerCircle) {
            this.mNumbersRadiusMultiplier = Float.parseFloat(resources.getString(R.string.numbers_radius_multiplier_outer));
            this.mTextSizeMultiplier = Float.parseFloat(resources.getString(R.string.text_size_multiplier_outer));
            this.mInnerNumbersRadiusMultiplier = Float.parseFloat(resources.getString(R.string.numbers_radius_multiplier_inner));
            this.mInnerTextSizeMultiplier = Float.parseFloat(resources.getString(R.string.text_size_multiplier_inner));
            this.mInnerTextGridHeights = new float[7];
            this.mInnerTextGridWidths = new float[7];
        } else {
            this.mNumbersRadiusMultiplier = Float.parseFloat(resources.getString(R.string.numbers_radius_multiplier_normal));
            this.mTextSizeMultiplier = Float.parseFloat(resources.getString(R.string.text_size_multiplier_normal));
        }
        this.mAnimationRadiusMultiplier = 1.0f;
        this.mTransitionMidRadiusMultiplier = ((z2 ? -1 : 1) * 0.05f) + 1.0f;
        this.mTransitionEndRadiusMultiplier = ((z2 ? 1 : -1) * 0.3f) + 1.0f;
        this.mInvalidateUpdateListener = new InvalidateUpdateListener();
        this.mTextGridValuesDirty = true;
        this.mIsInitialized = true;
    }

    public void setAnimationRadiusMultiplier(float f) {
        this.mAnimationRadiusMultiplier = f;
        this.mTextGridValuesDirty = true;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (getWidth() == 0 || !this.mIsInitialized) {
            return;
        }
        if (!this.mDrawValuesReady) {
            this.mXCenter = getWidth() / 2;
            this.mYCenter = getHeight() / 2;
            float fMin = Math.min(this.mXCenter, r0) * this.mCircleRadiusMultiplier;
            this.mCircleRadius = fMin;
            if (!this.mIs24HourMode) {
                this.mYCenter = (int) (this.mYCenter - ((this.mAmPmCircleRadiusMultiplier * fMin) / 2.0f));
            }
            this.mTextSize = this.mTextSizeMultiplier * fMin;
            if (this.mHasInnerCircle) {
                this.mInnerTextSize = fMin * this.mInnerTextSizeMultiplier;
            }
            renderAnimations();
            this.mTextGridValuesDirty = true;
            this.mDrawValuesReady = true;
        }
        if (this.mTextGridValuesDirty) {
            calculateGridSizes(this.mCircleRadius * this.mNumbersRadiusMultiplier * this.mAnimationRadiusMultiplier, this.mXCenter, this.mYCenter, this.mTextSize, this.mTextGridHeights, this.mTextGridWidths);
            if (this.mHasInnerCircle) {
                calculateGridSizes(this.mCircleRadius * this.mInnerNumbersRadiusMultiplier * this.mAnimationRadiusMultiplier, this.mXCenter, this.mYCenter, this.mInnerTextSize, this.mInnerTextGridHeights, this.mInnerTextGridWidths);
            }
            this.mTextGridValuesDirty = false;
        }
        drawTexts(canvas, this.mTextSize, this.mTypefaceLight, this.mTexts, this.mTextGridWidths, this.mTextGridHeights);
        if (this.mHasInnerCircle) {
            drawTexts(canvas, this.mInnerTextSize, this.mTypefaceRegular, this.mInnerTexts, this.mInnerTextGridWidths, this.mInnerTextGridHeights);
        }
    }

    private void calculateGridSizes(float f, float f2, float f3, float f4, float[] fArr, float[] fArr2) {
        float fSqrt = (((float) Math.sqrt(3.0d)) * f) / 2.0f;
        float f5 = f / 2.0f;
        this.mPaint.setTextSize(f4);
        float fDescent = f3 - ((this.mPaint.descent() + this.mPaint.ascent()) / 2.0f);
        fArr[0] = fDescent - f;
        fArr2[0] = f2 - f;
        fArr[1] = fDescent - fSqrt;
        fArr2[1] = f2 - fSqrt;
        fArr[2] = fDescent - f5;
        fArr2[2] = f2 - f5;
        fArr[3] = fDescent;
        fArr2[3] = f2;
        fArr[4] = fDescent + f5;
        fArr2[4] = f5 + f2;
        fArr[5] = fDescent + fSqrt;
        fArr2[5] = fSqrt + f2;
        fArr[6] = fDescent + f;
        fArr2[6] = f2 + f;
    }

    private void drawTexts(Canvas canvas, float f, Typeface typeface, String[] strArr, float[] fArr, float[] fArr2) {
        this.mPaint.setTextSize(f);
        this.mPaint.setTypeface(typeface);
        canvas.drawText(strArr[0], fArr[3], fArr2[0], this.mPaint);
        canvas.drawText(strArr[1], fArr[4], fArr2[1], this.mPaint);
        canvas.drawText(strArr[2], fArr[5], fArr2[2], this.mPaint);
        canvas.drawText(strArr[3], fArr[6], fArr2[3], this.mPaint);
        canvas.drawText(strArr[4], fArr[5], fArr2[4], this.mPaint);
        canvas.drawText(strArr[5], fArr[4], fArr2[5], this.mPaint);
        canvas.drawText(strArr[6], fArr[3], fArr2[6], this.mPaint);
        canvas.drawText(strArr[7], fArr[2], fArr2[5], this.mPaint);
        canvas.drawText(strArr[8], fArr[1], fArr2[4], this.mPaint);
        canvas.drawText(strArr[9], fArr[0], fArr2[3], this.mPaint);
        canvas.drawText(strArr[10], fArr[1], fArr2[2], this.mPaint);
        canvas.drawText(strArr[11], fArr[2], fArr2[1], this.mPaint);
    }

    private void renderAnimations() {
        ObjectAnimator duration = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", Keyframe.ofFloat(0.0f, 1.0f), Keyframe.ofFloat(0.2f, this.mTransitionMidRadiusMultiplier), Keyframe.ofFloat(1.0f, this.mTransitionEndRadiusMultiplier)), PropertyValuesHolder.ofKeyframe("alpha", Keyframe.ofFloat(0.0f, 1.0f), Keyframe.ofFloat(1.0f, 0.0f))).setDuration(500);
        this.mDisappearAnimator = duration;
        duration.addUpdateListener(this.mInvalidateUpdateListener);
        float f = 500;
        int i = (int) (1.25f * f);
        float f2 = (f * 0.25f) / i;
        ObjectAnimator duration2 = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", Keyframe.ofFloat(0.0f, this.mTransitionEndRadiusMultiplier), Keyframe.ofFloat(f2, this.mTransitionEndRadiusMultiplier), Keyframe.ofFloat(1.0f - ((1.0f - f2) * 0.2f), this.mTransitionMidRadiusMultiplier), Keyframe.ofFloat(1.0f, 1.0f)), PropertyValuesHolder.ofKeyframe("alpha", Keyframe.ofFloat(0.0f, 0.0f), Keyframe.ofFloat(f2, 0.0f), Keyframe.ofFloat(1.0f, 1.0f))).setDuration(i);
        this.mReappearAnimator = duration2;
        duration2.addUpdateListener(this.mInvalidateUpdateListener);
    }

    public ObjectAnimator getDisappearAnimator() {
        ObjectAnimator objectAnimator;
        if (this.mIsInitialized && this.mDrawValuesReady && (objectAnimator = this.mDisappearAnimator) != null) {
            return objectAnimator;
        }
        Log.e(TAG, "RadialTextView was not ready for animation.");
        return null;
    }

    public ObjectAnimator getReappearAnimator() {
        ObjectAnimator objectAnimator;
        if (this.mIsInitialized && this.mDrawValuesReady && (objectAnimator = this.mReappearAnimator) != null) {
            return objectAnimator;
        }
        Log.e(TAG, "RadialTextView was not ready for animation.");
        return null;
    }

    private class InvalidateUpdateListener implements ValueAnimator.AnimatorUpdateListener {
        private InvalidateUpdateListener() {
        }

        @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            RadialTextsView.this.invalidate();
        }
    }
}

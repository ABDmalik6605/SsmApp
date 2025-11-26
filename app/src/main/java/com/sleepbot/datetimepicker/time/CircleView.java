package com.sleepbot.datetimepicker.time;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import com.fourmob.datetimepicker.R;

/* loaded from: classes2.dex */
public class CircleView extends View {
    private static final String TAG = "CircleView";
    private float mAmPmCircleRadiusMultiplier;
    private int mBlack;
    private int mCircleRadius;
    private float mCircleRadiusMultiplier;
    private boolean mDrawValuesReady;
    private boolean mIs24HourMode;
    private boolean mIsInitialized;
    private final Paint mPaint;
    private int mWhite;
    private int mXCenter;
    private int mYCenter;

    public CircleView(Context context) {
        super(context);
        Paint paint = new Paint();
        this.mPaint = paint;
        Resources resources = context.getResources();
        this.mWhite = resources.getColor(R.color.white);
        this.mBlack = resources.getColor(R.color.numbers_text_color);
        paint.setAntiAlias(true);
        this.mIsInitialized = false;
    }

    public void initialize(Context context, boolean z) {
        if (this.mIsInitialized) {
            Log.e(TAG, "CircleView may only be initialized once.");
            return;
        }
        Resources resources = context.getResources();
        this.mIs24HourMode = z;
        if (z) {
            this.mCircleRadiusMultiplier = Float.parseFloat(resources.getString(R.string.circle_radius_multiplier_24HourMode));
        } else {
            this.mCircleRadiusMultiplier = Float.parseFloat(resources.getString(R.string.circle_radius_multiplier));
            this.mAmPmCircleRadiusMultiplier = Float.parseFloat(resources.getString(R.string.ampm_circle_radius_multiplier));
        }
        this.mIsInitialized = true;
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
            this.mDrawValuesReady = true;
        }
        this.mPaint.setColor(this.mWhite);
        canvas.drawCircle(this.mXCenter, this.mYCenter, this.mCircleRadius, this.mPaint);
        this.mPaint.setColor(this.mBlack);
        canvas.drawCircle(this.mXCenter, this.mYCenter, 2.0f, this.mPaint);
    }
}

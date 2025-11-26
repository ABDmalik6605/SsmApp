package com.sleepbot.datetimepicker.time;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import com.fourmob.datetimepicker.R;
import java.text.DateFormatSymbols;

/* loaded from: classes2.dex */
public class AmPmCirclesView extends View {
    private static final int AM = 0;
    private static final int PM = 1;
    private static final int PRESSED_ALPHA = 175;
    private static final int SELECTED_ALPHA = 51;
    private static final String TAG = "AmPmCirclesView";
    private int mAmOrPm;
    private int mAmOrPmPressed;
    private int mAmPmCircleRadius;
    private float mAmPmCircleRadiusMultiplier;
    private int mAmPmTextColor;
    private int mAmPmYCenter;
    private String mAmText;
    private int mAmXCenter;
    private int mBlue;
    private float mCircleRadiusMultiplier;
    private boolean mDrawValuesReady;
    private boolean mIsInitialized;
    private final Paint mPaint;
    private String mPmText;
    private int mPmXCenter;
    private int mWhite;

    public AmPmCirclesView(Context context) {
        super(context);
        this.mPaint = new Paint();
        this.mIsInitialized = false;
    }

    public void initialize(Context context, int i) {
        if (this.mIsInitialized) {
            Log.e(TAG, "AmPmCirclesView may only be initialized once.");
            return;
        }
        Resources resources = context.getResources();
        this.mWhite = resources.getColor(R.color.white);
        this.mAmPmTextColor = resources.getColor(R.color.ampm_text_color);
        this.mBlue = resources.getColor(R.color.blue);
        this.mPaint.setTypeface(Typeface.create(resources.getString(R.string.sans_serif), 0));
        this.mPaint.setAntiAlias(true);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mCircleRadiusMultiplier = Float.parseFloat(resources.getString(R.string.circle_radius_multiplier));
        this.mAmPmCircleRadiusMultiplier = Float.parseFloat(resources.getString(R.string.ampm_circle_radius_multiplier));
        String[] amPmStrings = new DateFormatSymbols().getAmPmStrings();
        this.mAmText = amPmStrings[0];
        this.mPmText = amPmStrings[1];
        setAmOrPm(i);
        this.mAmOrPmPressed = -1;
        this.mIsInitialized = true;
    }

    public void setAmOrPm(int i) {
        this.mAmOrPm = i;
    }

    public void setAmOrPmPressed(int i) {
        this.mAmOrPmPressed = i;
    }

    public int getIsTouchingAmOrPm(float f, float f2) {
        if (!this.mDrawValuesReady) {
            return -1;
        }
        int i = this.mAmPmYCenter;
        int i2 = (int) ((f2 - i) * (f2 - i));
        int i3 = this.mAmXCenter;
        float f3 = i2;
        if (((int) Math.sqrt(((f - i3) * (f - i3)) + f3)) <= this.mAmPmCircleRadius) {
            return 0;
        }
        int i4 = this.mPmXCenter;
        return ((int) Math.sqrt((double) (((f - ((float) i4)) * (f - ((float) i4))) + f3))) <= this.mAmPmCircleRadius ? 1 : -1;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int i;
        if (getWidth() == 0 || !this.mIsInitialized) {
            return;
        }
        if (!this.mDrawValuesReady) {
            int width = getWidth() / 2;
            int height = getHeight() / 2;
            int iMin = (int) (Math.min(width, height) * this.mCircleRadiusMultiplier);
            this.mAmPmCircleRadius = (int) (iMin * this.mAmPmCircleRadiusMultiplier);
            this.mPaint.setTextSize((r4 * 3) / 4);
            int i2 = this.mAmPmCircleRadius;
            this.mAmPmYCenter = (height - (i2 / 2)) + iMin;
            this.mAmXCenter = (width - iMin) + i2;
            this.mPmXCenter = (width + iMin) - i2;
            this.mDrawValuesReady = true;
        }
        int i3 = this.mWhite;
        int i4 = this.mAmOrPm;
        int i5 = 51;
        int i6 = 255;
        if (i4 == 0) {
            i = i3;
            i3 = this.mBlue;
        } else if (i4 == 1) {
            i = this.mBlue;
            i5 = 255;
            i6 = 51;
        } else {
            i = i3;
            i5 = 255;
        }
        int i7 = this.mAmOrPmPressed;
        if (i7 == 0) {
            i3 = this.mBlue;
            i5 = PRESSED_ALPHA;
        } else if (i7 == 1) {
            i = this.mBlue;
            i6 = PRESSED_ALPHA;
        }
        this.mPaint.setColor(i3);
        this.mPaint.setAlpha(i5);
        canvas.drawCircle(this.mAmXCenter, this.mAmPmYCenter, this.mAmPmCircleRadius, this.mPaint);
        this.mPaint.setColor(i);
        this.mPaint.setAlpha(i6);
        canvas.drawCircle(this.mPmXCenter, this.mAmPmYCenter, this.mAmPmCircleRadius, this.mPaint);
        this.mPaint.setColor(this.mAmPmTextColor);
        float fDescent = this.mAmPmYCenter - (((int) (this.mPaint.descent() + this.mPaint.ascent())) / 2);
        canvas.drawText(this.mAmText, this.mAmXCenter, fDescent, this.mPaint);
        canvas.drawText(this.mPmText, this.mPmXCenter, fDescent, this.mPaint);
    }
}

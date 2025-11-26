package com.pnikosis.materialishprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes2.dex */
public class ProgressWheel extends View {
    private static final String TAG = "ProgressWheel";
    private int barColor;
    private float barExtraLength;
    private boolean barGrowingFromFront;
    private final int barLength;
    private final int barMaxLength;
    private Paint barPaint;
    private double barSpinCycleTime;
    private int barWidth;
    private RectF circleBounds;
    private int circleRadius;
    private boolean fillRadius;
    private boolean isSpinning;
    private long lastTimeAnimated;
    private float mProgress;
    private float mTargetProgress;
    private final long pauseGrowingTime;
    private long pausedTimeWithoutGrowing;
    private int rimColor;
    private Paint rimPaint;
    private int rimWidth;
    private float spinSpeed;
    private double timeStartGrowing;

    public ProgressWheel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.circleRadius = 80;
        this.fillRadius = false;
        this.barLength = 40;
        this.barMaxLength = 270;
        this.timeStartGrowing = 0.0d;
        this.barSpinCycleTime = 1000.0d;
        this.barExtraLength = 0.0f;
        this.barGrowingFromFront = true;
        this.pausedTimeWithoutGrowing = 0L;
        this.pauseGrowingTime = 300L;
        this.barWidth = 5;
        this.rimWidth = 5;
        this.barColor = -1442840576;
        this.rimColor = ViewCompat.MEASURED_SIZE_MASK;
        this.barPaint = new Paint();
        this.rimPaint = new Paint();
        this.circleBounds = new RectF();
        this.spinSpeed = 270.0f;
        this.lastTimeAnimated = 0L;
        this.mProgress = 0.0f;
        this.mTargetProgress = 0.0f;
        this.isSpinning = false;
        parseAttributes(context.obtainStyledAttributes(attributeSet, R.styleable.ProgressWheel));
    }

    public ProgressWheel(Context context) {
        super(context);
        this.circleRadius = 80;
        this.fillRadius = false;
        this.barLength = 40;
        this.barMaxLength = 270;
        this.timeStartGrowing = 0.0d;
        this.barSpinCycleTime = 1000.0d;
        this.barExtraLength = 0.0f;
        this.barGrowingFromFront = true;
        this.pausedTimeWithoutGrowing = 0L;
        this.pauseGrowingTime = 300L;
        this.barWidth = 5;
        this.rimWidth = 5;
        this.barColor = -1442840576;
        this.rimColor = ViewCompat.MEASURED_SIZE_MASK;
        this.barPaint = new Paint();
        this.rimPaint = new Paint();
        this.circleBounds = new RectF();
        this.spinSpeed = 270.0f;
        this.lastTimeAnimated = 0L;
        this.mProgress = 0.0f;
        this.mTargetProgress = 0.0f;
        this.isSpinning = false;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int paddingLeft = this.circleRadius + getPaddingLeft() + getPaddingRight();
        int paddingTop = this.circleRadius + getPaddingTop() + getPaddingBottom();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            paddingLeft = size;
        } else if (mode == Integer.MIN_VALUE) {
            paddingLeft = Math.min(paddingLeft, size);
        }
        if (mode2 == 1073741824 || mode == 1073741824) {
            paddingTop = size2;
        } else if (mode2 == Integer.MIN_VALUE) {
            paddingTop = Math.min(paddingTop, size2);
        }
        setMeasuredDimension(paddingLeft, paddingTop);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        setupBounds(i, i2);
        setupPaints();
        invalidate();
    }

    private void setupPaints() {
        this.barPaint.setColor(this.barColor);
        this.barPaint.setAntiAlias(true);
        this.barPaint.setStyle(Paint.Style.STROKE);
        this.barPaint.setStrokeWidth(this.barWidth);
        this.rimPaint.setColor(this.rimColor);
        this.rimPaint.setAntiAlias(true);
        this.rimPaint.setStyle(Paint.Style.STROKE);
        this.rimPaint.setStrokeWidth(this.rimWidth);
    }

    private void setupBounds(int i, int i2) {
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        if (!this.fillRadius) {
            int i3 = (i - paddingLeft) - paddingRight;
            int iMin = Math.min(Math.min(i3, (i2 - paddingBottom) - paddingTop), (this.circleRadius * 2) - (this.barWidth * 2));
            int i4 = ((i3 - iMin) / 2) + paddingLeft;
            int i5 = ((((i2 - paddingTop) - paddingBottom) - iMin) / 2) + paddingTop;
            int i6 = this.barWidth;
            this.circleBounds = new RectF(i4 + i6, i5 + i6, (i4 + iMin) - i6, (i5 + iMin) - i6);
            return;
        }
        int i7 = this.barWidth;
        this.circleBounds = new RectF(paddingLeft + i7, paddingTop + i7, (i - paddingRight) - i7, (i2 - paddingBottom) - i7);
    }

    private void parseAttributes(TypedArray typedArray) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        this.barWidth = (int) TypedValue.applyDimension(1, this.barWidth, displayMetrics);
        this.rimWidth = (int) TypedValue.applyDimension(1, this.rimWidth, displayMetrics);
        this.circleRadius = (int) typedArray.getDimension(R.styleable.ProgressWheel_circleRadius, this.circleRadius);
        this.fillRadius = typedArray.getBoolean(R.styleable.ProgressWheel_fillRadius, false);
        this.barWidth = (int) typedArray.getDimension(R.styleable.ProgressWheel_barWidth, this.barWidth);
        this.rimWidth = (int) typedArray.getDimension(R.styleable.ProgressWheel_rimWidth, this.rimWidth);
        this.spinSpeed = typedArray.getFloat(R.styleable.ProgressWheel_spinSpeed, this.spinSpeed / 360.0f) * 360.0f;
        this.barSpinCycleTime = typedArray.getInt(R.styleable.ProgressWheel_barSpinCycleTime, (int) this.barSpinCycleTime);
        this.barColor = typedArray.getColor(R.styleable.ProgressWheel_barColor, this.barColor);
        this.rimColor = typedArray.getColor(R.styleable.ProgressWheel_rimColor, this.rimColor);
        if (typedArray.getBoolean(R.styleable.ProgressWheel_progressIndeterminate, false)) {
            spin();
        }
        typedArray.recycle();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.circleBounds, 360.0f, 360.0f, false, this.rimPaint);
        boolean z = true;
        if (this.isSpinning) {
            long jUptimeMillis = SystemClock.uptimeMillis() - this.lastTimeAnimated;
            float f = (jUptimeMillis * this.spinSpeed) / 1000.0f;
            updateBarLength(jUptimeMillis);
            float f2 = this.mProgress + f;
            this.mProgress = f2;
            if (f2 > 360.0f) {
                this.mProgress = f2 - 360.0f;
            }
            this.lastTimeAnimated = SystemClock.uptimeMillis();
            canvas.drawArc(this.circleBounds, this.mProgress - 90.0f, this.barExtraLength + 40.0f, false, this.barPaint);
        } else {
            if (this.mProgress != this.mTargetProgress) {
                this.mProgress = Math.min(this.mProgress + (((SystemClock.uptimeMillis() - this.lastTimeAnimated) / 1000.0f) * this.spinSpeed), this.mTargetProgress);
                this.lastTimeAnimated = SystemClock.uptimeMillis();
            } else {
                z = false;
            }
            canvas.drawArc(this.circleBounds, -90.0f, this.mProgress, false, this.barPaint);
        }
        if (z) {
            invalidate();
        }
    }

    private void updateBarLength(long j) {
        long j2 = this.pausedTimeWithoutGrowing;
        if (j2 >= 300) {
            double d = this.timeStartGrowing + j;
            this.timeStartGrowing = d;
            double d2 = this.barSpinCycleTime;
            if (d > d2) {
                this.timeStartGrowing = d - d2;
                this.timeStartGrowing = 0.0d;
                boolean z = this.barGrowingFromFront;
                if (!z) {
                    this.pausedTimeWithoutGrowing = 0L;
                }
                this.barGrowingFromFront = !z;
            }
            float fCos = (((float) Math.cos(((this.timeStartGrowing / d2) + 1.0d) * 3.141592653589793d)) / 2.0f) + 0.5f;
            if (this.barGrowingFromFront) {
                this.barExtraLength = fCos * 230.0f;
                return;
            }
            float f = (1.0f - fCos) * 230.0f;
            this.mProgress += this.barExtraLength - f;
            this.barExtraLength = f;
            return;
        }
        this.pausedTimeWithoutGrowing = j2 + j;
    }

    public boolean isSpinning() {
        return this.isSpinning;
    }

    public void resetCount() {
        this.mProgress = 0.0f;
        this.mTargetProgress = 0.0f;
        invalidate();
    }

    public void stopSpinning() {
        this.isSpinning = false;
        this.mProgress = 0.0f;
        this.mTargetProgress = 0.0f;
        invalidate();
    }

    public void spin() {
        this.lastTimeAnimated = SystemClock.uptimeMillis();
        this.isSpinning = true;
        invalidate();
    }

    public void setProgress(float f) {
        if (this.isSpinning) {
            this.mProgress = 0.0f;
            this.isSpinning = false;
        }
        if (f > 1.0f) {
            f -= 1.0f;
        } else if (f < 0.0f) {
            f = 0.0f;
        }
        float f2 = this.mTargetProgress;
        if (f == f2) {
            return;
        }
        if (this.mProgress == f2) {
            this.lastTimeAnimated = SystemClock.uptimeMillis();
        }
        this.mTargetProgress = Math.min(f * 360.0f, 360.0f);
        invalidate();
    }

    public void setInstantProgress(float f) {
        if (this.isSpinning) {
            this.mProgress = 0.0f;
            this.isSpinning = false;
        }
        if (f > 1.0f) {
            f -= 1.0f;
        } else if (f < 0.0f) {
            f = 0.0f;
        }
        if (f == this.mTargetProgress) {
            return;
        }
        float fMin = Math.min(f * 360.0f, 360.0f);
        this.mTargetProgress = fMin;
        this.mProgress = fMin;
        this.lastTimeAnimated = SystemClock.uptimeMillis();
        invalidate();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        WheelSavedState wheelSavedState = new WheelSavedState(super.onSaveInstanceState());
        wheelSavedState.mProgress = this.mProgress;
        wheelSavedState.mTargetProgress = this.mTargetProgress;
        wheelSavedState.isSpinning = this.isSpinning;
        wheelSavedState.spinSpeed = this.spinSpeed;
        wheelSavedState.barWidth = this.barWidth;
        wheelSavedState.barColor = this.barColor;
        wheelSavedState.rimWidth = this.rimWidth;
        wheelSavedState.rimColor = this.rimColor;
        wheelSavedState.circleRadius = this.circleRadius;
        return wheelSavedState;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof WheelSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        WheelSavedState wheelSavedState = (WheelSavedState) parcelable;
        super.onRestoreInstanceState(wheelSavedState.getSuperState());
        this.mProgress = wheelSavedState.mProgress;
        this.mTargetProgress = wheelSavedState.mTargetProgress;
        this.isSpinning = wheelSavedState.isSpinning;
        this.spinSpeed = wheelSavedState.spinSpeed;
        this.barWidth = wheelSavedState.barWidth;
        this.barColor = wheelSavedState.barColor;
        this.rimWidth = wheelSavedState.rimWidth;
        this.rimColor = wheelSavedState.rimColor;
        this.circleRadius = wheelSavedState.circleRadius;
    }

    public float getProgress() {
        if (this.isSpinning) {
            return -1.0f;
        }
        return this.mProgress / 360.0f;
    }

    public int getCircleRadius() {
        return this.circleRadius;
    }

    public void setCircleRadius(int i) {
        this.circleRadius = i;
        if (this.isSpinning) {
            return;
        }
        invalidate();
    }

    public int getBarWidth() {
        return this.barWidth;
    }

    public void setBarWidth(int i) {
        this.barWidth = i;
        if (this.isSpinning) {
            return;
        }
        invalidate();
    }

    public int getBarColor() {
        return this.barColor;
    }

    public void setBarColor(int i) {
        this.barColor = i;
        setupPaints();
        if (this.isSpinning) {
            return;
        }
        invalidate();
    }

    public int getRimColor() {
        return this.rimColor;
    }

    public void setRimColor(int i) {
        this.rimColor = i;
        setupPaints();
        if (this.isSpinning) {
            return;
        }
        invalidate();
    }

    public float getSpinSpeed() {
        return this.spinSpeed / 360.0f;
    }

    public void setSpinSpeed(float f) {
        this.spinSpeed = f * 360.0f;
    }

    public int getRimWidth() {
        return this.rimWidth;
    }

    public void setRimWidth(int i) {
        this.rimWidth = i;
        if (this.isSpinning) {
            return;
        }
        invalidate();
    }

    static class WheelSavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<WheelSavedState> CREATOR = new Parcelable.Creator<WheelSavedState>() { // from class: com.pnikosis.materialishprogress.ProgressWheel.WheelSavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public WheelSavedState createFromParcel(Parcel parcel) {
                return new WheelSavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public WheelSavedState[] newArray(int i) {
                return new WheelSavedState[i];
            }
        };
        int barColor;
        int barWidth;
        int circleRadius;
        boolean isSpinning;
        float mProgress;
        float mTargetProgress;
        int rimColor;
        int rimWidth;
        float spinSpeed;

        WheelSavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private WheelSavedState(Parcel parcel) {
            super(parcel);
            this.mProgress = parcel.readFloat();
            this.mTargetProgress = parcel.readFloat();
            this.isSpinning = parcel.readByte() != 0;
            this.spinSpeed = parcel.readFloat();
            this.barWidth = parcel.readInt();
            this.barColor = parcel.readInt();
            this.rimWidth = parcel.readInt();
            this.rimColor = parcel.readInt();
            this.circleRadius = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.mProgress);
            parcel.writeFloat(this.mTargetProgress);
            parcel.writeByte(this.isSpinning ? (byte) 1 : (byte) 0);
            parcel.writeFloat(this.spinSpeed);
            parcel.writeInt(this.barWidth);
            parcel.writeInt(this.barColor);
            parcel.writeInt(this.rimWidth);
            parcel.writeInt(this.rimColor);
            parcel.writeInt(this.circleRadius);
        }
    }
}

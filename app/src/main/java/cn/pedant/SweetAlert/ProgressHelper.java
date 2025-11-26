package cn.pedant.SweetAlert;

import android.content.Context;
import com.pnikosis.materialishprogress.ProgressWheel;

/* loaded from: classes.dex */
public class ProgressHelper {
    private int mBarColor;
    private int mBarWidth;
    private int mCircleRadius;
    private ProgressWheel mProgressWheel;
    private boolean mToSpin = true;
    private float mSpinSpeed = 0.75f;
    private int mRimWidth = 0;
    private int mRimColor = 0;
    private boolean mIsInstantProgress = false;
    private float mProgressVal = -1.0f;

    public ProgressHelper(Context ctx) {
        this.mBarWidth = ctx.getResources().getDimensionPixelSize(R.dimen.common_circle_width) + 1;
        this.mBarColor = ctx.getResources().getColor(R.color.success_stroke_color);
        this.mCircleRadius = ctx.getResources().getDimensionPixelOffset(R.dimen.progress_circle_radius);
    }

    public ProgressWheel getProgressWheel() {
        return this.mProgressWheel;
    }

    public void setProgressWheel(ProgressWheel progressWheel) {
        this.mProgressWheel = progressWheel;
        updatePropsIfNeed();
    }

    private void updatePropsIfNeed() {
        ProgressWheel progressWheel = this.mProgressWheel;
        if (progressWheel != null) {
            if (!this.mToSpin && progressWheel.isSpinning()) {
                this.mProgressWheel.stopSpinning();
            } else if (this.mToSpin && !this.mProgressWheel.isSpinning()) {
                this.mProgressWheel.spin();
            }
            if (this.mSpinSpeed != this.mProgressWheel.getSpinSpeed()) {
                this.mProgressWheel.setSpinSpeed(this.mSpinSpeed);
            }
            if (this.mBarWidth != this.mProgressWheel.getBarWidth()) {
                this.mProgressWheel.setBarWidth(this.mBarWidth);
            }
            if (this.mBarColor != this.mProgressWheel.getBarColor()) {
                this.mProgressWheel.setBarColor(this.mBarColor);
            }
            if (this.mRimWidth != this.mProgressWheel.getRimWidth()) {
                this.mProgressWheel.setRimWidth(this.mRimWidth);
            }
            if (this.mRimColor != this.mProgressWheel.getRimColor()) {
                this.mProgressWheel.setRimColor(this.mRimColor);
            }
            if (this.mProgressVal != this.mProgressWheel.getProgress()) {
                if (this.mIsInstantProgress) {
                    this.mProgressWheel.setInstantProgress(this.mProgressVal);
                } else {
                    this.mProgressWheel.setProgress(this.mProgressVal);
                }
            }
            if (this.mCircleRadius != this.mProgressWheel.getCircleRadius()) {
                this.mProgressWheel.setCircleRadius(this.mCircleRadius);
            }
        }
    }

    public void resetCount() {
        ProgressWheel progressWheel = this.mProgressWheel;
        if (progressWheel != null) {
            progressWheel.resetCount();
        }
    }

    public boolean isSpinning() {
        return this.mToSpin;
    }

    public void spin() {
        this.mToSpin = true;
        updatePropsIfNeed();
    }

    public void stopSpinning() {
        this.mToSpin = false;
        updatePropsIfNeed();
    }

    public float getProgress() {
        return this.mProgressVal;
    }

    public void setProgress(float progress) {
        this.mIsInstantProgress = false;
        this.mProgressVal = progress;
        updatePropsIfNeed();
    }

    public void setInstantProgress(float progress) {
        this.mProgressVal = progress;
        this.mIsInstantProgress = true;
        updatePropsIfNeed();
    }

    public int getCircleRadius() {
        return this.mCircleRadius;
    }

    public void setCircleRadius(int circleRadius) {
        this.mCircleRadius = circleRadius;
        updatePropsIfNeed();
    }

    public int getBarWidth() {
        return this.mBarWidth;
    }

    public void setBarWidth(int barWidth) {
        this.mBarWidth = barWidth;
        updatePropsIfNeed();
    }

    public int getBarColor() {
        return this.mBarColor;
    }

    public void setBarColor(int barColor) {
        this.mBarColor = barColor;
        updatePropsIfNeed();
    }

    public int getRimWidth() {
        return this.mRimWidth;
    }

    public void setRimWidth(int rimWidth) {
        this.mRimWidth = rimWidth;
        updatePropsIfNeed();
    }

    public int getRimColor() {
        return this.mRimColor;
    }

    public void setRimColor(int rimColor) {
        this.mRimColor = rimColor;
        updatePropsIfNeed();
    }

    public float getSpinSpeed() {
        return this.mSpinSpeed;
    }

    public void setSpinSpeed(float spinSpeed) {
        this.mSpinSpeed = spinSpeed;
        updatePropsIfNeed();
    }
}

package com.micromerger.ssms.utils;

/* compiled from: PictureUtils.java */
/* loaded from: classes2.dex */
class WatermarkOptions {
    float bottomPaddingRatio;
    Corner corner;
    float rightPaddingRadio;
    int shadowColor;
    int textColor;
    float textSizeToWidthRatio;

    WatermarkOptions() {
    }

    public Corner getCorner() {
        return this.corner;
    }

    public void setCorner(Corner corner) {
        this.corner = corner;
    }

    public float getTextSizeToWidthRatio() {
        return this.textSizeToWidthRatio;
    }

    public void setTextSizeToWidthRatio(float textSizeToWidthRatio) {
        this.textSizeToWidthRatio = textSizeToWidthRatio;
    }

    public float getBottomPaddingRatio() {
        return this.bottomPaddingRatio;
    }

    public void setBottomPaddingRatio(float bottomPaddingRatio) {
        this.bottomPaddingRatio = bottomPaddingRatio;
    }

    public float getRightPaddingRadio() {
        return this.rightPaddingRadio;
    }

    public void setRightPaddingRadio(float rightPaddingRadio) {
        this.rightPaddingRadio = rightPaddingRadio;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getShadowColor() {
        return this.shadowColor;
    }

    public void setShadowColor(int shadowColor) {
        this.shadowColor = shadowColor;
    }
}

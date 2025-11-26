package com.micromerger.ssms.camerax.utils;

import kotlin.Metadata;

/* compiled from: WatermarkImage.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\b¨\u0006\u001e"}, d2 = {"Lcom/micromerger/ssms/camerax/utils/WatermarkOptions;", "", "()V", "bottomPaddingRatio", "", "getBottomPaddingRatio", "()F", "setBottomPaddingRatio", "(F)V", "corner", "Lcom/micromerger/ssms/camerax/utils/Corner;", "getCorner", "()Lcom/micromerger/ssms/camerax/utils/Corner;", "setCorner", "(Lcom/micromerger/ssms/camerax/utils/Corner;)V", "rightPaddingRatio", "getRightPaddingRatio", "setRightPaddingRatio", "shadowColor", "", "getShadowColor", "()I", "setShadowColor", "(I)V", "textColor", "getTextColor", "setTextColor", "textSizeToWidthRatio", "getTextSizeToWidthRatio", "setTextSizeToWidthRatio", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class WatermarkOptions {
    private float bottomPaddingRatio;
    private Corner corner;
    private float rightPaddingRatio;
    private int shadowColor;
    private int textColor;
    private float textSizeToWidthRatio;

    public final Corner getCorner() {
        return this.corner;
    }

    public final void setCorner(Corner corner) {
        this.corner = corner;
    }

    public final float getTextSizeToWidthRatio() {
        return this.textSizeToWidthRatio;
    }

    public final void setTextSizeToWidthRatio(float f) {
        this.textSizeToWidthRatio = f;
    }

    public final float getBottomPaddingRatio() {
        return this.bottomPaddingRatio;
    }

    public final void setBottomPaddingRatio(float f) {
        this.bottomPaddingRatio = f;
    }

    public final float getRightPaddingRatio() {
        return this.rightPaddingRatio;
    }

    public final void setRightPaddingRatio(float f) {
        this.rightPaddingRatio = f;
    }

    public final int getTextColor() {
        return this.textColor;
    }

    public final void setTextColor(int i) {
        this.textColor = i;
    }

    public final int getShadowColor() {
        return this.shadowColor;
    }

    public final void setShadowColor(int i) {
        this.shadowColor = i;
    }
}

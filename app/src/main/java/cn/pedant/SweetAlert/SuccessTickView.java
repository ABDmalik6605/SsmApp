package cn.pedant.SweetAlert;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* loaded from: classes.dex */
public class SuccessTickView extends View {
    private final float CONST_LEFT_RECT_W;
    private final float CONST_RADIUS;
    private final float CONST_RECT_WEIGHT;
    private final float CONST_RIGHT_RECT_W;
    private final float MAX_RIGHT_RECT_W;
    private final float MIN_LEFT_RECT_W;
    private float mDensity;
    private boolean mLeftRectGrowMode;
    private float mLeftRectWidth;
    private float mMaxLeftRectWidth;
    private Paint mPaint;
    private float mRightRectWidth;

    public SuccessTickView(Context context) {
        super(context);
        this.mDensity = -1.0f;
        this.CONST_RADIUS = dip2px(1.2f);
        this.CONST_RECT_WEIGHT = dip2px(3.0f);
        this.CONST_LEFT_RECT_W = dip2px(15.0f);
        float fDip2px = dip2px(25.0f);
        this.CONST_RIGHT_RECT_W = fDip2px;
        this.MIN_LEFT_RECT_W = dip2px(3.3f);
        this.MAX_RIGHT_RECT_W = fDip2px + dip2px(6.7f);
        init();
    }

    public SuccessTickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mDensity = -1.0f;
        this.CONST_RADIUS = dip2px(1.2f);
        this.CONST_RECT_WEIGHT = dip2px(3.0f);
        this.CONST_LEFT_RECT_W = dip2px(15.0f);
        float fDip2px = dip2px(25.0f);
        this.CONST_RIGHT_RECT_W = fDip2px;
        this.MIN_LEFT_RECT_W = dip2px(3.3f);
        this.MAX_RIGHT_RECT_W = fDip2px + dip2px(6.7f);
        init();
    }

    private void init() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(getResources().getColor(R.color.success_stroke_color));
        this.mLeftRectWidth = this.CONST_LEFT_RECT_W;
        this.mRightRectWidth = this.CONST_RIGHT_RECT_W;
        this.mLeftRectGrowMode = false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.rotate(45.0f, width / 2, height / 2);
        int i = (int) (height / 1.4d);
        float f = (int) (width / 1.2d);
        this.mMaxLeftRectWidth = (((this.CONST_LEFT_RECT_W + f) / 2.0f) + this.CONST_RECT_WEIGHT) - 1.0f;
        RectF rectF = new RectF();
        if (this.mLeftRectGrowMode) {
            rectF.left = 0.0f;
            rectF.right = rectF.left + this.mLeftRectWidth;
            rectF.top = (i + this.CONST_RIGHT_RECT_W) / 2.0f;
            rectF.bottom = rectF.top + this.CONST_RECT_WEIGHT;
        } else {
            rectF.right = (((this.CONST_LEFT_RECT_W + f) / 2.0f) + this.CONST_RECT_WEIGHT) - 1.0f;
            rectF.left = rectF.right - this.mLeftRectWidth;
            rectF.top = (i + this.CONST_RIGHT_RECT_W) / 2.0f;
            rectF.bottom = rectF.top + this.CONST_RECT_WEIGHT;
        }
        float f2 = this.CONST_RADIUS;
        canvas.drawRoundRect(rectF, f2, f2, this.mPaint);
        RectF rectF2 = new RectF();
        rectF2.bottom = (((i + this.CONST_RIGHT_RECT_W) / 2.0f) + this.CONST_RECT_WEIGHT) - 1.0f;
        rectF2.left = (f + this.CONST_LEFT_RECT_W) / 2.0f;
        rectF2.right = rectF2.left + this.CONST_RECT_WEIGHT;
        rectF2.top = rectF2.bottom - this.mRightRectWidth;
        float f3 = this.CONST_RADIUS;
        canvas.drawRoundRect(rectF2, f3, f3, this.mPaint);
    }

    public float dip2px(float dpValue) {
        if (this.mDensity == -1.0f) {
            this.mDensity = getResources().getDisplayMetrics().density;
        }
        return (dpValue * this.mDensity) + 0.5f;
    }

    public void startTickAnim() {
        this.mLeftRectWidth = 0.0f;
        this.mRightRectWidth = 0.0f;
        invalidate();
        Animation animation = new Animation() { // from class: cn.pedant.SweetAlert.SuccessTickView.1
            @Override // android.view.animation.Animation
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);
                double d = interpolatedTime;
                if (0.54d < d && 0.7d >= d) {
                    SuccessTickView.this.mLeftRectGrowMode = true;
                    SuccessTickView successTickView = SuccessTickView.this;
                    successTickView.mLeftRectWidth = successTickView.mMaxLeftRectWidth * ((interpolatedTime - 0.54f) / 0.16f);
                    if (0.65d < d) {
                        SuccessTickView successTickView2 = SuccessTickView.this;
                        successTickView2.mRightRectWidth = successTickView2.MAX_RIGHT_RECT_W * ((interpolatedTime - 0.65f) / 0.19f);
                    }
                    SuccessTickView.this.invalidate();
                    return;
                }
                if (0.7d < d && 0.84d >= d) {
                    SuccessTickView.this.mLeftRectGrowMode = false;
                    SuccessTickView successTickView3 = SuccessTickView.this;
                    successTickView3.mLeftRectWidth = successTickView3.mMaxLeftRectWidth * (1.0f - ((interpolatedTime - 0.7f) / 0.14f));
                    SuccessTickView successTickView4 = SuccessTickView.this;
                    successTickView4.mLeftRectWidth = successTickView4.mLeftRectWidth < SuccessTickView.this.MIN_LEFT_RECT_W ? SuccessTickView.this.MIN_LEFT_RECT_W : SuccessTickView.this.mLeftRectWidth;
                    SuccessTickView successTickView5 = SuccessTickView.this;
                    successTickView5.mRightRectWidth = successTickView5.MAX_RIGHT_RECT_W * ((interpolatedTime - 0.65f) / 0.19f);
                    SuccessTickView.this.invalidate();
                    return;
                }
                if (0.84d >= d || 1.0f < interpolatedTime) {
                    return;
                }
                SuccessTickView.this.mLeftRectGrowMode = false;
                SuccessTickView successTickView6 = SuccessTickView.this;
                float f = (interpolatedTime - 0.84f) / 0.16f;
                successTickView6.mLeftRectWidth = successTickView6.MIN_LEFT_RECT_W + ((SuccessTickView.this.CONST_LEFT_RECT_W - SuccessTickView.this.MIN_LEFT_RECT_W) * f);
                SuccessTickView successTickView7 = SuccessTickView.this;
                successTickView7.mRightRectWidth = successTickView7.CONST_RIGHT_RECT_W + ((SuccessTickView.this.MAX_RIGHT_RECT_W - SuccessTickView.this.CONST_RIGHT_RECT_W) * (1.0f - f));
                SuccessTickView.this.invalidate();
            }
        };
        animation.setDuration(750L);
        animation.setStartOffset(100L);
        startAnimation(animation);
    }
}

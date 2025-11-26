package com.viewpagerindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.view.ViewConfigurationCompat;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes2.dex */
public class LinePageIndicator extends View implements PageIndicator {
    private static final int INVALID_POINTER = -1;
    private int mActivePointerId;
    private boolean mCentered;
    private int mCurrentPage;
    private float mGapWidth;
    private boolean mIsDragging;
    private float mLastMotionX;
    private float mLineWidth;
    private ViewPager.OnPageChangeListener mListener;
    private final Paint mPaintSelected;
    private final Paint mPaintUnselected;
    private int mTouchSlop;
    private ViewPager mViewPager;

    public LinePageIndicator(Context context) {
        this(context, null);
    }

    public LinePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.vpiLinePageIndicatorStyle);
    }

    public LinePageIndicator(Context context, AttributeSet attributeSet, int i) throws Resources.NotFoundException {
        super(context, attributeSet, i);
        Paint paint = new Paint(1);
        this.mPaintUnselected = paint;
        Paint paint2 = new Paint(1);
        this.mPaintSelected = paint2;
        this.mLastMotionX = -1.0f;
        this.mActivePointerId = -1;
        if (isInEditMode()) {
            return;
        }
        Resources resources = getResources();
        int color = resources.getColor(R.color.default_line_indicator_selected_color);
        int color2 = resources.getColor(R.color.default_line_indicator_unselected_color);
        float dimension = resources.getDimension(R.dimen.default_line_indicator_line_width);
        float dimension2 = resources.getDimension(R.dimen.default_line_indicator_gap_width);
        float dimension3 = resources.getDimension(R.dimen.default_line_indicator_stroke_width);
        boolean z = resources.getBoolean(R.bool.default_line_indicator_centered);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LinePageIndicator, i, 0);
        this.mCentered = typedArrayObtainStyledAttributes.getBoolean(R.styleable.LinePageIndicator_centered, z);
        this.mLineWidth = typedArrayObtainStyledAttributes.getDimension(R.styleable.LinePageIndicator_lineWidth, dimension);
        this.mGapWidth = typedArrayObtainStyledAttributes.getDimension(R.styleable.LinePageIndicator_gapWidth, dimension2);
        setStrokeWidth(typedArrayObtainStyledAttributes.getDimension(R.styleable.LinePageIndicator_strokeWidth, dimension3));
        paint.setColor(typedArrayObtainStyledAttributes.getColor(R.styleable.LinePageIndicator_unselectedColor, color2));
        paint2.setColor(typedArrayObtainStyledAttributes.getColor(R.styleable.LinePageIndicator_selectedColor, color));
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.LinePageIndicator_android_background);
        if (drawable != null) {
            setBackgroundDrawable(drawable);
        }
        typedArrayObtainStyledAttributes.recycle();
        this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    public void setCentered(boolean z) {
        this.mCentered = z;
        invalidate();
    }

    public boolean isCentered() {
        return this.mCentered;
    }

    public void setUnselectedColor(int i) {
        this.mPaintUnselected.setColor(i);
        invalidate();
    }

    public int getUnselectedColor() {
        return this.mPaintUnselected.getColor();
    }

    public void setSelectedColor(int i) {
        this.mPaintSelected.setColor(i);
        invalidate();
    }

    public int getSelectedColor() {
        return this.mPaintSelected.getColor();
    }

    public void setLineWidth(float f) {
        this.mLineWidth = f;
        invalidate();
    }

    public float getLineWidth() {
        return this.mLineWidth;
    }

    public void setStrokeWidth(float f) {
        this.mPaintSelected.setStrokeWidth(f);
        this.mPaintUnselected.setStrokeWidth(f);
        invalidate();
    }

    public float getStrokeWidth() {
        return this.mPaintSelected.getStrokeWidth();
    }

    public void setGapWidth(float f) {
        this.mGapWidth = f;
        invalidate();
    }

    public float getGapWidth() {
        return this.mGapWidth;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) throws Resources.NotFoundException {
        int count;
        super.onDraw(canvas);
        ViewPager viewPager = this.mViewPager;
        if (viewPager == null || (count = viewPager.getAdapter().getCount()) == 0) {
            return;
        }
        if (this.mCurrentPage >= count) {
            setCurrentItem(count - 1);
            return;
        }
        float f = this.mLineWidth;
        float f2 = this.mGapWidth;
        float f3 = f + f2;
        float f4 = (count * f3) - f2;
        float paddingTop = getPaddingTop();
        float paddingLeft = getPaddingLeft();
        float paddingRight = getPaddingRight();
        float height = paddingTop + (((getHeight() - paddingTop) - getPaddingBottom()) / 2.0f);
        if (this.mCentered) {
            paddingLeft += (((getWidth() - paddingLeft) - paddingRight) / 2.0f) - (f4 / 2.0f);
        }
        int i = 0;
        while (i < count) {
            float f5 = paddingLeft + (i * f3);
            canvas.drawLine(f5, height, f5 + this.mLineWidth, height, i == this.mCurrentPage ? this.mPaintSelected : this.mPaintUnselected);
            i++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00a1  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r10) throws android.content.res.Resources.NotFoundException {
        /*
            Method dump skipped, instructions count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.viewpagerindicator.LinePageIndicator.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager viewPager) {
        ViewPager viewPager2 = this.mViewPager;
        if (viewPager2 == viewPager) {
            return;
        }
        if (viewPager2 != null) {
            viewPager2.setOnPageChangeListener(null);
        }
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        this.mViewPager = viewPager;
        viewPager.setOnPageChangeListener(this);
        invalidate();
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setViewPager(ViewPager viewPager, int i) throws Resources.NotFoundException {
        setViewPager(viewPager);
        setCurrentItem(i);
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setCurrentItem(int i) throws Resources.NotFoundException {
        ViewPager viewPager = this.mViewPager;
        if (viewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        viewPager.setCurrentItem(i);
        this.mCurrentPage = i;
        invalidate();
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void notifyDataSetChanged() {
        invalidate();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.mListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.mListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i, f, i2);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        this.mCurrentPage = i;
        invalidate();
        ViewPager.OnPageChangeListener onPageChangeListener = this.mListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i);
        }
    }

    @Override // com.viewpagerindicator.PageIndicator
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mListener = onPageChangeListener;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(measureWidth(i), measureHeight(i2));
    }

    private int measureWidth(int i) {
        float paddingLeft;
        ViewPager viewPager;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824 || (viewPager = this.mViewPager) == null) {
            paddingLeft = size;
        } else {
            paddingLeft = getPaddingLeft() + getPaddingRight() + (viewPager.getAdapter().getCount() * this.mLineWidth) + ((r1 - 1) * this.mGapWidth);
            if (mode == Integer.MIN_VALUE) {
                paddingLeft = Math.min(paddingLeft, size);
            }
        }
        return (int) FloatMath.ceil(paddingLeft);
    }

    private int measureHeight(int i) {
        float fMin;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            fMin = size;
        } else {
            float strokeWidth = this.mPaintSelected.getStrokeWidth() + getPaddingTop() + getPaddingBottom();
            fMin = mode == Integer.MIN_VALUE ? Math.min(strokeWidth, size) : strokeWidth;
        }
        return (int) FloatMath.ceil(fMin);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mCurrentPage = savedState.currentPage;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.currentPage = this.mCurrentPage;
        return savedState;
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.viewpagerindicator.LinePageIndicator.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int currentPage;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.currentPage = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.currentPage);
        }
    }
}

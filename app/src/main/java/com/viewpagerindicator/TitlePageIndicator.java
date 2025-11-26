package com.viewpagerindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.view.ViewConfigurationCompat;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class TitlePageIndicator extends View implements PageIndicator {
    private static final float BOLD_FADE_PERCENTAGE = 0.05f;
    private static final String EMPTY_TITLE = "";
    private static final int INVALID_POINTER = -1;
    private static final float SELECTION_FADE_PERCENTAGE = 0.25f;
    private int mActivePointerId;
    private boolean mBoldText;
    private final Rect mBounds;
    private OnCenterItemClickListener mCenterItemClickListener;
    private float mClipPadding;
    private int mColorSelected;
    private int mColorText;
    private int mCurrentPage;
    private float mFooterIndicatorHeight;
    private IndicatorStyle mFooterIndicatorStyle;
    private float mFooterIndicatorUnderlinePadding;
    private float mFooterLineHeight;
    private float mFooterPadding;
    private boolean mIsDragging;
    private float mLastMotionX;
    private LinePosition mLinePosition;
    private ViewPager.OnPageChangeListener mListener;
    private float mPageOffset;
    private final Paint mPaintFooterIndicator;
    private final Paint mPaintFooterLine;
    private final Paint mPaintText;
    private Path mPath;
    private int mScrollState;
    private float mTitlePadding;
    private float mTopPadding;
    private int mTouchSlop;
    private ViewPager mViewPager;

    public interface OnCenterItemClickListener {
        void onCenterItemClick(int i);
    }

    public enum IndicatorStyle {
        None(0),
        Triangle(1),
        Underline(2);

        public final int value;

        IndicatorStyle(int i) {
            this.value = i;
        }

        public static IndicatorStyle fromValue(int i) {
            for (IndicatorStyle indicatorStyle : values()) {
                if (indicatorStyle.value == i) {
                    return indicatorStyle;
                }
            }
            return null;
        }
    }

    public enum LinePosition {
        Bottom(0),
        Top(1);

        public final int value;

        LinePosition(int i) {
            this.value = i;
        }

        public static LinePosition fromValue(int i) {
            for (LinePosition linePosition : values()) {
                if (linePosition.value == i) {
                    return linePosition;
                }
            }
            return null;
        }
    }

    public TitlePageIndicator(Context context) {
        this(context, null);
    }

    public TitlePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.vpiTitlePageIndicatorStyle);
    }

    public TitlePageIndicator(Context context, AttributeSet attributeSet, int i) throws Resources.NotFoundException {
        super(context, attributeSet, i);
        this.mCurrentPage = -1;
        Paint paint = new Paint();
        this.mPaintText = paint;
        this.mPath = new Path();
        this.mBounds = new Rect();
        Paint paint2 = new Paint();
        this.mPaintFooterLine = paint2;
        Paint paint3 = new Paint();
        this.mPaintFooterIndicator = paint3;
        this.mLastMotionX = -1.0f;
        this.mActivePointerId = -1;
        if (isInEditMode()) {
            return;
        }
        Resources resources = getResources();
        int color = resources.getColor(R.color.default_title_indicator_footer_color);
        float dimension = resources.getDimension(R.dimen.default_title_indicator_footer_line_height);
        int integer = resources.getInteger(R.integer.default_title_indicator_footer_indicator_style);
        float dimension2 = resources.getDimension(R.dimen.default_title_indicator_footer_indicator_height);
        float dimension3 = resources.getDimension(R.dimen.default_title_indicator_footer_indicator_underline_padding);
        float dimension4 = resources.getDimension(R.dimen.default_title_indicator_footer_padding);
        int integer2 = resources.getInteger(R.integer.default_title_indicator_line_position);
        int color2 = resources.getColor(R.color.default_title_indicator_selected_color);
        boolean z = resources.getBoolean(R.bool.default_title_indicator_selected_bold);
        int color3 = resources.getColor(R.color.default_title_indicator_text_color);
        float dimension5 = resources.getDimension(R.dimen.default_title_indicator_text_size);
        float dimension6 = resources.getDimension(R.dimen.default_title_indicator_title_padding);
        float dimension7 = resources.getDimension(R.dimen.default_title_indicator_clip_padding);
        float dimension8 = resources.getDimension(R.dimen.default_title_indicator_top_padding);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TitlePageIndicator, i, 0);
        this.mFooterLineHeight = typedArrayObtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_footerLineHeight, dimension);
        this.mFooterIndicatorStyle = IndicatorStyle.fromValue(typedArrayObtainStyledAttributes.getInteger(R.styleable.TitlePageIndicator_footerIndicatorStyle, integer));
        this.mFooterIndicatorHeight = typedArrayObtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_footerIndicatorHeight, dimension2);
        this.mFooterIndicatorUnderlinePadding = typedArrayObtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_footerIndicatorUnderlinePadding, dimension3);
        this.mFooterPadding = typedArrayObtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_footerPadding, dimension4);
        this.mLinePosition = LinePosition.fromValue(typedArrayObtainStyledAttributes.getInteger(R.styleable.TitlePageIndicator_linePosition, integer2));
        this.mTopPadding = typedArrayObtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_topPadding, dimension8);
        this.mTitlePadding = typedArrayObtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_titlePadding, dimension6);
        this.mClipPadding = typedArrayObtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_clipPadding, dimension7);
        this.mColorSelected = typedArrayObtainStyledAttributes.getColor(R.styleable.TitlePageIndicator_selectedColor, color2);
        this.mColorText = typedArrayObtainStyledAttributes.getColor(R.styleable.TitlePageIndicator_android_textColor, color3);
        this.mBoldText = typedArrayObtainStyledAttributes.getBoolean(R.styleable.TitlePageIndicator_selectedBold, z);
        float dimension9 = typedArrayObtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_android_textSize, dimension5);
        int color4 = typedArrayObtainStyledAttributes.getColor(R.styleable.TitlePageIndicator_footerColor, color);
        paint.setTextSize(dimension9);
        paint.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setStrokeWidth(this.mFooterLineHeight);
        paint2.setColor(color4);
        paint3.setStyle(Paint.Style.FILL_AND_STROKE);
        paint3.setColor(color4);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.TitlePageIndicator_android_background);
        if (drawable != null) {
            setBackgroundDrawable(drawable);
        }
        typedArrayObtainStyledAttributes.recycle();
        this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    public int getFooterColor() {
        return this.mPaintFooterLine.getColor();
    }

    public void setFooterColor(int i) {
        this.mPaintFooterLine.setColor(i);
        this.mPaintFooterIndicator.setColor(i);
        invalidate();
    }

    public float getFooterLineHeight() {
        return this.mFooterLineHeight;
    }

    public void setFooterLineHeight(float f) {
        this.mFooterLineHeight = f;
        this.mPaintFooterLine.setStrokeWidth(f);
        invalidate();
    }

    public float getFooterIndicatorHeight() {
        return this.mFooterIndicatorHeight;
    }

    public void setFooterIndicatorHeight(float f) {
        this.mFooterIndicatorHeight = f;
        invalidate();
    }

    public float getFooterIndicatorPadding() {
        return this.mFooterPadding;
    }

    public void setFooterIndicatorPadding(float f) {
        this.mFooterPadding = f;
        invalidate();
    }

    public IndicatorStyle getFooterIndicatorStyle() {
        return this.mFooterIndicatorStyle;
    }

    public void setFooterIndicatorStyle(IndicatorStyle indicatorStyle) {
        this.mFooterIndicatorStyle = indicatorStyle;
        invalidate();
    }

    public LinePosition getLinePosition() {
        return this.mLinePosition;
    }

    public void setLinePosition(LinePosition linePosition) {
        this.mLinePosition = linePosition;
        invalidate();
    }

    public int getSelectedColor() {
        return this.mColorSelected;
    }

    public void setSelectedColor(int i) {
        this.mColorSelected = i;
        invalidate();
    }

    public boolean isSelectedBold() {
        return this.mBoldText;
    }

    public void setSelectedBold(boolean z) {
        this.mBoldText = z;
        invalidate();
    }

    public int getTextColor() {
        return this.mColorText;
    }

    public void setTextColor(int i) {
        this.mPaintText.setColor(i);
        this.mColorText = i;
        invalidate();
    }

    public float getTextSize() {
        return this.mPaintText.getTextSize();
    }

    public void setTextSize(float f) {
        this.mPaintText.setTextSize(f);
        invalidate();
    }

    public float getTitlePadding() {
        return this.mTitlePadding;
    }

    public void setTitlePadding(float f) {
        this.mTitlePadding = f;
        invalidate();
    }

    public float getTopPadding() {
        return this.mTopPadding;
    }

    public void setTopPadding(float f) {
        this.mTopPadding = f;
        invalidate();
    }

    public float getClipPadding() {
        return this.mClipPadding;
    }

    public void setClipPadding(float f) {
        this.mClipPadding = f;
        invalidate();
    }

    public void setTypeface(Typeface typeface) {
        this.mPaintText.setTypeface(typeface);
        invalidate();
    }

    public Typeface getTypeface() {
        return this.mPaintText.getTypeface();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) throws Resources.NotFoundException {
        int count;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        float f;
        int i7;
        ViewPager viewPager;
        super.onDraw(canvas);
        ViewPager viewPager2 = this.mViewPager;
        if (viewPager2 == null || (count = viewPager2.getAdapter().getCount()) == 0) {
            return;
        }
        if (this.mCurrentPage == -1 && (viewPager = this.mViewPager) != null) {
            this.mCurrentPage = viewPager.getCurrentItem();
        }
        ArrayList<Rect> arrayListCalculateAllBounds = calculateAllBounds(this.mPaintText);
        int size = arrayListCalculateAllBounds.size();
        if (this.mCurrentPage >= size) {
            setCurrentItem(size - 1);
            return;
        }
        int i8 = count - 1;
        float width = getWidth() / 2.0f;
        int left = getLeft();
        float f2 = left + this.mClipPadding;
        int width2 = getWidth();
        int height = getHeight();
        int i9 = left + width2;
        float f3 = i9 - this.mClipPadding;
        int i10 = this.mCurrentPage;
        float f4 = this.mPageOffset;
        if (f4 <= 0.5d) {
            i = i10;
        } else {
            i = i10 + 1;
            f4 = 1.0f - f4;
        }
        boolean z = f4 <= SELECTION_FADE_PERCENTAGE;
        boolean z2 = f4 <= BOLD_FADE_PERCENTAGE;
        float f5 = (SELECTION_FADE_PERCENTAGE - f4) / SELECTION_FADE_PERCENTAGE;
        Rect rect = arrayListCalculateAllBounds.get(i10);
        float f6 = rect.right - rect.left;
        if (rect.left < f2) {
            clipViewOnTheLeft(rect, f6, left);
        }
        if (rect.right > f3) {
            clipViewOnTheRight(rect, f6, i9);
        }
        int i11 = this.mCurrentPage;
        if (i11 > 0) {
            int i12 = i11 - 1;
            while (i12 >= 0) {
                Rect rect2 = arrayListCalculateAllBounds.get(i12);
                if (rect2.left < f2) {
                    f = f2;
                    int i13 = rect2.right - rect2.left;
                    clipViewOnTheLeft(rect2, i13, left);
                    Rect rect3 = arrayListCalculateAllBounds.get(i12 + 1);
                    i7 = width2;
                    if (rect2.right + this.mTitlePadding > rect3.left) {
                        rect2.left = (int) ((rect3.left - i13) - this.mTitlePadding);
                        rect2.right = rect2.left + i13;
                    }
                } else {
                    f = f2;
                    i7 = width2;
                }
                i12--;
                f2 = f;
                width2 = i7;
            }
        }
        int i14 = width2;
        int i15 = this.mCurrentPage;
        if (i15 < i8) {
            for (int i16 = i15 + 1; i16 < count; i16++) {
                Rect rect4 = arrayListCalculateAllBounds.get(i16);
                if (rect4.right > f3) {
                    int i17 = rect4.right - rect4.left;
                    clipViewOnTheRight(rect4, i17, i9);
                    Rect rect5 = arrayListCalculateAllBounds.get(i16 - 1);
                    if (rect4.left - this.mTitlePadding < rect5.right) {
                        rect4.left = (int) (rect5.right + this.mTitlePadding);
                        rect4.right = rect4.left + i17;
                    }
                }
            }
        }
        int i18 = this.mColorText >>> 24;
        int i19 = 0;
        while (i19 < count) {
            Rect rect6 = arrayListCalculateAllBounds.get(i19);
            if ((rect6.left <= left || rect6.left >= i9) && (rect6.right <= left || rect6.right >= i9)) {
                i3 = i9;
                i4 = i18;
                i5 = i14;
                i6 = count;
            } else {
                boolean z3 = i19 == i;
                CharSequence title = getTitle(i19);
                this.mPaintText.setFakeBoldText(z3 && z2 && this.mBoldText);
                this.mPaintText.setColor(this.mColorText);
                if (z3 && z) {
                    this.mPaintText.setAlpha(i18 - ((int) (i18 * f5)));
                }
                if (i19 < size - 1) {
                    Rect rect7 = arrayListCalculateAllBounds.get(i19 + 1);
                    if (rect6.right + this.mTitlePadding > rect7.left) {
                        int i20 = rect6.right - rect6.left;
                        rect6.left = (int) ((rect7.left - i20) - this.mTitlePadding);
                        rect6.right = rect6.left + i20;
                    }
                }
                i3 = i9;
                i4 = i18;
                i5 = i14;
                i6 = count;
                canvas.drawText(title, 0, title.length(), rect6.left, rect6.bottom + this.mTopPadding, this.mPaintText);
                if (z3 && z) {
                    this.mPaintText.setColor(this.mColorSelected);
                    this.mPaintText.setAlpha((int) ((this.mColorSelected >>> 24) * f5));
                    canvas.drawText(title, 0, title.length(), rect6.left, rect6.bottom + this.mTopPadding, this.mPaintText);
                }
            }
            i19++;
            count = i6;
            i9 = i3;
            i14 = i5;
            i18 = i4;
        }
        int i21 = i14;
        float f7 = this.mFooterLineHeight;
        float f8 = this.mFooterIndicatorHeight;
        if (this.mLinePosition == LinePosition.Top) {
            f7 = -f7;
            f8 = -f8;
            i2 = 0;
        } else {
            i2 = height;
        }
        this.mPath.reset();
        float f9 = i2;
        float f10 = f9 - (f7 / 2.0f);
        this.mPath.moveTo(0.0f, f10);
        this.mPath.lineTo(i21, f10);
        this.mPath.close();
        canvas.drawPath(this.mPath, this.mPaintFooterLine);
        float f11 = f9 - f7;
        int i22 = AnonymousClass1.$SwitchMap$com$viewpagerindicator$TitlePageIndicator$IndicatorStyle[this.mFooterIndicatorStyle.ordinal()];
        if (i22 == 1) {
            this.mPath.reset();
            this.mPath.moveTo(width, f11 - f8);
            this.mPath.lineTo(width + f8, f11);
            this.mPath.lineTo(width - f8, f11);
            this.mPath.close();
            canvas.drawPath(this.mPath, this.mPaintFooterIndicator);
            return;
        }
        if (i22 == 2 && z && i < size) {
            Rect rect8 = arrayListCalculateAllBounds.get(i);
            float f12 = rect8.right + this.mFooterIndicatorUnderlinePadding;
            float f13 = rect8.left - this.mFooterIndicatorUnderlinePadding;
            float f14 = f11 - f8;
            this.mPath.reset();
            this.mPath.moveTo(f13, f11);
            this.mPath.lineTo(f12, f11);
            this.mPath.lineTo(f12, f14);
            this.mPath.lineTo(f13, f14);
            this.mPath.close();
            this.mPaintFooterIndicator.setAlpha((int) (f5 * 255.0f));
            canvas.drawPath(this.mPath, this.mPaintFooterIndicator);
            this.mPaintFooterIndicator.setAlpha(255);
        }
    }

    /* renamed from: com.viewpagerindicator.TitlePageIndicator$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$viewpagerindicator$TitlePageIndicator$IndicatorStyle;

        static {
            int[] iArr = new int[IndicatorStyle.values().length];
            $SwitchMap$com$viewpagerindicator$TitlePageIndicator$IndicatorStyle = iArr;
            try {
                iArr[IndicatorStyle.Triangle.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$viewpagerindicator$TitlePageIndicator$IndicatorStyle[IndicatorStyle.Underline.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00a1  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r9) throws android.content.res.Resources.NotFoundException {
        /*
            Method dump skipped, instructions count: 273
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.viewpagerindicator.TitlePageIndicator.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void clipViewOnTheRight(Rect rect, float f, int i) {
        rect.right = (int) (i - this.mClipPadding);
        rect.left = (int) (rect.right - f);
    }

    private void clipViewOnTheLeft(Rect rect, float f, int i) {
        rect.left = (int) (i + this.mClipPadding);
        rect.right = (int) (this.mClipPadding + f);
    }

    private ArrayList<Rect> calculateAllBounds(Paint paint) {
        ArrayList<Rect> arrayList = new ArrayList<>();
        int count = this.mViewPager.getAdapter().getCount();
        int width = getWidth();
        int i = width / 2;
        for (int i2 = 0; i2 < count; i2++) {
            Rect rectCalcBounds = calcBounds(i2, paint);
            int i3 = rectCalcBounds.right - rectCalcBounds.left;
            int i4 = rectCalcBounds.bottom - rectCalcBounds.top;
            rectCalcBounds.left = (int) ((i - (i3 / 2.0f)) + (((i2 - this.mCurrentPage) - this.mPageOffset) * width));
            rectCalcBounds.right = rectCalcBounds.left + i3;
            rectCalcBounds.top = 0;
            rectCalcBounds.bottom = i4;
            arrayList.add(rectCalcBounds);
        }
        return arrayList;
    }

    private Rect calcBounds(int i, Paint paint) {
        Rect rect = new Rect();
        CharSequence title = getTitle(i);
        rect.right = (int) paint.measureText(title, 0, title.length());
        rect.bottom = (int) (paint.descent() - paint.ascent());
        return rect;
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
    public void notifyDataSetChanged() {
        invalidate();
    }

    public void setOnCenterItemClickListener(OnCenterItemClickListener onCenterItemClickListener) {
        this.mCenterItemClickListener = onCenterItemClickListener;
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

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        this.mScrollState = i;
        ViewPager.OnPageChangeListener onPageChangeListener = this.mListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        this.mCurrentPage = i;
        this.mPageOffset = f;
        invalidate();
        ViewPager.OnPageChangeListener onPageChangeListener = this.mListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i, f, i2);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        if (this.mScrollState == 0) {
            this.mCurrentPage = i;
            invalidate();
        }
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
        float size;
        int size2 = View.MeasureSpec.getSize(i);
        if (View.MeasureSpec.getMode(i2) == 1073741824) {
            size = View.MeasureSpec.getSize(i2);
        } else {
            this.mBounds.setEmpty();
            this.mBounds.bottom = (int) (this.mPaintText.descent() - this.mPaintText.ascent());
            size = (this.mBounds.bottom - this.mBounds.top) + this.mFooterLineHeight + this.mFooterPadding + this.mTopPadding;
            if (this.mFooterIndicatorStyle != IndicatorStyle.None) {
                size += this.mFooterIndicatorHeight;
            }
        }
        setMeasuredDimension(size2, (int) size);
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
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.viewpagerindicator.TitlePageIndicator.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int currentPage;

        /* synthetic */ SavedState(Parcel parcel, AnonymousClass1 anonymousClass1) {
            this(parcel);
        }

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

    private CharSequence getTitle(int i) {
        CharSequence pageTitle = this.mViewPager.getAdapter().getPageTitle(i);
        return pageTitle == null ? "" : pageTitle;
    }
}

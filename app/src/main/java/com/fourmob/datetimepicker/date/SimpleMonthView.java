package com.fourmob.datetimepicker.date;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.view.MotionEvent;
import android.view.View;
import com.fourmob.datetimepicker.R;
import com.fourmob.datetimepicker.Utils;
import com.fourmob.datetimepicker.date.SimpleMonthAdapter;
import java.security.InvalidParameterException;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes.dex */
public class SimpleMonthView extends View {
    protected static int DAY_SELECTED_CIRCLE_SIZE = 0;
    protected static int DAY_SEPARATOR_WIDTH = 1;
    protected static int DEFAULT_HEIGHT = 32;
    protected static final int DEFAULT_NUM_ROWS = 6;
    protected static int MINI_DAY_NUMBER_TEXT_SIZE = 0;
    protected static int MIN_HEIGHT = 10;
    protected static int MONTH_DAY_LABEL_TEXT_SIZE = 0;
    protected static int MONTH_HEADER_SIZE = 0;
    protected static int MONTH_LABEL_TEXT_SIZE = 0;
    private static final int SELECTED_CIRCLE_ALPHA = 60;
    public static final String VIEW_PARAMS_FOCUS_MONTH = "focus_month";
    public static final String VIEW_PARAMS_HEIGHT = "height";
    public static final String VIEW_PARAMS_MONTH = "month";
    public static final String VIEW_PARAMS_NUM_DAYS = "num_days";
    public static final String VIEW_PARAMS_SELECTED_DAY = "selected_day";
    public static final String VIEW_PARAMS_SHOW_WK_NUM = "show_wk_num";
    public static final String VIEW_PARAMS_WEEK_START = "week_start";
    public static final String VIEW_PARAMS_YEAR = "year";
    protected static float mScale;
    private final Calendar mCalendar;
    private DateFormatSymbols mDateFormatSymbols;
    private final Calendar mDayLabelCalendar;
    private int mDayOfWeekStart;
    private String mDayOfWeekTypeface;
    protected int mDayTextColor;
    protected int mFirstJulianDay;
    protected int mFirstMonth;
    private final Formatter mFormatter;
    protected boolean mHasToday;
    protected int mLastMonth;
    protected int mMonth;
    protected Paint mMonthDayLabelPaint;
    protected Paint mMonthNumPaint;
    protected int mMonthTitleBGColor;
    protected Paint mMonthTitleBGPaint;
    protected int mMonthTitleColor;
    protected Paint mMonthTitlePaint;
    private String mMonthTitleTypeface;
    protected int mNumCells;
    protected int mNumDays;
    private int mNumRows;
    private OnDayClickListener mOnDayClickListener;
    protected int mPadding;
    protected int mRowHeight;
    protected Paint mSelectedCirclePaint;
    protected int mSelectedDay;
    protected int mSelectedLeft;
    protected int mSelectedRight;
    private final StringBuilder mStringBuilder;
    protected int mToday;
    protected int mTodayNumberColor;
    protected int mWeekStart;
    protected int mWidth;
    protected int mYear;

    public interface OnDayClickListener {
        void onDayClick(SimpleMonthView simpleMonthView, SimpleMonthAdapter.CalendarDay calendarDay);
    }

    public SimpleMonthView(Context context) {
        super(context);
        this.mPadding = 0;
        this.mFirstJulianDay = -1;
        this.mFirstMonth = -1;
        this.mLastMonth = -1;
        this.mHasToday = false;
        this.mSelectedDay = -1;
        this.mToday = -1;
        this.mWeekStart = 1;
        this.mNumDays = 7;
        this.mNumCells = 7;
        this.mSelectedLeft = -1;
        this.mSelectedRight = -1;
        this.mDayOfWeekStart = 0;
        this.mRowHeight = DEFAULT_HEIGHT;
        this.mNumRows = 6;
        this.mDateFormatSymbols = new DateFormatSymbols();
        Resources resources = context.getResources();
        this.mDayLabelCalendar = Calendar.getInstance();
        this.mCalendar = Calendar.getInstance();
        this.mDayOfWeekTypeface = resources.getString(R.string.day_of_week_label_typeface);
        this.mMonthTitleTypeface = resources.getString(R.string.sans_serif);
        this.mDayTextColor = resources.getColor(R.color.date_picker_text_normal);
        this.mTodayNumberColor = resources.getColor(R.color.blue);
        this.mMonthTitleColor = resources.getColor(R.color.white);
        this.mMonthTitleBGColor = resources.getColor(R.color.circle_background);
        StringBuilder sb = new StringBuilder(50);
        this.mStringBuilder = sb;
        this.mFormatter = new Formatter(sb, Locale.getDefault());
        MINI_DAY_NUMBER_TEXT_SIZE = resources.getDimensionPixelSize(R.dimen.day_number_size);
        MONTH_LABEL_TEXT_SIZE = resources.getDimensionPixelSize(R.dimen.month_label_size);
        MONTH_DAY_LABEL_TEXT_SIZE = resources.getDimensionPixelSize(R.dimen.month_day_label_text_size);
        MONTH_HEADER_SIZE = resources.getDimensionPixelOffset(R.dimen.month_list_item_header_height);
        DAY_SELECTED_CIRCLE_SIZE = resources.getDimensionPixelSize(R.dimen.day_number_select_circle_radius);
        this.mRowHeight = (resources.getDimensionPixelOffset(R.dimen.date_picker_view_animator_height) - MONTH_HEADER_SIZE) / 6;
        initView();
    }

    private int calculateNumRows() {
        int iFindDayOffset = findDayOffset();
        int i = this.mNumCells;
        int i2 = this.mNumDays;
        return ((iFindDayOffset + i) / i2) + ((iFindDayOffset + i) % i2 > 0 ? 1 : 0);
    }

    private void drawMonthDayLabels(Canvas canvas) {
        int i = MONTH_HEADER_SIZE - (MONTH_DAY_LABEL_TEXT_SIZE / 2);
        int i2 = (this.mWidth - (this.mPadding * 2)) / (this.mNumDays * 2);
        int i3 = 0;
        while (true) {
            int i4 = this.mNumDays;
            if (i3 >= i4) {
                return;
            }
            int i5 = (this.mWeekStart + i3) % i4;
            int i6 = (((i3 * 2) + 1) * i2) + this.mPadding;
            this.mDayLabelCalendar.set(7, i5);
            canvas.drawText(this.mDateFormatSymbols.getShortWeekdays()[this.mDayLabelCalendar.get(7)].toUpperCase(Locale.getDefault()), i6, i, this.mMonthDayLabelPaint);
            i3++;
        }
    }

    private void drawMonthTitle(Canvas canvas) {
        canvas.drawText(getMonthAndYearString(), (this.mWidth + (this.mPadding * 2)) / 2, ((MONTH_HEADER_SIZE - MONTH_DAY_LABEL_TEXT_SIZE) / 2) + (MONTH_LABEL_TEXT_SIZE / 3), this.mMonthTitlePaint);
    }

    private int findDayOffset() {
        int i = this.mDayOfWeekStart;
        int i2 = this.mWeekStart;
        if (i < i2) {
            i += this.mNumDays;
        }
        return i - i2;
    }

    private String getMonthAndYearString() {
        this.mStringBuilder.setLength(0);
        long timeInMillis = this.mCalendar.getTimeInMillis();
        return DateUtils.formatDateRange(getContext(), timeInMillis, timeInMillis, 52);
    }

    private void onDayClick(SimpleMonthAdapter.CalendarDay calendarDay) {
        OnDayClickListener onDayClickListener = this.mOnDayClickListener;
        if (onDayClickListener != null) {
            onDayClickListener.onDayClick(this, calendarDay);
        }
    }

    private boolean sameDay(int i, Time time) {
        return this.mYear == time.year && this.mMonth == time.month && i == time.monthDay;
    }

    protected void drawMonthNums(Canvas canvas) {
        int i = (((this.mRowHeight + MINI_DAY_NUMBER_TEXT_SIZE) / 2) - DAY_SEPARATOR_WIDTH) + MONTH_HEADER_SIZE;
        int i2 = (this.mWidth - (this.mPadding * 2)) / (this.mNumDays * 2);
        int iFindDayOffset = findDayOffset();
        for (int i3 = 1; i3 <= this.mNumCells; i3++) {
            int i4 = (((iFindDayOffset * 2) + 1) * i2) + this.mPadding;
            if (this.mSelectedDay == i3) {
                canvas.drawCircle(i4, i - (MINI_DAY_NUMBER_TEXT_SIZE / 3), DAY_SELECTED_CIRCLE_SIZE, this.mSelectedCirclePaint);
            }
            if (this.mHasToday && this.mToday == i3) {
                this.mMonthNumPaint.setColor(this.mTodayNumberColor);
            } else {
                this.mMonthNumPaint.setColor(this.mDayTextColor);
            }
            canvas.drawText(String.format("%d", Integer.valueOf(i3)), i4, i, this.mMonthNumPaint);
            iFindDayOffset++;
            if (iFindDayOffset == this.mNumDays) {
                i += this.mRowHeight;
                iFindDayOffset = 0;
            }
        }
    }

    public SimpleMonthAdapter.CalendarDay getDayFromLocation(float f, float f2) {
        float f3 = this.mPadding;
        if (f < f3) {
            return null;
        }
        int i = this.mWidth;
        if (f > i - r0) {
            return null;
        }
        return new SimpleMonthAdapter.CalendarDay(this.mYear, this.mMonth, (((int) (((f - f3) * this.mNumDays) / ((i - r0) - r0))) - findDayOffset()) + 1 + ((((int) (f2 - MONTH_HEADER_SIZE)) / this.mRowHeight) * this.mNumDays));
    }

    protected void initView() {
        Paint paint = new Paint();
        this.mMonthTitlePaint = paint;
        paint.setFakeBoldText(true);
        this.mMonthTitlePaint.setAntiAlias(true);
        this.mMonthTitlePaint.setTextSize(MONTH_LABEL_TEXT_SIZE);
        this.mMonthTitlePaint.setTypeface(Typeface.create(this.mMonthTitleTypeface, 1));
        this.mMonthTitlePaint.setColor(this.mDayTextColor);
        this.mMonthTitlePaint.setTextAlign(Paint.Align.CENTER);
        this.mMonthTitlePaint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.mMonthTitleBGPaint = paint2;
        paint2.setFakeBoldText(true);
        this.mMonthTitleBGPaint.setAntiAlias(true);
        this.mMonthTitleBGPaint.setColor(this.mMonthTitleBGColor);
        this.mMonthTitleBGPaint.setTextAlign(Paint.Align.CENTER);
        this.mMonthTitleBGPaint.setStyle(Paint.Style.FILL);
        Paint paint3 = new Paint();
        this.mSelectedCirclePaint = paint3;
        paint3.setFakeBoldText(true);
        this.mSelectedCirclePaint.setAntiAlias(true);
        this.mSelectedCirclePaint.setColor(this.mTodayNumberColor);
        this.mSelectedCirclePaint.setTextAlign(Paint.Align.CENTER);
        this.mSelectedCirclePaint.setStyle(Paint.Style.FILL);
        this.mSelectedCirclePaint.setAlpha(60);
        Paint paint4 = new Paint();
        this.mMonthDayLabelPaint = paint4;
        paint4.setAntiAlias(true);
        this.mMonthDayLabelPaint.setTextSize(MONTH_DAY_LABEL_TEXT_SIZE);
        this.mMonthDayLabelPaint.setColor(this.mDayTextColor);
        this.mMonthDayLabelPaint.setTypeface(Typeface.create(this.mDayOfWeekTypeface, 0));
        this.mMonthDayLabelPaint.setStyle(Paint.Style.FILL);
        this.mMonthDayLabelPaint.setTextAlign(Paint.Align.CENTER);
        this.mMonthDayLabelPaint.setFakeBoldText(true);
        Paint paint5 = new Paint();
        this.mMonthNumPaint = paint5;
        paint5.setAntiAlias(true);
        this.mMonthNumPaint.setTextSize(MINI_DAY_NUMBER_TEXT_SIZE);
        this.mMonthNumPaint.setStyle(Paint.Style.FILL);
        this.mMonthNumPaint.setTextAlign(Paint.Align.CENTER);
        this.mMonthNumPaint.setFakeBoldText(false);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        drawMonthTitle(canvas);
        drawMonthDayLabels(canvas);
        drawMonthNums(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(View.MeasureSpec.getSize(i), (this.mRowHeight * this.mNumRows) + MONTH_HEADER_SIZE);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.mWidth = i;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        SimpleMonthAdapter.CalendarDay dayFromLocation;
        if (motionEvent.getAction() == 1 && (dayFromLocation = getDayFromLocation(motionEvent.getX(), motionEvent.getY())) != null) {
            onDayClick(dayFromLocation);
        }
        return true;
    }

    public void reuse() {
        this.mNumRows = 6;
        requestLayout();
    }

    public void setMonthParams(HashMap<String, Integer> map) {
        if (!map.containsKey(VIEW_PARAMS_MONTH) && !map.containsKey(VIEW_PARAMS_YEAR)) {
            throw new InvalidParameterException("You must specify month and year for this view");
        }
        setTag(map);
        if (map.containsKey(VIEW_PARAMS_HEIGHT)) {
            int iIntValue = map.get(VIEW_PARAMS_HEIGHT).intValue();
            this.mRowHeight = iIntValue;
            int i = MIN_HEIGHT;
            if (iIntValue < i) {
                this.mRowHeight = i;
            }
        }
        if (map.containsKey(VIEW_PARAMS_SELECTED_DAY)) {
            this.mSelectedDay = map.get(VIEW_PARAMS_SELECTED_DAY).intValue();
        }
        this.mMonth = map.get(VIEW_PARAMS_MONTH).intValue();
        this.mYear = map.get(VIEW_PARAMS_YEAR).intValue();
        Time time = new Time(Time.getCurrentTimezone());
        time.setToNow();
        int i2 = 0;
        this.mHasToday = false;
        this.mToday = -1;
        this.mCalendar.set(2, this.mMonth);
        this.mCalendar.set(1, this.mYear);
        this.mCalendar.set(5, 1);
        this.mDayOfWeekStart = this.mCalendar.get(7);
        if (map.containsKey("week_start")) {
            this.mWeekStart = map.get("week_start").intValue();
        } else {
            this.mWeekStart = this.mCalendar.getFirstDayOfWeek();
        }
        this.mNumCells = Utils.getDaysInMonth(this.mMonth, this.mYear);
        while (i2 < this.mNumCells) {
            i2++;
            if (sameDay(i2, time)) {
                this.mHasToday = true;
                this.mToday = i2;
            }
        }
        this.mNumRows = calculateNumRows();
    }

    public void setOnDayClickListener(OnDayClickListener onDayClickListener) {
        this.mOnDayClickListener = onDayClickListener;
    }
}

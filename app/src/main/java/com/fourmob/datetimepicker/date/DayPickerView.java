package com.fourmob.datetimepicker.date;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.fourmob.datetimepicker.date.SimpleMonthAdapter;

/* loaded from: classes.dex */
public class DayPickerView extends ListView implements AbsListView.OnScrollListener, DatePickerDialog.OnDateChangedListener {
    protected static final int GOTO_SCROLL_DURATION = 250;
    public static int LIST_TOP_OFFSET = -1;
    protected static final int SCROLL_CHANGE_DELAY = 40;
    protected SimpleMonthAdapter mAdapter;
    protected Context mContext;
    private final DatePickerController mController;
    protected int mCurrentMonthDisplayed;
    protected int mCurrentScrollState;
    protected int mDaysPerWeek;
    protected float mFriction;
    protected Handler mHandler;
    protected int mNumWeeks;
    private boolean mPerformingScroll;
    protected long mPreviousScrollPosition;
    protected int mPreviousScrollState;
    protected ScrollStateRunnable mScrollStateChangedRunnable;
    protected SimpleMonthAdapter.CalendarDay mSelectedDay;
    protected boolean mShowWeekNumber;
    protected SimpleMonthAdapter.CalendarDay mTempDay;

    public DayPickerView(Context context, DatePickerController datePickerController) {
        super(context);
        this.mHandler = new Handler();
        this.mCurrentScrollState = 0;
        this.mPreviousScrollState = 0;
        this.mScrollStateChangedRunnable = new ScrollStateRunnable();
        this.mSelectedDay = new SimpleMonthAdapter.CalendarDay();
        this.mTempDay = new SimpleMonthAdapter.CalendarDay();
        this.mNumWeeks = 6;
        this.mShowWeekNumber = false;
        this.mDaysPerWeek = 7;
        this.mFriction = 1.0f;
        this.mController = datePickerController;
        datePickerController.registerOnDateChangedListener(this);
        setLayoutParams(new AbsListView.LayoutParams(-1, -1));
        setDrawSelectorOnTop(false);
        init(context);
        onDateChanged();
    }

    public int getMostVisiblePosition() {
        int firstVisiblePosition = getFirstVisiblePosition();
        int height = getHeight();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i < height) {
            View childAt = getChildAt(i2);
            if (childAt == null) {
                break;
            }
            int bottom = childAt.getBottom();
            int iMin = Math.min(bottom, height) - Math.max(0, childAt.getTop());
            if (iMin > i3) {
                i4 = i2;
                i3 = iMin;
            }
            i2++;
            i = bottom;
        }
        return firstVisiblePosition + i4;
    }

    public boolean goTo(SimpleMonthAdapter.CalendarDay calendarDay, boolean z, boolean z2, boolean z3) {
        View childAt;
        if (z2) {
            this.mSelectedDay.set(calendarDay);
        }
        this.mTempDay.set(calendarDay);
        int minYear = ((calendarDay.year - this.mController.getMinYear()) * 12) + calendarDay.month;
        int i = 0;
        while (true) {
            int i2 = i + 1;
            childAt = getChildAt(i);
            if (childAt == null || childAt.getTop() >= 0) {
                break;
            }
            i = i2;
        }
        int positionForView = childAt != null ? getPositionForView(childAt) : 0;
        if (z2) {
            this.mAdapter.setSelectedDay(this.mSelectedDay);
        }
        if (minYear != positionForView || z3) {
            setMonthDisplayed(this.mTempDay);
            this.mPreviousScrollState = 2;
            if (z && Build.VERSION.SDK_INT >= 11) {
                smoothScrollToPositionFromTop(minYear, LIST_TOP_OFFSET, 250);
                return true;
            }
            postSetSelection(minYear);
        } else if (z2) {
            setMonthDisplayed(this.mSelectedDay);
        }
        return false;
    }

    public void init(Context context) {
        this.mContext = context;
        setUpListView();
        setUpAdapter();
        setAdapter((ListAdapter) this.mAdapter);
    }

    @Override // android.widget.ListView, android.widget.AbsListView
    protected void layoutChildren() {
        super.layoutChildren();
        if (this.mPerformingScroll) {
            this.mPerformingScroll = false;
        }
    }

    public void onChange() {
        setUpAdapter();
        setAdapter((ListAdapter) this.mAdapter);
    }

    @Override // com.fourmob.datetimepicker.date.DatePickerDialog.OnDateChangedListener
    public void onDateChanged() {
        goTo(this.mController.getSelectedDay(), false, true, true);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (((SimpleMonthView) absListView.getChildAt(0)) == null) {
            return;
        }
        this.mPreviousScrollPosition = (absListView.getFirstVisiblePosition() * r2.getHeight()) - r2.getBottom();
        this.mPreviousScrollState = this.mCurrentScrollState;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.mScrollStateChangedRunnable.doScrollStateChange(absListView, i);
    }

    public void postSetSelection(final int i) {
        clearFocus();
        post(new Runnable() { // from class: com.fourmob.datetimepicker.date.DayPickerView.1
            @Override // java.lang.Runnable
            public void run() {
                DayPickerView.this.setSelection(i);
            }
        });
        onScrollStateChanged(this, 0);
    }

    protected void setMonthDisplayed(SimpleMonthAdapter.CalendarDay calendarDay) {
        this.mCurrentMonthDisplayed = calendarDay.month;
        invalidateViews();
    }

    protected void setUpAdapter() {
        if (this.mAdapter == null) {
            this.mAdapter = new SimpleMonthAdapter(getContext(), this.mController);
        }
        this.mAdapter.setSelectedDay(this.mSelectedDay);
        this.mAdapter.notifyDataSetChanged();
    }

    protected void setUpListView() {
        setCacheColorHint(0);
        setDivider(null);
        setItemsCanFocus(true);
        setFastScrollEnabled(false);
        setVerticalScrollBarEnabled(false);
        setOnScrollListener(this);
        setFadingEdgeLength(0);
        setFrictionIfSupported(ViewConfiguration.getScrollFriction() * this.mFriction);
    }

    void setFrictionIfSupported(float f) {
        if (Build.VERSION.SDK_INT >= 11) {
            setFriction(f);
        }
    }

    protected class ScrollStateRunnable implements Runnable {
        private int mNewState;

        protected ScrollStateRunnable() {
        }

        public void doScrollStateChange(AbsListView absListView, int i) {
            DayPickerView.this.mHandler.removeCallbacks(this);
            this.mNewState = i;
            DayPickerView.this.mHandler.postDelayed(this, 40L);
        }

        @Override // java.lang.Runnable
        public void run() {
            DayPickerView.this.mCurrentScrollState = this.mNewState;
            if (this.mNewState == 0 && DayPickerView.this.mPreviousScrollState != 0) {
                if (DayPickerView.this.mPreviousScrollState != 1) {
                    DayPickerView.this.mPreviousScrollState = this.mNewState;
                    View childAt = DayPickerView.this.getChildAt(0);
                    int i = 0;
                    while (childAt != null && childAt.getBottom() <= 0) {
                        i++;
                        childAt = DayPickerView.this.getChildAt(i);
                    }
                    if (childAt == null) {
                        return;
                    }
                    boolean z = (DayPickerView.this.getFirstVisiblePosition() == 0 || DayPickerView.this.getLastVisiblePosition() == DayPickerView.this.getCount() - 1) ? false : true;
                    int top = childAt.getTop();
                    int bottom = childAt.getBottom();
                    int height = DayPickerView.this.getHeight() / 2;
                    if (!z || top >= DayPickerView.LIST_TOP_OFFSET) {
                        return;
                    }
                    if (bottom > height) {
                        DayPickerView.this.smoothScrollBy(top, 250);
                        return;
                    } else {
                        DayPickerView.this.smoothScrollBy(bottom, 250);
                        return;
                    }
                }
            }
            DayPickerView.this.mPreviousScrollState = this.mNewState;
        }
    }
}

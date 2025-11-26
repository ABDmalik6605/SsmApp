package com.fourmob.datetimepicker.date;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import com.fourmob.datetimepicker.date.SimpleMonthView;
import java.util.Calendar;
import java.util.HashMap;

/* loaded from: classes.dex */
public class SimpleMonthAdapter extends BaseAdapter implements SimpleMonthView.OnDayClickListener {
    protected static final int MONTHS_IN_YEAR = 12;
    protected static int WEEK_7_OVERHANG_HEIGHT = 7;
    private final Context mContext;
    private final DatePickerController mController;
    private CalendarDay mSelectedDay;

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public SimpleMonthAdapter(Context context, DatePickerController datePickerController) {
        this.mContext = context;
        this.mController = datePickerController;
        init();
        setSelectedDay(datePickerController.getSelectedDay());
    }

    private boolean isSelectedDayInMonth(int i, int i2) {
        return this.mSelectedDay.year == i && this.mSelectedDay.month == i2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return ((this.mController.getMaxYear() - this.mController.getMinYear()) + 1) * 12;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        SimpleMonthView simpleMonthView;
        HashMap<String, Integer> map;
        if (view != null) {
            simpleMonthView = (SimpleMonthView) view;
            map = (HashMap) simpleMonthView.getTag();
        } else {
            simpleMonthView = new SimpleMonthView(this.mContext);
            simpleMonthView.setLayoutParams(new AbsListView.LayoutParams(-1, -1));
            simpleMonthView.setClickable(true);
            simpleMonthView.setOnDayClickListener(this);
            map = null;
        }
        if (map == null) {
            map = new HashMap<>();
        }
        map.clear();
        int i2 = i % 12;
        int minYear = (i / 12) + this.mController.getMinYear();
        int i3 = isSelectedDayInMonth(minYear, i2) ? this.mSelectedDay.day : -1;
        simpleMonthView.reuse();
        map.put(SimpleMonthView.VIEW_PARAMS_SELECTED_DAY, Integer.valueOf(i3));
        map.put(SimpleMonthView.VIEW_PARAMS_YEAR, Integer.valueOf(minYear));
        map.put(SimpleMonthView.VIEW_PARAMS_MONTH, Integer.valueOf(i2));
        map.put("week_start", Integer.valueOf(this.mController.getFirstDayOfWeek()));
        simpleMonthView.setMonthParams(map);
        simpleMonthView.invalidate();
        return simpleMonthView;
    }

    protected void init() {
        this.mSelectedDay = new CalendarDay(System.currentTimeMillis());
    }

    @Override // com.fourmob.datetimepicker.date.SimpleMonthView.OnDayClickListener
    public void onDayClick(SimpleMonthView simpleMonthView, CalendarDay calendarDay) {
        if (calendarDay != null) {
            onDayTapped(calendarDay);
        }
    }

    protected void onDayTapped(CalendarDay calendarDay) {
        this.mController.tryVibrate();
        this.mController.onDayOfMonthSelected(calendarDay.year, calendarDay.month, calendarDay.day);
        setSelectedDay(calendarDay);
    }

    public void setSelectedDay(CalendarDay calendarDay) {
        this.mSelectedDay = calendarDay;
        notifyDataSetChanged();
    }

    public static class CalendarDay {
        private Calendar calendar;
        int day;
        int month;
        int year;

        public CalendarDay() {
            setTime(System.currentTimeMillis());
        }

        public CalendarDay(int i, int i2, int i3) {
            setDay(i, i2, i3);
        }

        public CalendarDay(long j) {
            setTime(j);
        }

        public CalendarDay(Calendar calendar) {
            this.year = calendar.get(1);
            this.month = calendar.get(2);
            this.day = calendar.get(5);
        }

        private void setTime(long j) {
            if (this.calendar == null) {
                this.calendar = Calendar.getInstance();
            }
            this.calendar.setTimeInMillis(j);
            this.month = this.calendar.get(2);
            this.year = this.calendar.get(1);
            this.day = this.calendar.get(5);
        }

        public void set(CalendarDay calendarDay) {
            this.year = calendarDay.year;
            this.month = calendarDay.month;
            this.day = calendarDay.day;
        }

        public void setDay(int i, int i2, int i3) {
            this.year = i;
            this.month = i2;
            this.day = i3;
        }
    }
}

package com.fourmob.datetimepicker.date;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.fourmob.datetimepicker.R;
import com.fourmob.datetimepicker.Utils;
import com.fourmob.datetimepicker.date.SimpleMonthAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes.dex */
public class DatePickerDialog extends DialogFragment implements View.OnClickListener, DatePickerController {
    public static final int ANIMATION_DELAY = 500;
    public static final String KEY_CURRENT_VIEW = "current_view";
    public static final String KEY_LIST_POSITION = "list_position";
    public static final String KEY_LIST_POSITION_OFFSET = "list_position_offset";
    private static final String KEY_SELECTED_DAY = "day";
    private static final String KEY_SELECTED_MONTH = "month";
    private static final String KEY_SELECTED_YEAR = "year";
    private static final String KEY_VIBRATE = "vibrate";
    public static final String KEY_WEEK_START = "week_start";
    public static final String KEY_YEAR_END = "year_end";
    public static final String KEY_YEAR_START = "year_start";
    private static final int MAX_YEAR = 2037;
    private static final int MIN_YEAR = 1902;
    private static final int MONTH_AND_DAY_VIEW = 0;
    private static final int UNINITIALIZED = -1;
    private static final int YEAR_VIEW = 1;
    private AccessibleDateAnimator mAnimator;
    private final Calendar mCalendar;
    private OnDateSetListener mCallBack;
    private boolean mCloseOnSingleTapDay;
    private int mCurrentView;
    private DateFormatSymbols mDateFormatSymbols = new DateFormatSymbols();
    private TextView mDayOfWeekView;
    private String mDayPickerDescription;
    private DayPickerView mDayPickerView;
    private boolean mDelayAnimation;
    private Button mDoneButton;
    private long mLastVibrate;
    private HashSet<OnDateChangedListener> mListeners;
    private int mMaxYear;
    private int mMinYear;
    private LinearLayout mMonthAndDayView;
    private String mSelectDay;
    private String mSelectYear;
    private TextView mSelectedDayTextView;
    private TextView mSelectedMonthTextView;
    private boolean mVibrate;
    private Vibrator mVibrator;
    private int mWeekStart;
    private String mYearPickerDescription;
    private YearPickerView mYearPickerView;
    private TextView mYearView;
    private static SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("dd", Locale.getDefault());
    private static SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy", Locale.getDefault());

    interface OnDateChangedListener {
        void onDateChanged();
    }

    public interface OnDateSetListener {
        void onDateSet(DatePickerDialog datePickerDialog, int i, int i2, int i3);
    }

    private void adjustDayInMonthIfNeeded(int i, int i2) {
        int i3 = this.mCalendar.get(5);
        int daysInMonth = Utils.getDaysInMonth(i, i2);
        if (i3 > daysInMonth) {
            this.mCalendar.set(5, daysInMonth);
        }
    }

    public DatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        this.mCalendar = calendar;
        this.mListeners = new HashSet<>();
        this.mDelayAnimation = true;
        this.mCurrentView = -1;
        this.mWeekStart = calendar.getFirstDayOfWeek();
        this.mMaxYear = MAX_YEAR;
        this.mMinYear = MIN_YEAR;
        this.mVibrate = true;
    }

    public static DatePickerDialog newInstance(OnDateSetListener onDateSetListener, int i, int i2, int i3) {
        return newInstance(onDateSetListener, i, i2, i3, true);
    }

    public static DatePickerDialog newInstance(OnDateSetListener onDateSetListener, int i, int i2, int i3, boolean z) {
        DatePickerDialog datePickerDialog = new DatePickerDialog();
        datePickerDialog.initialize(onDateSetListener, i, i2, i3, z);
        return datePickerDialog;
    }

    public void setVibrate(boolean z) {
        this.mVibrate = z;
    }

    private void setCurrentView(int i) {
        setCurrentView(i, false);
    }

    private void setCurrentView(int i, boolean z) {
        long timeInMillis = this.mCalendar.getTimeInMillis();
        if (i == 0) {
            ObjectAnimator pulseAnimator = Utils.getPulseAnimator(this.mMonthAndDayView, 0.9f, 1.05f);
            if (this.mDelayAnimation) {
                pulseAnimator.setStartDelay(500L);
                this.mDelayAnimation = false;
            }
            this.mDayPickerView.onDateChanged();
            if (this.mCurrentView != i || z) {
                this.mMonthAndDayView.setSelected(true);
                this.mYearView.setSelected(false);
                this.mAnimator.setDisplayedChild(0);
                this.mCurrentView = i;
            }
            pulseAnimator.start();
            String dateTime = DateUtils.formatDateTime(getActivity(), timeInMillis, 16);
            this.mAnimator.setContentDescription(this.mDayPickerDescription + ": " + dateTime);
            Utils.tryAccessibilityAnnounce(this.mAnimator, this.mSelectDay);
            return;
        }
        if (i != 1) {
            return;
        }
        ObjectAnimator pulseAnimator2 = Utils.getPulseAnimator(this.mYearView, 0.85f, 1.1f);
        if (this.mDelayAnimation) {
            pulseAnimator2.setStartDelay(500L);
            this.mDelayAnimation = false;
        }
        this.mYearPickerView.onDateChanged();
        if (this.mCurrentView != i || z) {
            this.mMonthAndDayView.setSelected(false);
            this.mYearView.setSelected(true);
            this.mAnimator.setDisplayedChild(1);
            this.mCurrentView = i;
        }
        pulseAnimator2.start();
        String str = YEAR_FORMAT.format(Long.valueOf(timeInMillis));
        this.mAnimator.setContentDescription(this.mYearPickerDescription + ": " + str);
        Utils.tryAccessibilityAnnounce(this.mAnimator, this.mSelectYear);
    }

    private void updateDisplay(boolean z) {
        if (this.mDayOfWeekView != null) {
            this.mCalendar.setFirstDayOfWeek(this.mWeekStart);
            this.mDayOfWeekView.setText(this.mDateFormatSymbols.getWeekdays()[this.mCalendar.get(7)].toUpperCase(Locale.getDefault()));
        }
        this.mSelectedMonthTextView.setText(this.mDateFormatSymbols.getMonths()[this.mCalendar.get(2)].toUpperCase(Locale.getDefault()));
        this.mSelectedDayTextView.setText(DAY_FORMAT.format(this.mCalendar.getTime()));
        this.mYearView.setText(YEAR_FORMAT.format(this.mCalendar.getTime()));
        long timeInMillis = this.mCalendar.getTimeInMillis();
        this.mAnimator.setDateMillis(timeInMillis);
        this.mMonthAndDayView.setContentDescription(DateUtils.formatDateTime(getActivity(), timeInMillis, 24));
        if (z) {
            Utils.tryAccessibilityAnnounce(this.mAnimator, DateUtils.formatDateTime(getActivity(), timeInMillis, 20));
        }
    }

    private void updatePickers() {
        Iterator<OnDateChangedListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onDateChanged();
        }
    }

    @Override // com.fourmob.datetimepicker.date.DatePickerController
    public int getFirstDayOfWeek() {
        return this.mWeekStart;
    }

    @Override // com.fourmob.datetimepicker.date.DatePickerController
    public int getMaxYear() {
        return this.mMaxYear;
    }

    @Override // com.fourmob.datetimepicker.date.DatePickerController
    public int getMinYear() {
        return this.mMinYear;
    }

    @Override // com.fourmob.datetimepicker.date.DatePickerController
    public SimpleMonthAdapter.CalendarDay getSelectedDay() {
        return new SimpleMonthAdapter.CalendarDay(this.mCalendar);
    }

    public void initialize(OnDateSetListener onDateSetListener, int i, int i2, int i3, boolean z) {
        if (i > MAX_YEAR) {
            throw new IllegalArgumentException("year end must < 2037");
        }
        if (i < MIN_YEAR) {
            throw new IllegalArgumentException("year end must > 1902");
        }
        this.mCallBack = onDateSetListener;
        this.mCalendar.set(1, i);
        this.mCalendar.set(2, i2);
        this.mCalendar.set(5, i3);
        this.mVibrate = z;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        tryVibrate();
        if (view.getId() == R.id.date_picker_year) {
            setCurrentView(1);
        } else if (view.getId() == R.id.date_picker_month_and_day) {
            setCurrentView(0);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        activity.getWindow().setSoftInputMode(3);
        this.mVibrator = (Vibrator) activity.getSystemService("vibrator");
        if (bundle != null) {
            this.mCalendar.set(1, bundle.getInt("year"));
            this.mCalendar.set(2, bundle.getInt("month"));
            this.mCalendar.set(5, bundle.getInt(KEY_SELECTED_DAY));
            this.mVibrate = bundle.getBoolean(KEY_VIBRATE);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        int i2;
        int i3;
        getDialog().getWindow().requestFeature(1);
        View viewInflate = layoutInflater.inflate(R.layout.date_picker_dialog, (ViewGroup) null);
        this.mDayOfWeekView = (TextView) viewInflate.findViewById(R.id.date_picker_header);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.date_picker_month_and_day);
        this.mMonthAndDayView = linearLayout;
        linearLayout.setOnClickListener(this);
        this.mSelectedMonthTextView = (TextView) viewInflate.findViewById(R.id.date_picker_month);
        this.mSelectedDayTextView = (TextView) viewInflate.findViewById(R.id.date_picker_day);
        TextView textView = (TextView) viewInflate.findViewById(R.id.date_picker_year);
        this.mYearView = textView;
        textView.setOnClickListener(this);
        if (bundle != null) {
            this.mWeekStart = bundle.getInt("week_start");
            this.mMinYear = bundle.getInt(KEY_YEAR_START);
            this.mMaxYear = bundle.getInt(KEY_YEAR_END);
            i2 = bundle.getInt(KEY_CURRENT_VIEW);
            i3 = bundle.getInt(KEY_LIST_POSITION);
            i = bundle.getInt(KEY_LIST_POSITION_OFFSET);
        } else {
            i = 0;
            i2 = 0;
            i3 = -1;
        }
        FragmentActivity activity = getActivity();
        this.mDayPickerView = new DayPickerView(activity, this);
        this.mYearPickerView = new YearPickerView(activity, this);
        Resources resources = getResources();
        this.mDayPickerDescription = resources.getString(R.string.day_picker_description);
        this.mSelectDay = resources.getString(R.string.select_day);
        this.mYearPickerDescription = resources.getString(R.string.year_picker_description);
        this.mSelectYear = resources.getString(R.string.select_year);
        AccessibleDateAnimator accessibleDateAnimator = (AccessibleDateAnimator) viewInflate.findViewById(R.id.animator);
        this.mAnimator = accessibleDateAnimator;
        accessibleDateAnimator.addView(this.mDayPickerView);
        this.mAnimator.addView(this.mYearPickerView);
        this.mAnimator.setDateMillis(this.mCalendar.getTimeInMillis());
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300L);
        this.mAnimator.setInAnimation(alphaAnimation);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation2.setDuration(300L);
        this.mAnimator.setOutAnimation(alphaAnimation2);
        Button button = (Button) viewInflate.findViewById(R.id.done);
        this.mDoneButton = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.fourmob.datetimepicker.date.DatePickerDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DatePickerDialog.this.onDoneButtonClick();
            }
        });
        updateDisplay(false);
        setCurrentView(i2, true);
        if (i3 != -1) {
            if (i2 == 0) {
                this.mDayPickerView.postSetSelection(i3);
            }
            if (i2 == 1) {
                this.mYearPickerView.postSetSelectionFromTop(i3, i);
            }
        }
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDoneButtonClick() {
        tryVibrate();
        OnDateSetListener onDateSetListener = this.mCallBack;
        if (onDateSetListener != null) {
            onDateSetListener.onDateSet(this, this.mCalendar.get(1), this.mCalendar.get(2), this.mCalendar.get(5));
        }
        dismiss();
    }

    @Override // com.fourmob.datetimepicker.date.DatePickerController
    public void onDayOfMonthSelected(int i, int i2, int i3) {
        this.mCalendar.set(1, i);
        this.mCalendar.set(2, i2);
        this.mCalendar.set(5, i3);
        updatePickers();
        updateDisplay(true);
        if (this.mCloseOnSingleTapDay) {
            onDoneButtonClick();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("year", this.mCalendar.get(1));
        bundle.putInt("month", this.mCalendar.get(2));
        bundle.putInt(KEY_SELECTED_DAY, this.mCalendar.get(5));
        bundle.putInt("week_start", this.mWeekStart);
        bundle.putInt(KEY_YEAR_START, this.mMinYear);
        bundle.putInt(KEY_YEAR_END, this.mMaxYear);
        bundle.putInt(KEY_CURRENT_VIEW, this.mCurrentView);
        int mostVisiblePosition = this.mCurrentView == 0 ? this.mDayPickerView.getMostVisiblePosition() : -1;
        if (this.mCurrentView == 1) {
            mostVisiblePosition = this.mYearPickerView.getFirstVisiblePosition();
            bundle.putInt(KEY_LIST_POSITION_OFFSET, this.mYearPickerView.getFirstPositionOffset());
        }
        bundle.putInt(KEY_LIST_POSITION, mostVisiblePosition);
        bundle.putBoolean(KEY_VIBRATE, this.mVibrate);
    }

    @Override // com.fourmob.datetimepicker.date.DatePickerController
    public void onYearSelected(int i) {
        adjustDayInMonthIfNeeded(this.mCalendar.get(2), i);
        this.mCalendar.set(1, i);
        updatePickers();
        setCurrentView(0);
        updateDisplay(true);
    }

    @Override // com.fourmob.datetimepicker.date.DatePickerController
    public void registerOnDateChangedListener(OnDateChangedListener onDateChangedListener) {
        this.mListeners.add(onDateChangedListener);
    }

    public void setFirstDayOfWeek(int i) {
        if (i < 1 || i > 7) {
            throw new IllegalArgumentException("Value must be between Calendar.SUNDAY and Calendar.SATURDAY");
        }
        this.mWeekStart = i;
        DayPickerView dayPickerView = this.mDayPickerView;
        if (dayPickerView != null) {
            dayPickerView.onChange();
        }
    }

    public void setOnDateSetListener(OnDateSetListener onDateSetListener) {
        this.mCallBack = onDateSetListener;
    }

    public void setYearRange(int i, int i2) {
        if (i2 <= i) {
            throw new IllegalArgumentException("Year end must be larger than year start");
        }
        if (i2 > MAX_YEAR) {
            throw new IllegalArgumentException("max year end must < 2037");
        }
        if (i < MIN_YEAR) {
            throw new IllegalArgumentException("min year end must > 1902");
        }
        this.mMinYear = i;
        this.mMaxYear = i2;
        DayPickerView dayPickerView = this.mDayPickerView;
        if (dayPickerView != null) {
            dayPickerView.onChange();
        }
    }

    @Override // com.fourmob.datetimepicker.date.DatePickerController
    public void tryVibrate() {
        if (this.mVibrator == null || !this.mVibrate) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (jUptimeMillis - this.mLastVibrate >= 125) {
            this.mVibrator.vibrate(5L);
            this.mLastVibrate = jUptimeMillis;
        }
    }

    public void setCloseOnSingleTapDay(boolean z) {
        this.mCloseOnSingleTapDay = z;
    }
}

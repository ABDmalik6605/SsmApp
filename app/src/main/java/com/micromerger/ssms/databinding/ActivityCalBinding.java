package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class ActivityCalBinding implements ViewBinding {
    public final CalendarView calendar;
    private final CalendarView rootView;

    private ActivityCalBinding(CalendarView rootView, CalendarView calendar) {
        this.rootView = rootView;
        this.calendar = calendar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CalendarView getRoot() {
        return this.rootView;
    }

    public static ActivityCalBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityCalBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_cal, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityCalBinding bind(View rootView) {
        Objects.requireNonNull(rootView, "rootView");
        CalendarView calendarView = (CalendarView) rootView;
        return new ActivityCalBinding(calendarView, calendarView);
    }
}

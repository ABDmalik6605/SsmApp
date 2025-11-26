package com.micromerger.ssms.printreport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import com.micromerger.ssms.R;
import com.micromerger.ssms.utils.Constant;

/* loaded from: classes2.dex */
public class CalendarActivity extends Activity implements CalendarView.OnDateChangeListener {
    CalendarView calendar = null;
    String message = "";

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar);
        this.calendar = calendarView;
        calendarView.setOnDateChangeListener(this);
    }

    @Override // android.widget.CalendarView.OnDateChangeListener
    public void onSelectedDayChange(CalendarView view, int year, int monthOfYear, int dayOfMonth) {
        this.message = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
        onBackPressed();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(Constant.MESSAGE, this.message);
        if (getIntent().hasExtra(Constant.FROM)) {
            setResult(111, intent);
        } else if (getIntent().hasExtra(Constant.TO)) {
            setResult(Constant.DATE_TO, intent);
        }
        finish();
    }
}

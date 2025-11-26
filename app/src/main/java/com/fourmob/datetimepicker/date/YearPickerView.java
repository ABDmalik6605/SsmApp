package com.fourmob.datetimepicker.date;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.fourmob.datetimepicker.R;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class YearPickerView extends ListView implements AdapterView.OnItemClickListener, DatePickerDialog.OnDateChangedListener {
    private YearAdapter mAdapter;
    private int mChildSize;
    private final DatePickerController mController;
    private TextViewWithCircularIndicator mSelectedView;
    private int mViewSize;

    public YearPickerView(Context context, DatePickerController datePickerController) {
        super(context);
        this.mController = datePickerController;
        datePickerController.registerOnDateChangedListener(this);
        setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        Resources resources = context.getResources();
        this.mViewSize = resources.getDimensionPixelOffset(R.dimen.date_picker_view_animator_height);
        this.mChildSize = resources.getDimensionPixelOffset(R.dimen.year_label_height);
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength(this.mChildSize / 3);
        init(context);
        setOnItemClickListener(this);
        setSelector(new StateListDrawable());
        setDividerHeight(0);
        onDateChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getYearFromTextView(TextView textView) {
        return Integer.valueOf(textView.getText().toString()).intValue();
    }

    private void init(Context context) {
        ArrayList arrayList = new ArrayList();
        for (int minYear = this.mController.getMinYear(); minYear <= this.mController.getMaxYear(); minYear++) {
            arrayList.add(String.format("%d", Integer.valueOf(minYear)));
        }
        YearAdapter yearAdapter = new YearAdapter(context, R.layout.year_label_text_view, arrayList);
        this.mAdapter = yearAdapter;
        setAdapter((ListAdapter) yearAdapter);
    }

    public int getFirstPositionOffset() {
        View childAt = getChildAt(0);
        if (childAt == null) {
            return 0;
        }
        return childAt.getTop();
    }

    @Override // com.fourmob.datetimepicker.date.DatePickerDialog.OnDateChangedListener
    public void onDateChanged() {
        this.mAdapter.notifyDataSetChanged();
        postSetSelectionCentered(this.mController.getSelectedDay().year - this.mController.getMinYear());
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.mController.tryVibrate();
        TextViewWithCircularIndicator textViewWithCircularIndicator = (TextViewWithCircularIndicator) view;
        if (textViewWithCircularIndicator != null) {
            TextViewWithCircularIndicator textViewWithCircularIndicator2 = this.mSelectedView;
            if (textViewWithCircularIndicator != textViewWithCircularIndicator2) {
                if (textViewWithCircularIndicator2 != null) {
                    textViewWithCircularIndicator2.drawIndicator(false);
                    this.mSelectedView.requestLayout();
                }
                textViewWithCircularIndicator.drawIndicator(true);
                textViewWithCircularIndicator.requestLayout();
                this.mSelectedView = textViewWithCircularIndicator;
            }
            this.mController.onYearSelected(getYearFromTextView(textViewWithCircularIndicator));
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void postSetSelectionCentered(int i) {
        postSetSelectionFromTop(i, (this.mViewSize / 2) - (this.mChildSize / 2));
    }

    public void postSetSelectionFromTop(final int i, final int i2) {
        post(new Runnable() { // from class: com.fourmob.datetimepicker.date.YearPickerView.1
            @Override // java.lang.Runnable
            public void run() {
                YearPickerView.this.setSelectionFromTop(i, i2);
                YearPickerView.this.requestLayout();
            }
        });
    }

    private class YearAdapter extends ArrayAdapter<String> {
        public YearAdapter(Context context, int i, List<String> list) {
            super(context, i, list);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextViewWithCircularIndicator textViewWithCircularIndicator = (TextViewWithCircularIndicator) super.getView(i, view, viewGroup);
            textViewWithCircularIndicator.requestLayout();
            boolean z = YearPickerView.this.mController.getSelectedDay().year == YearPickerView.getYearFromTextView(textViewWithCircularIndicator);
            textViewWithCircularIndicator.drawIndicator(z);
            if (z) {
                YearPickerView.this.mSelectedView = textViewWithCircularIndicator;
            }
            return textViewWithCircularIndicator;
        }
    }
}

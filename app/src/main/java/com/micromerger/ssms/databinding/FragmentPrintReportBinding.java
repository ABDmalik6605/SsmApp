package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentPrintReportBinding implements ViewBinding {
    public final Button btnPrint;
    public final TextView dateFrom;
    public final TextView dateTo;
    private final LinearLayout rootView;
    public final TextView tvCal;
    public final TextView tvFrom;

    private FragmentPrintReportBinding(LinearLayout rootView, Button btnPrint, TextView dateFrom, TextView dateTo, TextView tvCal, TextView tvFrom) {
        this.rootView = rootView;
        this.btnPrint = btnPrint;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.tvCal = tvCal;
        this.tvFrom = tvFrom;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPrintReportBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentPrintReportBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_print_report, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPrintReportBinding bind(View rootView) {
        int i = R.id.btn_print;
        Button button = (Button) rootView.findViewById(R.id.btn_print);
        if (button != null) {
            i = R.id.date_from;
            TextView textView = (TextView) rootView.findViewById(R.id.date_from);
            if (textView != null) {
                i = R.id.date_to;
                TextView textView2 = (TextView) rootView.findViewById(R.id.date_to);
                if (textView2 != null) {
                    i = R.id.tv_cal;
                    TextView textView3 = (TextView) rootView.findViewById(R.id.tv_cal);
                    if (textView3 != null) {
                        i = R.id.tv_from;
                        TextView textView4 = (TextView) rootView.findViewById(R.id.tv_from);
                        if (textView4 != null) {
                            return new FragmentPrintReportBinding((LinearLayout) rootView, button, textView, textView2, textView3, textView4);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

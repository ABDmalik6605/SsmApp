package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class LayoutResetBinding implements ViewBinding {
    public final Button btnSubmit;
    public final EditText etCode;
    private final LinearLayout rootView;
    public final TextView tvCal;
    public final TextView tvCensusWidget;

    private LayoutResetBinding(LinearLayout rootView, Button btnSubmit, EditText etCode, TextView tvCal, TextView tvCensusWidget) {
        this.rootView = rootView;
        this.btnSubmit = btnSubmit;
        this.etCode = etCode;
        this.tvCal = tvCal;
        this.tvCensusWidget = tvCensusWidget;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutResetBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutResetBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_reset, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutResetBinding bind(View rootView) {
        int i = R.id.btn_submit;
        Button button = (Button) rootView.findViewById(R.id.btn_submit);
        if (button != null) {
            i = R.id.et_code;
            EditText editText = (EditText) rootView.findViewById(R.id.et_code);
            if (editText != null) {
                i = R.id.tv_cal;
                TextView textView = (TextView) rootView.findViewById(R.id.tv_cal);
                if (textView != null) {
                    i = R.id.tv_census_widget;
                    TextView textView2 = (TextView) rootView.findViewById(R.id.tv_census_widget);
                    if (textView2 != null) {
                        return new LayoutResetBinding((LinearLayout) rootView, button, editText, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

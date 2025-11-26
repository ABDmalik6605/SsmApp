package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragEnrollmentFilterBinding implements ViewBinding {
    public final Button btnBack;
    public final Button btnNext;
    public final RelativeLayout content;
    public final RelativeLayout footer;
    public final RelativeLayout header;
    private final RelativeLayout rootView;
    public final Spinner spEnrollmentSource;
    public final TextView tvOwnership;

    private FragEnrollmentFilterBinding(RelativeLayout rootView, Button btnBack, Button btnNext, RelativeLayout content, RelativeLayout footer, RelativeLayout header, Spinner spEnrollmentSource, TextView tvOwnership) {
        this.rootView = rootView;
        this.btnBack = btnBack;
        this.btnNext = btnNext;
        this.content = content;
        this.footer = footer;
        this.header = header;
        this.spEnrollmentSource = spEnrollmentSource;
        this.tvOwnership = tvOwnership;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragEnrollmentFilterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragEnrollmentFilterBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_enrollment_filter, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragEnrollmentFilterBinding bind(View rootView) {
        int i = R.id.btn_back;
        Button button = (Button) rootView.findViewById(R.id.btn_back);
        if (button != null) {
            i = R.id.btn_next;
            Button button2 = (Button) rootView.findViewById(R.id.btn_next);
            if (button2 != null) {
                i = R.id.content;
                RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.content);
                if (relativeLayout != null) {
                    i = R.id.footer;
                    RelativeLayout relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.footer);
                    if (relativeLayout2 != null) {
                        i = R.id.header;
                        RelativeLayout relativeLayout3 = (RelativeLayout) rootView.findViewById(R.id.header);
                        if (relativeLayout3 != null) {
                            i = R.id.sp_enrollment_source;
                            Spinner spinner = (Spinner) rootView.findViewById(R.id.sp_enrollment_source);
                            if (spinner != null) {
                                i = R.id.tv_ownership;
                                TextView textView = (TextView) rootView.findViewById(R.id.tv_ownership);
                                if (textView != null) {
                                    return new FragEnrollmentFilterBinding((RelativeLayout) rootView, button, button2, relativeLayout, relativeLayout2, relativeLayout3, spinner, textView);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

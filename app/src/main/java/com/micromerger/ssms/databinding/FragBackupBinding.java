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
public final class FragBackupBinding implements ViewBinding {
    public final Button btnBackUp;
    public final Button btnManualSync;
    private final LinearLayout rootView;
    public final TextView tvCal;
    public final TextView tvCensusWidget;

    private FragBackupBinding(LinearLayout rootView, Button btnBackUp, Button btnManualSync, TextView tvCal, TextView tvCensusWidget) {
        this.rootView = rootView;
        this.btnBackUp = btnBackUp;
        this.btnManualSync = btnManualSync;
        this.tvCal = tvCal;
        this.tvCensusWidget = tvCensusWidget;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragBackupBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragBackupBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_backup, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragBackupBinding bind(View rootView) {
        int i = R.id.btn_back_up;
        Button button = (Button) rootView.findViewById(R.id.btn_back_up);
        if (button != null) {
            Button button2 = (Button) rootView.findViewById(R.id.btn_Manual_sync);
            i = R.id.tv_cal;
            TextView textView = (TextView) rootView.findViewById(R.id.tv_cal);
            if (textView != null) {
                i = R.id.tv_census_widget;
                TextView textView2 = (TextView) rootView.findViewById(R.id.tv_census_widget);
                if (textView2 != null) {
                    return new FragBackupBinding((LinearLayout) rootView, button, button2, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

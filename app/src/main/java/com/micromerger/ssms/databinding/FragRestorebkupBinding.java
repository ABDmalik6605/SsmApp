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
public final class FragRestorebkupBinding implements ViewBinding {
    public final Button btnRestoreBackup;
    private final LinearLayout rootView;
    public final TextView tvCensusWidget;
    public final TextView tvLast;
    public final TextView tvLastbackup;
    public final TextView tvSize;
    public final TextView tvSizevalue;

    private FragRestorebkupBinding(LinearLayout rootView, Button btnRestoreBackup, TextView tvCensusWidget, TextView tvLast, TextView tvLastbackup, TextView tvSize, TextView tvSizevalue) {
        this.rootView = rootView;
        this.btnRestoreBackup = btnRestoreBackup;
        this.tvCensusWidget = tvCensusWidget;
        this.tvLast = tvLast;
        this.tvLastbackup = tvLastbackup;
        this.tvSize = tvSize;
        this.tvSizevalue = tvSizevalue;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragRestorebkupBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragRestorebkupBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_restorebkup, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragRestorebkupBinding bind(View rootView) {
        int i = R.id.btn_restore_backup;
        Button button = (Button) rootView.findViewById(R.id.btn_restore_backup);
        if (button != null) {
            i = R.id.tv_census_widget;
            TextView textView = (TextView) rootView.findViewById(R.id.tv_census_widget);
            if (textView != null) {
                i = R.id.tv_last;
                TextView textView2 = (TextView) rootView.findViewById(R.id.tv_last);
                if (textView2 != null) {
                    i = R.id.tv_lastbackup;
                    TextView textView3 = (TextView) rootView.findViewById(R.id.tv_lastbackup);
                    if (textView3 != null) {
                        i = R.id.tv_size;
                        TextView textView4 = (TextView) rootView.findViewById(R.id.tv_size);
                        if (textView4 != null) {
                            i = R.id.tv_sizevalue;
                            TextView textView5 = (TextView) rootView.findViewById(R.id.tv_sizevalue);
                            if (textView5 != null) {
                                return new FragRestorebkupBinding((LinearLayout) rootView, button, textView, textView2, textView3, textView4, textView5);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

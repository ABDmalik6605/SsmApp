package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class ItemStartMonitoringBinding implements ViewBinding {
    public final CardView cardView;
    public final CheckBox cbSchoolIsChecked;
    private final LinearLayout rootView;
    public final TextView tvLastMonitored;
    public final TextView tvSchoolId;
    public final TextView tvSchoolTitle;

    private ItemStartMonitoringBinding(LinearLayout rootView, CardView cardView, CheckBox cbSchoolIsChecked, TextView tvLastMonitored, TextView tvSchoolId, TextView tvSchoolTitle) {
        this.rootView = rootView;
        this.cardView = cardView;
        this.cbSchoolIsChecked = cbSchoolIsChecked;
        this.tvLastMonitored = tvLastMonitored;
        this.tvSchoolId = tvSchoolId;
        this.tvSchoolTitle = tvSchoolTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemStartMonitoringBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemStartMonitoringBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_start_monitoring, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemStartMonitoringBinding bind(View rootView) {
        int i = R.id.card_view;
        CardView cardView = (CardView) rootView.findViewById(R.id.card_view);
        if (cardView != null) {
            i = R.id.cb_school_is_checked;
            CheckBox checkBox = (CheckBox) rootView.findViewById(R.id.cb_school_is_checked);
            if (checkBox != null) {
                i = R.id.tv_last_monitored;
                TextView textView = (TextView) rootView.findViewById(R.id.tv_last_monitored);
                if (textView != null) {
                    i = R.id.tv_school_id;
                    TextView textView2 = (TextView) rootView.findViewById(R.id.tv_school_id);
                    if (textView2 != null) {
                        i = R.id.tv_school_title;
                        TextView textView3 = (TextView) rootView.findViewById(R.id.tv_school_title);
                        if (textView3 != null) {
                            return new ItemStartMonitoringBinding((LinearLayout) rootView, cardView, checkBox, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

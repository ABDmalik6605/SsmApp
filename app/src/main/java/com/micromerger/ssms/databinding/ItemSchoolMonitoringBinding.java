package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class ItemSchoolMonitoringBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final ImageView smArrow;
    public final ImageView smIcon;
    public final CardView smLayout;
    public final TextView smTitle;

    private ItemSchoolMonitoringBinding(LinearLayout rootView, ImageView smArrow, ImageView smIcon, CardView smLayout, TextView smTitle) {
        this.rootView = rootView;
        this.smArrow = smArrow;
        this.smIcon = smIcon;
        this.smLayout = smLayout;
        this.smTitle = smTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemSchoolMonitoringBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemSchoolMonitoringBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_school_monitoring, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemSchoolMonitoringBinding bind(View rootView) {
        int i = R.id.smArrow;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.smArrow);
        if (imageView != null) {
            i = R.id.smIcon;
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.smIcon);
            if (imageView2 != null) {
                i = R.id.smLayout;
                CardView cardView = (CardView) rootView.findViewById(R.id.smLayout);
                if (cardView != null) {
                    i = R.id.smTitle;
                    TextView textView = (TextView) rootView.findViewById(R.id.smTitle);
                    if (textView != null) {
                        return new ItemSchoolMonitoringBinding((LinearLayout) rootView, imageView, imageView2, cardView, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

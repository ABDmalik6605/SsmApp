package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class CustomSpinnerItemsBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView textView;

    private CustomSpinnerItemsBinding(LinearLayout rootView, TextView textView) {
        this.rootView = rootView;
        this.textView = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static CustomSpinnerItemsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static CustomSpinnerItemsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.custom_spinner_items, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CustomSpinnerItemsBinding bind(View rootView) {
        TextView textView = (TextView) rootView.findViewById(R.id.textView);
        if (textView != null) {
            return new CustomSpinnerItemsBinding((LinearLayout) rootView, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.textView)));
    }
}

package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class SpinnerDropdownBinding implements ViewBinding {
    private final TextView rootView;

    private SpinnerDropdownBinding(TextView rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public TextView getRoot() {
        return this.rootView;
    }

    public static SpinnerDropdownBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SpinnerDropdownBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.spinner_dropdown, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SpinnerDropdownBinding bind(View rootView) {
        Objects.requireNonNull(rootView, "rootView");
        return new SpinnerDropdownBinding((TextView) rootView);
    }
}

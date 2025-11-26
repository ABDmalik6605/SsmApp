package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class SchoolListDropdownBinding implements ViewBinding {
    private final TextView rootView;
    public final TextView textView;

    private SchoolListDropdownBinding(TextView rootView, TextView textView) {
        this.rootView = rootView;
        this.textView = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public TextView getRoot() {
        return this.rootView;
    }

    public static SchoolListDropdownBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SchoolListDropdownBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.school_list_dropdown, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SchoolListDropdownBinding bind(View rootView) {
        Objects.requireNonNull(rootView, "rootView");
        TextView textView = (TextView) rootView;
        return new SchoolListDropdownBinding(textView, textView);
    }
}

package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class CustomItemLoadingBinding implements ViewBinding {
    private final ProgressBar rootView;

    private CustomItemLoadingBinding(ProgressBar rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ProgressBar getRoot() {
        return this.rootView;
    }

    public static CustomItemLoadingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static CustomItemLoadingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.custom_item_loading, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CustomItemLoadingBinding bind(View rootView) {
        Objects.requireNonNull(rootView, "rootView");
        return new CustomItemLoadingBinding((ProgressBar) rootView);
    }
}

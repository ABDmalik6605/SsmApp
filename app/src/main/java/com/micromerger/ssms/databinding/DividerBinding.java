package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class DividerBinding implements ViewBinding {
    private final View rootView;

    private DividerBinding(View rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static DividerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DividerBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.divider, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DividerBinding bind(View rootView) {
        Objects.requireNonNull(rootView, "rootView");
        return new DividerBinding(rootView);
    }
}

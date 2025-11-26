package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class ActivityEmptyBinding implements ViewBinding {
    public final ProgressBar emptyProgressBar;
    private final LinearLayout rootView;

    private ActivityEmptyBinding(LinearLayout rootView, ProgressBar emptyProgressBar) {
        this.rootView = rootView;
        this.emptyProgressBar = emptyProgressBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityEmptyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityEmptyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_empty, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityEmptyBinding bind(View rootView) {
        ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.empty_progress_bar);
        if (progressBar != null) {
            return new ActivityEmptyBinding((LinearLayout) rootView, progressBar);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.empty_progress_bar)));
    }
}

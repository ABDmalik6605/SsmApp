package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class ProgressdialogBinding implements ViewBinding {
    public final ProgressBar progressBar1;
    private final FrameLayout rootView;

    private ProgressdialogBinding(FrameLayout rootView, ProgressBar progressBar1) {
        this.rootView = rootView;
        this.progressBar1 = progressBar1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ProgressdialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ProgressdialogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.progressdialog, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ProgressdialogBinding bind(View rootView) {
        ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar1);
        if (progressBar != null) {
            return new ProgressdialogBinding((FrameLayout) rootView, progressBar);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.progressBar1)));
    }
}

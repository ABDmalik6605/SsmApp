package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class AppBarMainBinding implements ViewBinding {
    private final CoordinatorLayout rootView;
    public final MainToolbarBinding toolbar;

    private AppBarMainBinding(CoordinatorLayout rootView, MainToolbarBinding toolbar) {
        this.rootView = rootView;
        this.toolbar = toolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static AppBarMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static AppBarMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.app_bar_main, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static AppBarMainBinding bind(View rootView) {
        View viewFindViewById = rootView.findViewById(R.id.toolbar);
        if (viewFindViewById != null) {
            return new AppBarMainBinding((CoordinatorLayout) rootView, MainToolbarBinding.bind(viewFindViewById));
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.toolbar)));
    }
}

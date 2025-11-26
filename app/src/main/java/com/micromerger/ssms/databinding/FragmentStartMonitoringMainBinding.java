package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;
import com.micromerger.ssms.utils.widgets.ClearableEditText;

/* loaded from: classes2.dex */
public final class FragmentStartMonitoringMainBinding implements ViewBinding {
    public final ClearableEditText etSearchBar;
    public final LinearLayout llSearchBar;
    public final RecyclerView recyclerview;
    public final RelativeLayout rl;
    private final RelativeLayout rootView;
    public final SwipeRefreshLayout swipeRefreshLayout;

    private FragmentStartMonitoringMainBinding(RelativeLayout rootView, ClearableEditText etSearchBar, LinearLayout llSearchBar, RecyclerView recyclerview, RelativeLayout rl, SwipeRefreshLayout swipeRefreshLayout) {
        this.rootView = rootView;
        this.etSearchBar = etSearchBar;
        this.llSearchBar = llSearchBar;
        this.recyclerview = recyclerview;
        this.rl = rl;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentStartMonitoringMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentStartMonitoringMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_start_monitoring_main, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentStartMonitoringMainBinding bind(View rootView) {
        int i = R.id.et_search_bar;
        ClearableEditText clearableEditText = (ClearableEditText) rootView.findViewById(R.id.et_search_bar);
        if (clearableEditText != null) {
            i = R.id.ll_search_bar;
            LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll_search_bar);
            if (linearLayout != null) {
                i = R.id.recyclerview;
                RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
                if (recyclerView != null) {
                    RelativeLayout relativeLayout = (RelativeLayout) rootView;
                    i = R.id.swipe_refresh_layout;
                    SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
                    if (swipeRefreshLayout != null) {
                        return new FragmentStartMonitoringMainBinding(relativeLayout, clearableEditText, linearLayout, recyclerView, relativeLayout, swipeRefreshLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;
import com.micromerger.ssms.utils.widgets.ClearableEditText;

/* loaded from: classes2.dex */
public final class FragmentDashboardBinding implements ViewBinding {
    public final ClearableEditText etSearchBar;
    public final LinearLayout llSearchBar;
    private final RelativeLayout rootView;
    public final RecyclerView rvDashboard;
    public final TextView tvNoInternet;

    private FragmentDashboardBinding(RelativeLayout rootView, ClearableEditText etSearchBar, LinearLayout llSearchBar, RecyclerView rvDashboard, TextView tvNoInternet) {
        this.rootView = rootView;
        this.etSearchBar = etSearchBar;
        this.llSearchBar = llSearchBar;
        this.rvDashboard = rvDashboard;
        this.tvNoInternet = tvNoInternet;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentDashboardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentDashboardBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_dashboard, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentDashboardBinding bind(View rootView) {
        int i = R.id.et_search_bar;
        ClearableEditText clearableEditText = (ClearableEditText) rootView.findViewById(R.id.et_search_bar);
        if (clearableEditText != null) {
            i = R.id.ll_search_bar;
            LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll_search_bar);
            if (linearLayout != null) {
                i = R.id.rv_dashboard;
                RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_dashboard);
                if (recyclerView != null) {
                    i = R.id.tv_no_internet;
                    TextView textView = (TextView) rootView.findViewById(R.id.tv_no_internet);
                    if (textView != null) {
                        return new FragmentDashboardBinding((RelativeLayout) rootView, clearableEditText, linearLayout, recyclerView, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

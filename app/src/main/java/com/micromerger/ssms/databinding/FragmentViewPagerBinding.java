package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import com.micromerger.ssms.R;
import com.viewpagerindicator.CirclePageIndicator;

/* loaded from: classes2.dex */
public final class FragmentViewPagerBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final CirclePageIndicator titlePageIndicator;
    public final AutoScrollViewPager viewpager;

    private FragmentViewPagerBinding(RelativeLayout rootView, CirclePageIndicator titlePageIndicator, AutoScrollViewPager viewpager) {
        this.rootView = rootView;
        this.titlePageIndicator = titlePageIndicator;
        this.viewpager = viewpager;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentViewPagerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentViewPagerBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_view_pager, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentViewPagerBinding bind(View rootView) {
        int i = R.id.titlePageIndicator;
        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) rootView.findViewById(R.id.titlePageIndicator);
        if (circlePageIndicator != null) {
            i = R.id.viewpager;
            AutoScrollViewPager autoScrollViewPager = (AutoScrollViewPager) rootView.findViewById(R.id.viewpager);
            if (autoScrollViewPager != null) {
                return new FragmentViewPagerBinding((RelativeLayout) rootView, circlePageIndicator, autoScrollViewPager);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

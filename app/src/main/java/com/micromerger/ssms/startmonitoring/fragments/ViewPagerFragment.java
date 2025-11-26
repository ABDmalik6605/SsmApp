package com.micromerger.ssms.startmonitoring.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.micromerger.ssms.R;
import com.micromerger.ssms.startmonitoring.adapters.MyPageAdapter;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.List;

/* loaded from: classes2.dex */
public class ViewPagerFragment extends Fragment implements ViewPager.OnPageChangeListener {
    public static String IMAGE_INDEX = "page_index";
    private static int NO_OF_IMAGES = 6;
    public static int currentIndex;
    int currentPagePosition;
    CirclePageIndicator indicator;
    MyPageAdapter pageAdapter;
    View rootview;
    boolean isEnglish = true;
    List<Fragment> fragments = null;

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int state) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_view_pager, container, false);
        this.rootview = viewInflate;
        this.indicator = (CirclePageIndicator) viewInflate.findViewById(R.id.titlePageIndicator);
        return this.rootview;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int position) {
        currentIndex = position;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.pageAdapter = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }
}

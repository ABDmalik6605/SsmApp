package com.micromerger.ssms.startmonitoring.adapters;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.micromerger.ssms.startmonitoring.fragments.SlidingImageFragment;

/* loaded from: classes2.dex */
public class MyPageAdapter extends FragmentPagerAdapter {
    private int[] idList;
    private SlidingImageFragment mSlidingImageFragment;

    public MyPageAdapter(FragmentManager fm, int[] idList) {
        super(fm);
        this.idList = idList;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("KEY_INDEX", position);
        SlidingImageFragment slidingImageFragmentNewInstance = SlidingImageFragment.newInstance(bundle);
        this.mSlidingImageFragment = slidingImageFragmentNewInstance;
        return slidingImageFragmentNewInstance;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.idList.length;
    }
}

package com.micromerger.ssms.startmonitoring.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public class EducationQuality extends Fragment {
    View mView;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_census_education_quality, (ViewGroup) null);
        this.mView = viewInflate;
        return viewInflate;
    }
}

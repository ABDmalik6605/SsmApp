package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragEnrollmentSchoolTypeBinding implements ViewBinding {
    public final CardView cvElementary;
    public final CardView cvHigher;
    public final CardView cvSecondary;
    private final LinearLayout rootView;

    private FragEnrollmentSchoolTypeBinding(LinearLayout rootView, CardView cvElementary, CardView cvHigher, CardView cvSecondary) {
        this.rootView = rootView;
        this.cvElementary = cvElementary;
        this.cvHigher = cvHigher;
        this.cvSecondary = cvSecondary;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragEnrollmentSchoolTypeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragEnrollmentSchoolTypeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_enrollment_school_type, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragEnrollmentSchoolTypeBinding bind(View rootView) {
        int i = R.id.cv_elementary;
        CardView cardView = (CardView) rootView.findViewById(R.id.cv_elementary);
        if (cardView != null) {
            i = R.id.cv_higher;
            CardView cardView2 = (CardView) rootView.findViewById(R.id.cv_higher);
            if (cardView2 != null) {
                i = R.id.cv_secondary;
                CardView cardView3 = (CardView) rootView.findViewById(R.id.cv_secondary);
                if (cardView3 != null) {
                    return new FragEnrollmentSchoolTypeBinding((LinearLayout) rootView, cardView, cardView2, cardView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

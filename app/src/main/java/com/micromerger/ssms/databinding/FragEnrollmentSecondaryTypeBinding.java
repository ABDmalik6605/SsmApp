package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragEnrollmentSecondaryTypeBinding implements ViewBinding {
    public final Button btnBack;
    public final RelativeLayout content;
    public final CardView cvArts;
    public final CardView cvBio;
    public final CardView cvCommerce;
    public final CardView cvComp;
    public final CardView cvOther;
    public final RelativeLayout footer;
    public final RelativeLayout header;
    public final LinearLayout llClass;
    private final RelativeLayout rootView;

    private FragEnrollmentSecondaryTypeBinding(RelativeLayout rootView, Button btnBack, RelativeLayout content, CardView cvArts, CardView cvBio, CardView cvCommerce, CardView cvComp, CardView cvOther, RelativeLayout footer, RelativeLayout header, LinearLayout llClass) {
        this.rootView = rootView;
        this.btnBack = btnBack;
        this.content = content;
        this.cvArts = cvArts;
        this.cvBio = cvBio;
        this.cvCommerce = cvCommerce;
        this.cvComp = cvComp;
        this.cvOther = cvOther;
        this.footer = footer;
        this.header = header;
        this.llClass = llClass;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragEnrollmentSecondaryTypeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragEnrollmentSecondaryTypeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_enrollment_secondary_type, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragEnrollmentSecondaryTypeBinding bind(View rootView) {
        int i = R.id.btn_back;
        Button button = (Button) rootView.findViewById(R.id.btn_back);
        if (button != null) {
            i = R.id.content;
            RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.content);
            if (relativeLayout != null) {
                i = R.id.cv_arts;
                CardView cardView = (CardView) rootView.findViewById(R.id.cv_arts);
                if (cardView != null) {
                    i = R.id.cv_bio;
                    CardView cardView2 = (CardView) rootView.findViewById(R.id.cv_bio);
                    if (cardView2 != null) {
                        i = R.id.cv_commerce;
                        CardView cardView3 = (CardView) rootView.findViewById(R.id.cv_commerce);
                        if (cardView3 != null) {
                            i = R.id.cv_comp;
                            CardView cardView4 = (CardView) rootView.findViewById(R.id.cv_comp);
                            if (cardView4 != null) {
                                i = R.id.cv_other;
                                CardView cardView5 = (CardView) rootView.findViewById(R.id.cv_other);
                                if (cardView5 != null) {
                                    i = R.id.footer;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.footer);
                                    if (relativeLayout2 != null) {
                                        i = R.id.header;
                                        RelativeLayout relativeLayout3 = (RelativeLayout) rootView.findViewById(R.id.header);
                                        if (relativeLayout3 != null) {
                                            i = R.id.ll_class;
                                            LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll_class);
                                            if (linearLayout != null) {
                                                return new FragEnrollmentSecondaryTypeBinding((RelativeLayout) rootView, button, relativeLayout, cardView, cardView2, cardView3, cardView4, cardView5, relativeLayout2, relativeLayout3, linearLayout);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

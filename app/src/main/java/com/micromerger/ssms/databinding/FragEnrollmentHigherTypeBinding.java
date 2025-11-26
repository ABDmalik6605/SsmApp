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
public final class FragEnrollmentHigherTypeBinding implements ViewBinding {
    public final Button btnBack;
    public final RelativeLayout content;
    public final CardView cvArts;
    public final CardView cvCommerce;
    public final CardView cvComp;
    public final CardView cvMedical;
    public final CardView cvOther;
    public final CardView cvPreeng;
    public final RelativeLayout footer;
    public final RelativeLayout header;
    public final LinearLayout llClass;
    private final RelativeLayout rootView;

    private FragEnrollmentHigherTypeBinding(RelativeLayout rootView, Button btnBack, RelativeLayout content, CardView cvArts, CardView cvCommerce, CardView cvComp, CardView cvMedical, CardView cvOther, CardView cvPreeng, RelativeLayout footer, RelativeLayout header, LinearLayout llClass) {
        this.rootView = rootView;
        this.btnBack = btnBack;
        this.content = content;
        this.cvArts = cvArts;
        this.cvCommerce = cvCommerce;
        this.cvComp = cvComp;
        this.cvMedical = cvMedical;
        this.cvOther = cvOther;
        this.cvPreeng = cvPreeng;
        this.footer = footer;
        this.header = header;
        this.llClass = llClass;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragEnrollmentHigherTypeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragEnrollmentHigherTypeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_enrollment_higher_type, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragEnrollmentHigherTypeBinding bind(View rootView) {
        int i = R.id.btn_back;
        Button button = (Button) rootView.findViewById(R.id.btn_back);
        if (button != null) {
            i = R.id.content;
            RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.content);
            if (relativeLayout != null) {
                i = R.id.cv_arts;
                CardView cardView = (CardView) rootView.findViewById(R.id.cv_arts);
                if (cardView != null) {
                    i = R.id.cv_commerce;
                    CardView cardView2 = (CardView) rootView.findViewById(R.id.cv_commerce);
                    if (cardView2 != null) {
                        i = R.id.cv_comp;
                        CardView cardView3 = (CardView) rootView.findViewById(R.id.cv_comp);
                        if (cardView3 != null) {
                            i = R.id.cv_medical;
                            CardView cardView4 = (CardView) rootView.findViewById(R.id.cv_medical);
                            if (cardView4 != null) {
                                i = R.id.cv_other;
                                CardView cardView5 = (CardView) rootView.findViewById(R.id.cv_other);
                                if (cardView5 != null) {
                                    i = R.id.cv_preeng;
                                    CardView cardView6 = (CardView) rootView.findViewById(R.id.cv_preeng);
                                    if (cardView6 != null) {
                                        i = R.id.footer;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.footer);
                                        if (relativeLayout2 != null) {
                                            i = R.id.header;
                                            RelativeLayout relativeLayout3 = (RelativeLayout) rootView.findViewById(R.id.header);
                                            if (relativeLayout3 != null) {
                                                i = R.id.ll_class;
                                                LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll_class);
                                                if (linearLayout != null) {
                                                    return new FragEnrollmentHigherTypeBinding((RelativeLayout) rootView, button, relativeLayout, cardView, cardView2, cardView3, cardView4, cardView5, cardView6, relativeLayout2, relativeLayout3, linearLayout);
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
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentCensusTextbooksBinding implements ViewBinding {
    public final CardView cvClass1;
    public final CardView cvClass10;
    public final CardView cvClass11;
    public final CardView cvClass12;
    public final CardView cvClass2;
    public final CardView cvClass3;
    public final CardView cvClass4;
    public final CardView cvClass5;
    public final CardView cvClass6;
    public final CardView cvClass7;
    public final CardView cvClass8;
    public final CardView cvClass9;
    public final CardView cvClassKatchi;
    public final LinearLayout llClass;
    private final LinearLayout rootView;

    private FragmentCensusTextbooksBinding(LinearLayout rootView, CardView cvClass1, CardView cvClass10, CardView cvClass11, CardView cvClass12, CardView cvClass2, CardView cvClass3, CardView cvClass4, CardView cvClass5, CardView cvClass6, CardView cvClass7, CardView cvClass8, CardView cvClass9, CardView cvClassKatchi, LinearLayout llClass) {
        this.rootView = rootView;
        this.cvClass1 = cvClass1;
        this.cvClass10 = cvClass10;
        this.cvClass11 = cvClass11;
        this.cvClass12 = cvClass12;
        this.cvClass2 = cvClass2;
        this.cvClass3 = cvClass3;
        this.cvClass4 = cvClass4;
        this.cvClass5 = cvClass5;
        this.cvClass6 = cvClass6;
        this.cvClass7 = cvClass7;
        this.cvClass8 = cvClass8;
        this.cvClass9 = cvClass9;
        this.cvClassKatchi = cvClassKatchi;
        this.llClass = llClass;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCensusTextbooksBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentCensusTextbooksBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_census_textbooks, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCensusTextbooksBinding bind(View rootView) {
        int i = R.id.cv_class1;
        CardView cardView = (CardView) rootView.findViewById(R.id.cv_class1);
        if (cardView != null) {
            i = R.id.cv_class10;
            CardView cardView2 = (CardView) rootView.findViewById(R.id.cv_class10);
            if (cardView2 != null) {
                i = R.id.cv_class11;
                CardView cardView3 = (CardView) rootView.findViewById(R.id.cv_class11);
                if (cardView3 != null) {
                    i = R.id.cv_class12;
                    CardView cardView4 = (CardView) rootView.findViewById(R.id.cv_class12);
                    if (cardView4 != null) {
                        i = R.id.cv_class2;
                        CardView cardView5 = (CardView) rootView.findViewById(R.id.cv_class2);
                        if (cardView5 != null) {
                            i = R.id.cv_class3;
                            CardView cardView6 = (CardView) rootView.findViewById(R.id.cv_class3);
                            if (cardView6 != null) {
                                i = R.id.cv_class4;
                                CardView cardView7 = (CardView) rootView.findViewById(R.id.cv_class4);
                                if (cardView7 != null) {
                                    i = R.id.cv_class5;
                                    CardView cardView8 = (CardView) rootView.findViewById(R.id.cv_class5);
                                    if (cardView8 != null) {
                                        i = R.id.cv_class6;
                                        CardView cardView9 = (CardView) rootView.findViewById(R.id.cv_class6);
                                        if (cardView9 != null) {
                                            i = R.id.cv_class7;
                                            CardView cardView10 = (CardView) rootView.findViewById(R.id.cv_class7);
                                            if (cardView10 != null) {
                                                i = R.id.cv_class8;
                                                CardView cardView11 = (CardView) rootView.findViewById(R.id.cv_class8);
                                                if (cardView11 != null) {
                                                    i = R.id.cv_class9;
                                                    CardView cardView12 = (CardView) rootView.findViewById(R.id.cv_class9);
                                                    if (cardView12 != null) {
                                                        i = R.id.cv_class_katchi;
                                                        CardView cardView13 = (CardView) rootView.findViewById(R.id.cv_class_katchi);
                                                        if (cardView13 != null) {
                                                            i = R.id.ll_class;
                                                            LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll_class);
                                                            if (linearLayout != null) {
                                                                return new FragmentCensusTextbooksBinding((LinearLayout) rootView, cardView, cardView2, cardView3, cardView4, cardView5, cardView6, cardView7, cardView8, cardView9, cardView10, cardView11, cardView12, cardView13, linearLayout);
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
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

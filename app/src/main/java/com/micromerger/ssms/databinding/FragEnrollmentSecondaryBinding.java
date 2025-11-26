package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragEnrollmentSecondaryBinding implements ViewBinding {
    public final Button btnBack;
    public final RelativeLayout content;
    public final CardView cvClass1;
    public final CardView cvClass10;
    public final CardView cvClass2;
    public final CardView cvClass3;
    public final CardView cvClass4;
    public final CardView cvClass5;
    public final CardView cvClass6;
    public final CardView cvClass7;
    public final CardView cvClass8;
    public final CardView cvClass9;
    public final EditText etTotalEnrollments;
    public final EditText etTotalPresents;
    public final RelativeLayout footer;
    public final RelativeLayout header;
    public final LinearLayout llClass;
    private final RelativeLayout rootView;
    public final TextView tvTotalNoOfClassrooms;
    public final TextView tvTotalNoOfClassrooms3;

    private FragEnrollmentSecondaryBinding(RelativeLayout rootView, Button btnBack, RelativeLayout content, CardView cvClass1, CardView cvClass10, CardView cvClass2, CardView cvClass3, CardView cvClass4, CardView cvClass5, CardView cvClass6, CardView cvClass7, CardView cvClass8, CardView cvClass9, EditText etTotalEnrollments, EditText etTotalPresents, RelativeLayout footer, RelativeLayout header, LinearLayout llClass, TextView tvTotalNoOfClassrooms, TextView tvTotalNoOfClassrooms3) {
        this.rootView = rootView;
        this.btnBack = btnBack;
        this.content = content;
        this.cvClass1 = cvClass1;
        this.cvClass10 = cvClass10;
        this.cvClass2 = cvClass2;
        this.cvClass3 = cvClass3;
        this.cvClass4 = cvClass4;
        this.cvClass5 = cvClass5;
        this.cvClass6 = cvClass6;
        this.cvClass7 = cvClass7;
        this.cvClass8 = cvClass8;
        this.cvClass9 = cvClass9;
        this.etTotalEnrollments = etTotalEnrollments;
        this.etTotalPresents = etTotalPresents;
        this.footer = footer;
        this.header = header;
        this.llClass = llClass;
        this.tvTotalNoOfClassrooms = tvTotalNoOfClassrooms;
        this.tvTotalNoOfClassrooms3 = tvTotalNoOfClassrooms3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragEnrollmentSecondaryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragEnrollmentSecondaryBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_enrollment_secondary, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragEnrollmentSecondaryBinding bind(View rootView) {
        int i = R.id.btn_back;
        Button button = (Button) rootView.findViewById(R.id.btn_back);
        if (button != null) {
            i = R.id.content;
            RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.content);
            if (relativeLayout != null) {
                i = R.id.cv_class1;
                CardView cardView = (CardView) rootView.findViewById(R.id.cv_class1);
                if (cardView != null) {
                    i = R.id.cv_class10;
                    CardView cardView2 = (CardView) rootView.findViewById(R.id.cv_class10);
                    if (cardView2 != null) {
                        i = R.id.cv_class2;
                        CardView cardView3 = (CardView) rootView.findViewById(R.id.cv_class2);
                        if (cardView3 != null) {
                            i = R.id.cv_class3;
                            CardView cardView4 = (CardView) rootView.findViewById(R.id.cv_class3);
                            if (cardView4 != null) {
                                i = R.id.cv_class4;
                                CardView cardView5 = (CardView) rootView.findViewById(R.id.cv_class4);
                                if (cardView5 != null) {
                                    i = R.id.cv_class5;
                                    CardView cardView6 = (CardView) rootView.findViewById(R.id.cv_class5);
                                    if (cardView6 != null) {
                                        i = R.id.cv_class6;
                                        CardView cardView7 = (CardView) rootView.findViewById(R.id.cv_class6);
                                        if (cardView7 != null) {
                                            i = R.id.cv_class7;
                                            CardView cardView8 = (CardView) rootView.findViewById(R.id.cv_class7);
                                            if (cardView8 != null) {
                                                i = R.id.cv_class8;
                                                CardView cardView9 = (CardView) rootView.findViewById(R.id.cv_class8);
                                                if (cardView9 != null) {
                                                    i = R.id.cv_class9;
                                                    CardView cardView10 = (CardView) rootView.findViewById(R.id.cv_class9);
                                                    if (cardView10 != null) {
                                                        i = R.id.et_total_enrollments;
                                                        EditText editText = (EditText) rootView.findViewById(R.id.et_total_enrollments);
                                                        if (editText != null) {
                                                            i = R.id.et_total_presents;
                                                            EditText editText2 = (EditText) rootView.findViewById(R.id.et_total_presents);
                                                            if (editText2 != null) {
                                                                i = R.id.footer;
                                                                RelativeLayout relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.footer);
                                                                if (relativeLayout2 != null) {
                                                                    i = R.id.header;
                                                                    RelativeLayout relativeLayout3 = (RelativeLayout) rootView.findViewById(R.id.header);
                                                                    if (relativeLayout3 != null) {
                                                                        i = R.id.ll_class;
                                                                        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll_class);
                                                                        if (linearLayout != null) {
                                                                            i = R.id.tv_total_no_of_classrooms;
                                                                            TextView textView = (TextView) rootView.findViewById(R.id.tv_total_no_of_classrooms);
                                                                            if (textView != null) {
                                                                                i = R.id.tv_total_no_of_classrooms3;
                                                                                TextView textView2 = (TextView) rootView.findViewById(R.id.tv_total_no_of_classrooms3);
                                                                                if (textView2 != null) {
                                                                                    return new FragEnrollmentSecondaryBinding((RelativeLayout) rootView, button, relativeLayout, cardView, cardView2, cardView3, cardView4, cardView5, cardView6, cardView7, cardView8, cardView9, cardView10, editText, editText2, relativeLayout2, relativeLayout3, linearLayout, textView, textView2);
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
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

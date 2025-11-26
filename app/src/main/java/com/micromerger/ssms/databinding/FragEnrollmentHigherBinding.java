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
public final class FragEnrollmentHigherBinding implements ViewBinding {
    public final Button btnBack;
    public final RelativeLayout content;
    public final CardView cvClass11;
    public final CardView cvClass12;
    public final EditText etTotalEnrollments;
    public final EditText etTotalPresents;
    public final RelativeLayout footer;
    public final RelativeLayout header;
    public final LinearLayout llClass;
    private final RelativeLayout rootView;
    public final TextView tvTotalNoOfClassrooms;
    public final TextView tvTotalNoOfClassrooms3;

    private FragEnrollmentHigherBinding(RelativeLayout rootView, Button btnBack, RelativeLayout content, CardView cvClass11, CardView cvClass12, EditText etTotalEnrollments, EditText etTotalPresents, RelativeLayout footer, RelativeLayout header, LinearLayout llClass, TextView tvTotalNoOfClassrooms, TextView tvTotalNoOfClassrooms3) {
        this.rootView = rootView;
        this.btnBack = btnBack;
        this.content = content;
        this.cvClass11 = cvClass11;
        this.cvClass12 = cvClass12;
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

    public static FragEnrollmentHigherBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragEnrollmentHigherBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_enrollment_higher, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragEnrollmentHigherBinding bind(View rootView) {
        int i = R.id.btn_back;
        Button button = (Button) rootView.findViewById(R.id.btn_back);
        if (button != null) {
            i = R.id.content;
            RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.content);
            if (relativeLayout != null) {
                i = R.id.cv_class11;
                CardView cardView = (CardView) rootView.findViewById(R.id.cv_class11);
                if (cardView != null) {
                    i = R.id.cv_class12;
                    CardView cardView2 = (CardView) rootView.findViewById(R.id.cv_class12);
                    if (cardView2 != null) {
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
                                                    return new FragEnrollmentHigherBinding((RelativeLayout) rootView, button, relativeLayout, cardView, cardView2, editText, editText2, relativeLayout2, relativeLayout3, linearLayout, textView, textView2);
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

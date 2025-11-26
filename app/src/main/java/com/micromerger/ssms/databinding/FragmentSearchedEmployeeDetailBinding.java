package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentSearchedEmployeeDetailBinding implements ViewBinding {
    public final ImageButton copyButtonId;
    private final LinearLayout rootView;
    public final TextView tvAddress;
    public final TextView tvCnic;
    public final TextView tvDesig;
    public final TextView tvDob;
    public final TextView tvDoj;
    public final TextView tvId;
    public final TextView tvName;
    public final TextView tvRd;
    public final TextView tvSchool;
    public final TextView tvSemisCode;

    private FragmentSearchedEmployeeDetailBinding(LinearLayout rootView, ImageButton copyButtonId, TextView tvAddress, TextView tvCnic, TextView tvDesig, TextView tvDob, TextView tvDoj, TextView tvId, TextView tvName, TextView tvRd, TextView tvSchool, TextView tvSemisCode) {
        this.rootView = rootView;
        this.copyButtonId = copyButtonId;
        this.tvAddress = tvAddress;
        this.tvCnic = tvCnic;
        this.tvDesig = tvDesig;
        this.tvDob = tvDob;
        this.tvDoj = tvDoj;
        this.tvId = tvId;
        this.tvName = tvName;
        this.tvRd = tvRd;
        this.tvSchool = tvSchool;
        this.tvSemisCode = tvSemisCode;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSearchedEmployeeDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentSearchedEmployeeDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_searched_employee_detail, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSearchedEmployeeDetailBinding bind(View rootView) {
        int i = R.id.copy_button_id;
        ImageButton imageButton = (ImageButton) rootView.findViewById(R.id.copy_button_id);
        if (imageButton != null) {
            i = R.id.tv_address;
            TextView textView = (TextView) rootView.findViewById(R.id.tv_address);
            if (textView != null) {
                i = R.id.tv_cnic;
                TextView textView2 = (TextView) rootView.findViewById(R.id.tv_cnic);
                if (textView2 != null) {
                    i = R.id.tv_desig;
                    TextView textView3 = (TextView) rootView.findViewById(R.id.tv_desig);
                    if (textView3 != null) {
                        i = R.id.tv_dob;
                        TextView textView4 = (TextView) rootView.findViewById(R.id.tv_dob);
                        if (textView4 != null) {
                            i = R.id.tv_doj;
                            TextView textView5 = (TextView) rootView.findViewById(R.id.tv_doj);
                            if (textView5 != null) {
                                i = R.id.tv_id;
                                TextView textView6 = (TextView) rootView.findViewById(R.id.tv_id);
                                if (textView6 != null) {
                                    i = R.id.tv_name;
                                    TextView textView7 = (TextView) rootView.findViewById(R.id.tv_name);
                                    if (textView7 != null) {
                                        i = R.id.tv_rd;
                                        TextView textView8 = (TextView) rootView.findViewById(R.id.tv_rd);
                                        if (textView8 != null) {
                                            i = R.id.tv_school;
                                            TextView textView9 = (TextView) rootView.findViewById(R.id.tv_school);
                                            if (textView9 != null) {
                                                i = R.id.tv_semis_code;
                                                TextView textView10 = (TextView) rootView.findViewById(R.id.tv_semis_code);
                                                if (textView10 != null) {
                                                    return new FragmentSearchedEmployeeDetailBinding((LinearLayout) rootView, imageButton, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10);
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

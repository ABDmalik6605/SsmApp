package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class EmployeePayslipLayoutBinding implements ViewBinding {
    public final LinearLayout employeePayslipLayout;
    public final ImageView employeePayslipLayoutCameraIcon;
    public final ImageView employeePayslipPicture;
    private final LinearLayout rootView;
    public final TextView tvEmployeePayslip;

    private EmployeePayslipLayoutBinding(LinearLayout rootView, LinearLayout employeePayslipLayout, ImageView employeePayslipLayoutCameraIcon, ImageView employeePayslipPicture, TextView tvEmployeePayslip) {
        this.rootView = rootView;
        this.employeePayslipLayout = employeePayslipLayout;
        this.employeePayslipLayoutCameraIcon = employeePayslipLayoutCameraIcon;
        this.employeePayslipPicture = employeePayslipPicture;
        this.tvEmployeePayslip = tvEmployeePayslip;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static EmployeePayslipLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static EmployeePayslipLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.employee_payslip_layout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static EmployeePayslipLayoutBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.employee_payslip_layout_camera_icon;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.employee_payslip_layout_camera_icon);
        if (imageView != null) {
            i = R.id.employee_payslip_picture;
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.employee_payslip_picture);
            if (imageView2 != null) {
                i = R.id.tv_employee_payslip;
                TextView textView = (TextView) rootView.findViewById(R.id.tv_employee_payslip);
                if (textView != null) {
                    return new EmployeePayslipLayoutBinding(linearLayout, linearLayout, imageView, imageView2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

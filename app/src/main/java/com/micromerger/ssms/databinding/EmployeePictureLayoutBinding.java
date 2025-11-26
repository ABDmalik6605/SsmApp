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
public final class EmployeePictureLayoutBinding implements ViewBinding {
    public final ImageView employeePicture;
    public final ImageView employeePictureCameraIcon;
    public final LinearLayout employeePictureLayout;
    private final LinearLayout rootView;
    public final TextView tvEmployeeImage;

    private EmployeePictureLayoutBinding(LinearLayout rootView, ImageView employeePicture, ImageView employeePictureCameraIcon, LinearLayout employeePictureLayout, TextView tvEmployeeImage) {
        this.rootView = rootView;
        this.employeePicture = employeePicture;
        this.employeePictureCameraIcon = employeePictureCameraIcon;
        this.employeePictureLayout = employeePictureLayout;
        this.tvEmployeeImage = tvEmployeeImage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static EmployeePictureLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static EmployeePictureLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.employee_picture_layout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static EmployeePictureLayoutBinding bind(View rootView) {
        int i = R.id.employee_picture;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.employee_picture);
        if (imageView != null) {
            i = R.id.employee_picture_camera_icon;
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.employee_picture_camera_icon);
            if (imageView2 != null) {
                LinearLayout linearLayout = (LinearLayout) rootView;
                i = R.id.tv_employee_image;
                TextView textView = (TextView) rootView.findViewById(R.id.tv_employee_image);
                if (textView != null) {
                    return new EmployeePictureLayoutBinding(linearLayout, imageView, imageView2, linearLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

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
public final class EmployeeThumbLayoutBinding implements ViewBinding {
    public final ImageView employeeThumbCameraIcon;
    public final LinearLayout employeeThumbLayout;
    public final ImageView employeeThumbPicture;
    private final LinearLayout rootView;
    public final TextView tvEmployeeThumb;

    private EmployeeThumbLayoutBinding(LinearLayout rootView, ImageView employeeThumbCameraIcon, LinearLayout employeeThumbLayout, ImageView employeeThumbPicture, TextView tvEmployeeThumb) {
        this.rootView = rootView;
        this.employeeThumbCameraIcon = employeeThumbCameraIcon;
        this.employeeThumbLayout = employeeThumbLayout;
        this.employeeThumbPicture = employeeThumbPicture;
        this.tvEmployeeThumb = tvEmployeeThumb;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static EmployeeThumbLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static EmployeeThumbLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.employee_thumb_layout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static EmployeeThumbLayoutBinding bind(View rootView) {
        int i = R.id.employee_thumb_camera_icon;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.employee_thumb_camera_icon);
        if (imageView != null) {
            LinearLayout linearLayout = (LinearLayout) rootView;
            i = R.id.employee_thumb_picture;
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.employee_thumb_picture);
            if (imageView2 != null) {
                i = R.id.tv_employee_thumb;
                TextView textView = (TextView) rootView.findViewById(R.id.tv_employee_thumb);
                if (textView != null) {
                    return new EmployeeThumbLayoutBinding(linearLayout, imageView, linearLayout, imageView2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

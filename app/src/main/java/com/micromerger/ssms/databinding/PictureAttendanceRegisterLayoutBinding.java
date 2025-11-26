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
public final class PictureAttendanceRegisterLayoutBinding implements ViewBinding {
    public final ImageView attendanceRegisterPicture;
    public final LinearLayout pictureAttendanceRegisterLayout;
    public final ImageView pictureAttendanceRegisterLayoutCameraIcon;
    private final LinearLayout rootView;
    public final TextView tvPictureAttendanceRegister;

    private PictureAttendanceRegisterLayoutBinding(LinearLayout rootView, ImageView attendanceRegisterPicture, LinearLayout pictureAttendanceRegisterLayout, ImageView pictureAttendanceRegisterLayoutCameraIcon, TextView tvPictureAttendanceRegister) {
        this.rootView = rootView;
        this.attendanceRegisterPicture = attendanceRegisterPicture;
        this.pictureAttendanceRegisterLayout = pictureAttendanceRegisterLayout;
        this.pictureAttendanceRegisterLayoutCameraIcon = pictureAttendanceRegisterLayoutCameraIcon;
        this.tvPictureAttendanceRegister = tvPictureAttendanceRegister;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static PictureAttendanceRegisterLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static PictureAttendanceRegisterLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.picture_attendance_register_layout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PictureAttendanceRegisterLayoutBinding bind(View rootView) {
        int i = R.id.attendance_register_picture;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.attendance_register_picture);
        if (imageView != null) {
            LinearLayout linearLayout = (LinearLayout) rootView;
            i = R.id.picture_attendance_register_layout_camera_icon;
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.picture_attendance_register_layout_camera_icon);
            if (imageView2 != null) {
                i = R.id.tv_picture_attendance_register;
                TextView textView = (TextView) rootView.findViewById(R.id.tv_picture_attendance_register);
                if (textView != null) {
                    return new PictureAttendanceRegisterLayoutBinding(linearLayout, imageView, linearLayout, imageView2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

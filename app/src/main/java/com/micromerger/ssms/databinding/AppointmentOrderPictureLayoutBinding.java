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
public final class AppointmentOrderPictureLayoutBinding implements ViewBinding {
    public final ImageView appointmentOrderPdf;
    public final ImageView appointmentOrderPicture;
    public final ImageView appointmentOrderPictureCameraIcon;
    public final LinearLayout appointmentOrderPictureLayout;
    private final LinearLayout rootView;
    public final TextView tvAppointmentOrder;

    private AppointmentOrderPictureLayoutBinding(LinearLayout rootView, ImageView appointmentOrderPdf, ImageView appointmentOrderPicture, ImageView appointmentOrderPictureCameraIcon, LinearLayout appointmentOrderPictureLayout, TextView tvAppointmentOrder) {
        this.rootView = rootView;
        this.appointmentOrderPdf = appointmentOrderPdf;
        this.appointmentOrderPicture = appointmentOrderPicture;
        this.appointmentOrderPictureCameraIcon = appointmentOrderPictureCameraIcon;
        this.appointmentOrderPictureLayout = appointmentOrderPictureLayout;
        this.tvAppointmentOrder = tvAppointmentOrder;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static AppointmentOrderPictureLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static AppointmentOrderPictureLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.appointment_order_picture_layout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static AppointmentOrderPictureLayoutBinding bind(View rootView) {
        int i = R.id.appointment_order_pdf;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.appointment_order_pdf);
        if (imageView != null) {
            i = R.id.appointment_order_picture;
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.appointment_order_picture);
            if (imageView2 != null) {
                i = R.id.appointment_order_picture_camera_icon;
                ImageView imageView3 = (ImageView) rootView.findViewById(R.id.appointment_order_picture_camera_icon);
                if (imageView3 != null) {
                    LinearLayout linearLayout = (LinearLayout) rootView;
                    i = R.id.tv_appointment_order;
                    TextView textView = (TextView) rootView.findViewById(R.id.tv_appointment_order);
                    if (textView != null) {
                        return new AppointmentOrderPictureLayoutBinding(linearLayout, imageView, imageView2, imageView3, linearLayout, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

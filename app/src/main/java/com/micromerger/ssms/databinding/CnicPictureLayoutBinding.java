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
public final class CnicPictureLayoutBinding implements ViewBinding {
    public final ImageView cnicPicture;
    public final ImageView cnicPictureCameraIcon;
    public final LinearLayout cnicPictureLayout;
    private final LinearLayout rootView;
    public final TextView tvCnicImage;

    private CnicPictureLayoutBinding(LinearLayout rootView, ImageView cnicPicture, ImageView cnicPictureCameraIcon, LinearLayout cnicPictureLayout, TextView tvCnicImage) {
        this.rootView = rootView;
        this.cnicPicture = cnicPicture;
        this.cnicPictureCameraIcon = cnicPictureCameraIcon;
        this.cnicPictureLayout = cnicPictureLayout;
        this.tvCnicImage = tvCnicImage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static CnicPictureLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static CnicPictureLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.cnic_picture_layout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CnicPictureLayoutBinding bind(View rootView) {
        int i = R.id.cnic_picture;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.cnic_picture);
        if (imageView != null) {
            i = R.id.cnic_picture_camera_icon;
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.cnic_picture_camera_icon);
            if (imageView2 != null) {
                LinearLayout linearLayout = (LinearLayout) rootView;
                i = R.id.tv_cnic_image;
                TextView textView = (TextView) rootView.findViewById(R.id.tv_cnic_image);
                if (textView != null) {
                    return new CnicPictureLayoutBinding(linearLayout, imageView, imageView2, linearLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
